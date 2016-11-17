# In diesem Fall legt man also fest, dass für die Ausführung des Skriptes die Bash verwendet werden soll.
#! /bin/bash

# ------------------------------------------------------------
# This variable for the version script
# ------------------------------------------------------------
s_version="1.0"

# ------------------------------------------------------------
# This variable size for the split
# ------------------------------------------------------------
s_size=10
s_debug=1

# ------------------------------------------------------------
# This function splits the given file
# ------------------------------------------------------------
splitfix_split() {
	#---------------------------------------
	# Wenn Datei NICHT existiert (regulär)
	# Dann exit mit Fehlerangabe 1
	#---------------------------------------
	s_debug "split file $1"
	
	if [ ! -f $1 ]; then
		echo "file $1 not found"
		exit 1
	fi
	
	#---------------------------------------
	# Suchen
	# > Dient der Umleitung der Standardausgabe in eine Datei.
	# Durch > /dev/null wird die STDOUT nach /dev/null geleitet, 
	# durch 2>&1 wird STDERR nach STDOUT geleitet 
	# -b sorgt für keine Ausgabe des Filenamen im Terminal
	# --mime-type stellt den typen des Files fest.
	#---------------------------------------
	file -b --mime-type $1 | grep text > /dev/null 2>&1
	
	#---------------------------------------------
	# $? speichert den letzten ausgeführten Befehl. 
	# Ist Zeilenende bzw. Stringende erreicht?
	# -a (Suffix N) -l(Anzahl Zeilen in die AD) #-d(numerische Suffix)
	#---------------------------------------------
	if [ $? -eq 0 ]; then
		split -a 4 -l $s_size -d $1 $1"."
	else
		#---------------------------------------------
		# -b für Bytes, wenn wir von einem nicht 
		# Textfile ausgehen
		#---------------------------------------------
		split -a 4 -b $s_size"k" -d $1 $1"."
	fi
}

splitfix_setsize() {

	s_debug "Geben Sie die gewuenschte Anzahl an!"

	if [ $# -eq 0 ]; then
		echo 'Es wurde keine Files ausgewaehlt!!'
		exit 1
	fi
 
	echo $1 | grep  "[^0-9]" > /dev/null 2>&1
	if [ "$?" -eq "0" ]; then
		echo "Ungültige Anzahl!"
		exit 1
	fi
 
	s_size=$1
	
	s_debug "Die Datei wird in $s_size geteilt."

}

s_enable_debug(){
    s_debug=0
}

s_debug() {
      test $s_debug = 0 && echo $1
}

# ------------------------------------------------------------
# This function shows the help text for this splitscript
# ------------------------------------------------------------
usage() {
cat <<EOF
	$0 [OPTIONS] FILE [FILE ...] Split FILE into fixed-size pieces.
	The pieces are 10 lines long, if FILE is a text file.
	The pieces are 10 kiB long, if FILE is *not* a text file.
	The last piece may be smaller, it contains the rest of the original file.
	The output files bear the name of the input file with a 4-digit numerical suffix.
	The original file can be reconstructed with the command ''cat FILE.*''

	EXAMPLE
	splitfix.sh foo.pdf
	splits foo.pdf into the files
	foo.pdf.0000 foo.pdf.0001 etc.

	splitfix.sh [-h | --help] This help text
	splitfix.sh --version Print version Number

	OPTIONS:
	-h --help		this help text
	-s SIZE			size of the pieces in lines (for text files) or in kiBytes (for other files)
	-v --verbose		print debugging messages
	--version		print version number
EOF
}

# ------------------------------------------------------------
# $#: Die Anzahl der an das Script übergebenen Parameter.
# Wenn die übergebene Paramenter kleiner als 1 ist; invalid
# ------------------------------------------------------------
if [ $# -lt 1 ]; then
	echo "Invalid option or arguments!"
	exit 1 
fi

# ------------------------------------------------------------
# solange Parameterübergabe greaterthan 0
# * ist Unbekannter Parameter
# ------------------------------------------------------------
while [ $# -gt 0 ]
do 
	case $1 in
		--version)
			echo $s_version
			exit 0
			;;
		-h | --help)
			usage
			exit 0
			;;
		-s)
			splitfix_setsize $2
			shift
			shift
			;;
		-v | --verbose)
			s_enable_debug
			shift
			;;
		*)
			splitfix_split $1
			shift
			;;
	esac
done

exit 0