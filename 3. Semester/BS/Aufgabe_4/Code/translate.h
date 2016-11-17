/*
 * File: translate.h
 * Author: Mesut Koc
 * Created: 19.12.2015
 * TODO: nothing
 */

//=====================================================
// 			CONFIG
//=====================================================
#ifndef SRC_TRANSLATE_H_
#define SRC_TRANSLATE_H_

#include <linux/kernel.h>
#include <linux/version.h> // Die Kernel-Version wird eingebunden, für die die der Treiber kompoiliert wird
#include <linux/module.h> // Enthält Makros und Defines für die Integration des Moduls in den Kernel. (MODULE_LICENSE)
#include <linux/moduleparam.h>
#include <linux/init.h> // Wird für die Schlüsselwrter _init und _exit benötigt. Helfen dem Kernel, die Speicherrr. zu verwalten.
#include <linux/fs.h> // Char device aufsetzten, device Nummer bekommen.
#include <linux/cdev.h>
#include <linux/device.h>
#include <linux/slab.h> // Für kzalloc (Speicherzuweisung)
#include <linux/uaccess.h>
#include <linux/sched.h> // Für wait_event_interruptible
#include <linux/mutex.h>

#define NELEMS(x)  ((sizeof(x) / sizeof(x[0])-1))
#define DEVICE_NAME "trans"
#define BUF_LEN 40
#define SHIFT 3
#define READ_POSSIBLE (dev->count > 0) // Schreiben möglich, wenn weniger Elemente im Ringbuffer drin sind als translate_bufsize
#define WRITE_POSSIBLE (dev->count < buf_len) // Lesen möglich, wenn mehr Elemente als 0 im Ringbuffer drin sind
//=====================================================
// 			CONFIG-END
//=====================================================

//=====================================================
// 			VARIABLES
//=====================================================
const char shift_table[] = {"ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz"};
int buf_len = BUF_LEN, shift_size = SHIFT;

static int major; // Unsere Majornummer
static dev_t trans_device; // Wird gebraucht für die Gerätenummern
//=====================================================
// 			VARIABLES-END
//=====================================================

//=====================================================
/* STRUCT: translate_dev
 * *p_in  => Zeiger auf Stelle in Buffer, wo reingeschrieben werden kann
 * *p_out => Zeiger auf Stelle in Buffer, wo herausgelesen weerden kann
 * shift  => Verschiebungsgrad: bei Trans0 -> +1, bei Trans1 -> -1
 * count  => Anzahl der elemente im Buffer
 * readOpened => Prädikat, ob Device bereits offen zum lesen ist
 * writeOpened => Prädikat, ob Device bereits offen zum schreiben ist
 * read_queue => Warteschlange wenn das Device nicht lesen kann, weil keine Elemente vorhanden
 * write_queue => Warteschlange, wenn das Device nicht schreiben kann, weil Buffer voll
 * sem =>
 * cdev => Respräsentiert einen Charakter-Device und ist verfügbar im Ordner /dev/
 */
//=====================================================
static  struct trans_dev {
	char data[BUF_LEN], *p_in, *p_out;
	int  shift, count, readOpened, writeOpened;
	wait_queue_head_t read_queue, write_queue;
	struct semaphore sem;
	struct cdev cdev;
};

//=====================================================
// 	FUNCTION: translate_init
//	@return: int (err code)
//=====================================================
static int __init translate_init(void);

//=====================================================
// 	FUNCTION: translate_exit
//	@Description: clean all the translates
//=====================================================
static void translate_exit(void);

//=====================================================
// 	FUNCTION: trans_open
//	@param: the pointer of the device, the instance
//	@Description: Wenn das Programm auf Device zugreifen möchte
//=====================================================
int trans_open(struct inode *geraetedatei, struct file *instance);

//=====================================================
// 	FUNCTION: trans_close
//	@param: the pointer of the device, the instance
//	@Description: Wenn das Programm Driver space verlässt
//=====================================================
int trans_close(struct inode *geraetedatei, struct file *instance);

//=====================================================
// 	FUNCTION: translate_write
//	@param:
//	@Description: Wenn das Programm schreiben möchte
//=====================================================
ssize_t trans_write(struct file *filp, const char __user *buf, size_t count, loff_t *f_pos);

//=====================================================
// 	FUNCTION: translate_read
//	@param:
//	@Description: Wenn das Programm lesen möchte
//=====================================================
ssize_t trans_read(struct file *instance, char __user *buf, size_t count, loff_t *f_pos);

//=====================================================
// 	STRUCT: file_operations
//	DESCRIPTION: Hier werden die Adressen der der eigentlichen
// 			     Treiberfunktionen hinterlegt
//=====================================================
static struct file_operations fops = {
	.owner   =     THIS_MODULE,
	.read    =     trans_read,
	.write 	 =     trans_write,
	.open    =     trans_open,
	.release =     trans_close,
};

#endif
