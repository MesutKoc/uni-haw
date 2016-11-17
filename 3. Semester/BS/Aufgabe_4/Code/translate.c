/**
 * Translate (Caesar encryption) Module for Linuxkernel.
 * Two devices will be to your dev folder: /dev/trans0, /dev/trans1
 * trans0 will encrypt a given char array and trans1 will decrypt it.
 */

#include "translate.h"

struct trans_dev *trans_devices;
char ceaser(char move_char, int shift);
/*
 * Gibt den Index von einem gegebenen char (in einem Array) zurück
 * @param: a[], the size from the array, the value to search
 * @return: the index or in worst-case -1
 */
int find_index(const char a[], const int size, const char value) {
	int i = 0;
	for (i = 0; i < size; i++) if (a[i] == value) return i;
	return(-1);
}

/**
 * Shifts the char code of <move_char> by <shift> times,
 * if <move_char> is present in <shift_table>. Otherwise <move_char> is not being shifted.
 */
char caesar(char move_char, int shift) {
	int index = find_index(shift_table, NELEMS(shift_table), move_char); // Fetch the index of move_char
	if(index >= 0) {
		int new_index = (index + shift);
		while (new_index < 0)
			new_index = new_index + NELEMS(shift_table);

		new_index %= NELEMS(shift_table);
		move_char = shift_table[new_index];
	}
	return move_char;
}

/*
 * Der Kernel ruft die zugehörige trans_open im Treiber auf, wenn keine Zugriffsverletzungen vorlegen
 * Wichtige Aufgabe ist, zu überprüfen, ob die zugreifende Treiberinstanz zum Zugriff berechtigt ist.
 * @param: <geraetedatei> zur Gerätedatei gehöre die Zugriffsrechte ebenso Information über den OWNER der Datei.
 * 			Wir greifen aber drauf zu, um die Major (vor allem) die Minornummer auszulesen.
 * 	       <instsance> enthält sämtliche Elemente, die die aufrufende Treiberinstanz spezifizieren.
 * 		   zB um festzustellen, in welchem Modus der Zugriff erfolgt. ob lesen oder schreibend etc.
 */
int trans_open(struct inode *geraetedatei, struct file *instance) {
	struct trans_dev *dev;
	printk(KERN_INFO "OPEN: Translate Module wird geoeffnet\n");

	/*
	 * container_of(pointer, der container Typ, und das feld)
	 * @return: gibt ein Poiter auf die Struktur zurück
	 */
	dev = container_of(geraetedatei->i_cdev, struct trans_dev, cdev);
	instance -> private_data = dev;

	// Versuche, semaphore zu sperren
	if (down_interruptible(&dev->sem)) return -ERESTARTSYS;

	// Die Zugriffflags überprüfen (1. Fall: Nur schreiben)
	if ((instance->f_flags & O_ACCMODE) == O_WRONLY && !dev->writeOpened){
		dev->writeOpened = 1;
		// Nur lesen
	} else if((instance->f_flags & O_ACCMODE) == O_RDONLY && !dev->readOpened){
		dev->readOpened = 1;
		// Beides
	} else if((instance->f_flags & O_ACCMODE) == O_RDWR && !dev->readOpened && !dev->writeOpened){
		dev->writeOpened = 1;
		dev->readOpened = 1;
	}
	else {
		// Wenn die Zugriffsberechtigungen nicht zutreffen, ist die DATEI bereits geöffnet
		printk(KERN_WARNING "OPEN: Translate Module ist bereits geoeffnet\n");
		return -EBUSY;
	}

	/*
	 * These manipulate the module usage count, to protect against removal (a module also can't be removed if another module uses one of its exported symbols: see below).
	 * Before calling into module code, you should call try_module_get() on that module: if it fails, then the module is being removed and you should act as if it wasn't there.
	 * Otherwise, you can safely enter the module, and call module_put() when you're finished.
	 */
	try_module_get(THIS_MODULE);
	// Hier geben wir die Semaphore wieder frei. Mit dem Befehl "up".
	up(&(dev->sem));
	return 0;
}

/*
 * Ruft die Appilikation ein close auf, wird treiberseitig auch unsere Funktion trans_close aufgerufen,
 * deren Adresse Adresse innerhalb der struct file_operations unter dem Namen realease abgelegt ist.
 * Aufgabe von trans_close/release ist es, die mit der Treiberinstanz assoziierten Ressourcen freizugeben.
 * @param: <geraetedatei> zur Gerätedatei gehöre die Zugriffsrechte ebenso Information über den OWNER der Datei.
 * 			Wir greifen aber drauf zu, um die Major (vor allem) die Minornummer auszulesen.
 * 	       <instsance> enthält sämtliche Elemente, die die aufrufende Treiberinstanz spezifizieren.
 * 		   zB um festzustellen, in welchem Modus der Zugriff erfolgt. ob lesen oder schreibend etc.
 * @return 0, wenn der Treiber nichts machen muss.
 */
int trans_close(struct inode *geraetedatei, struct file *instance) {
	 struct trans_dev *dev; 
	 printk(KERN_INFO "CLOSE: Translate Module wird verlassen\n");
	
	/*
	 * container_of(pointer, der container Typ, und das feld)
	 * @return: gibt ein Poiter auf die Struktur zurück
	 */
	dev = container_of(geraetedatei->i_cdev, struct trans_dev, cdev);
	instance->private_data = dev;

	// Zugriffsflags wieder zurücksetzen
	if((instance->f_flags & O_ACCMODE) == O_WRONLY && dev->writeOpened){
		dev -> writeOpened = 0;
	} else if((instance->f_flags & O_ACCMODE) == O_RDONLY && dev->readOpened){
		dev -> readOpened = 0;
	} else if((instance->f_flags & O_ACCMODE) == O_RDWR && dev->readOpened && dev->writeOpened){
		dev -> writeOpened = 0;
		dev -> readOpened = 0;
	}

	// MODULE-USAGE aktualisieren
	module_put(THIS_MODULE);
	return 0;
}

/*
 * Zum schreiben des Treibers
 * @param ....
 */
ssize_t trans_write(struct file *filp, const char __user *buf, size_t count, loff_t *f_pos) {
  
	struct trans_dev *dev = filp->private_data;
	// Get device from driver file
	int bytes_written = 0;
	// Get current character
	char tmp = *buf;
	printk(KERN_INFO "WRITE: Starte schreiben von %d Zeichen.\n", count);
	
	
	
	// Writeloop for required bytes to write
	for(bytes_written = 0; (bytes_written < count); bytes_written++){
		// Check for condition variable to be sure we can write (e.g. buffer is full, so we'd have to wait)
		if(!WRITE_POSSIBLE){
			// IF we should not block error out as we're forced to block
			if(filp->f_flags&O_NONBLOCK) return -EAGAIN;
			// Wait for condition
			if(wait_event_interruptible(dev->write_queue,WRITE_POSSIBLE)) return -ERESTARTSYS;
		}
		

		// Get character from userspace into kernelspace
		get_user(*(dev->p_in), buf++);
		// Shift character
		*dev->p_in = caesar(*dev->p_in,dev->shift);
		// Move to empty character
		dev->p_in++;
		// Increase count of buffered characters
		dev->count++;
		printk(KERN_INFO "WRITE: schreibe zeichen %d\n", bytes_written + 1);
		// If buffer is full, move cached data to buffer
		if(((dev->p_in - dev->data) % BUF_LEN) == 0) dev->p_in = dev->data;
		// Notify read process to have more data to read
		wake_up_interruptible(&dev->read_queue);
	}
	printk(KERN_INFO "WRITE: %d konnten nicht geschrieben werden\n", count - bytes_written);
	// Return written bytes
	return bytes_written;
}

/*
 * Die Funktion hat zur Aufgabe, die vom Gerät angeforderten Daten in den Speicherbereich der Appilikation zu kopieren.
 * Dabei ist drauf zu achten, dass nicht mehr Byte in den übergebenen Buffer kopiert werden, als dieser überhaupt
 * zur Verfügung stellt.
 * @param: <instance> die dazugehörige Treiberinstanz. <buf> Adresse des Speicherbereis im Usespace
 * 	       <count> maximale Anzahl zu kopierender Byte
 * 	       <pos> ist ein Zeiger auf den Offset, auf den innerhalb des Gerätes zugegriffen werden soll.
 */
ssize_t trans_read(struct file *instance, char __user *buf, size_t count, loff_t *f_pos) {
	int bytes_read = 0;
	// Get device from file handle
	struct trans_dev *dev = instance->private_data;
	printk(KERN_INFO "READ: Start reading.");

	// While there should be something to read
	while (count) {
		// If there's nothing to read however
		if(!READ_POSSIBLE){
			// Error if we shouldn't block
			if(instance->f_flags&O_NONBLOCK) return -EAGAIN;
			// Wait for new data to be available
			if(wait_event_interruptible(dev->read_queue,READ_POSSIBLE)) return -ERESTARTSYS;
		}
		// Put data from kernelspace into userspace
		put_user(*(dev->p_out++), buf++);
		// Decrease amount to read
		count--;
		// If there's nothing to read from cache, but from buffer
		if(((dev->p_out - dev->data) % BUF_LEN) == 0) dev->p_out = dev->data;
		// Decrease size of held data
		dev->count--;
		bytes_read++;
		// Notify write process
		wake_up_interruptible(&dev->write_queue);
		printk(KERN_INFO "Noch zu lesende Zeichen: %d\n", count);
	}
	// Return amount of read bytes
	return bytes_read;
}

/**
 * Setup of character device
 */
static void setup_cdev(struct trans_dev *dev, int index) {
	int err, devno = MKDEV(major, index); // Durch MKDEV erhalten wir zu einer M und Minornumber die Gerätenummer
	// Init driver with out file operation handles
	cdev_init(&dev->cdev, &fops);
	// Den Besitzer auf dieses MODUL setzen
	dev -> cdev.owner = THIS_MODULE;
    // Die Opereationen noch mal setzen
	dev -> cdev.ops = &fops;
	// Set shifting amount for caesar enc. We do - the amount if we're decrypting.
	dev -> shift = (index ? -SHIFT : SHIFT);
	// Zurücksetzen
	dev -> readOpened = 0;
	dev -> writeOpened = 0;
	// Die Quees initialisieren
	init_waitqueue_head(&dev->read_queue);
	init_waitqueue_head(&dev->write_queue);
	// Semaphoren initialisieren
	sema_init(&(dev->sem),1);
	/* Add the driver to the kernel - Fail gracefully if need be */
	if ((err = cdev_add (&dev->cdev, devno, 1))) printk(KERN_NOTICE "Error %d adding scull%d", err, index);
	// Set the current cache to data
	dev -> p_in  = dev -> data;
	dev -> p_out = dev -> data;
	dev -> count = 0;
}



/*
 * Zum Initialisieren des Treibers
 * @param void
 * @return int (errcode)
 */
static int __init translate_init(void) {
	int i, err;
	printk(KERN_INFO "INIT: Translate Module wird hinzugefuegt\n");
	

	/*
	 * Bei alloc_chrdev_region wird die zu reservierende Gerätenummer nicht vorgegeben
	 * sondern es wird vom Kernel der nächste freie, ausreichend große Bereich von Gerätenr. reserviert
	 * @param: Eine Speicherzelle, in die die erste Gerätenr. abelegegt wird.
	 */
	if((err = alloc_chrdev_region(&trans_device, 0,1, DEVICE_NAME)) != 0) {
		printk(KERN_ALERT "INIT: Erstellen des Devices fehlgeschalgen. Error: %d\n", major);
		return -1;
	}
	// Wir holen uns die Major-Nummer
	major = MAJOR(trans_device);

	/*
	 * Das Objekt, welches einen zeichenorientierten Treiber beim Kernel anmeldet, ist vom Typ struct cdev
	 * aber in unserem Fall verwenden wir stattdessen kmalloc.
	 */
	trans_devices = kmalloc(1 * sizeof(struct trans_dev), GFP_KERNEL); // Speicherzuweisung für unsere Devices

	// Ist trans_devices == NULL?
	if (!trans_devices) {
		translate_exit(); // Wenn wir Speicher nicht zuweisen konnten, dann exit
		return -ENOMEM;
	}
	// Den zugewiesenen Speicher für unsere Devices setzen
	memset(trans_devices, 0, 2 * sizeof(struct trans_dev));
	// Unsere Devices konfiguieren
	for (i = 0; i < 1; i++) setup_cdev(&trans_devices[i], i);
	return 0;
}

/*
 * Zum Bereinigen
 */
static void translate_exit(void) {
	int i;
	printk(KERN_INFO "EXIT: Translate-Module wird entfernt\n");
	

	// Wenn Speicherzuweisung stattgefunden hat
	if (trans_devices) {
		for (i = 0; i < 1; i++) cdev_del(&trans_devices[i].cdev); // Beide Devices entfernen (über kobject_put, um dem Treiber beim Kernel abzumemlden.)
		kfree(trans_devices); // Den zugewiesenen Speicher wieder leeren
	}
	// Den reservierten Bereich der Gerätenummern wieder freigeben.
	unregister_chrdev_region(trans_device, 1);
}

//=====================================================
// 			Referenzen für init & exit setzen
//=====================================================
module_init(translate_init);
module_exit(translate_exit);

//=====================================================
// 			METAINFORMATIONEN
//=====================================================
MODULE_AUTHOR("Mesut, Anton");
MODULE_LICENSE("GPL");

//=====================================================
// Die eben gesetzten Variablen werden dem Module uebergeben
//=====================================================
module_param(buf_len, int, S_IRUGO);
module_param(shift_size, int, S_IRUGO);
MODULE_PARM_DESC(buf_len, "Internal buffer size");
MODULE_PARM_DESC(shift_size, "Internal shift size");