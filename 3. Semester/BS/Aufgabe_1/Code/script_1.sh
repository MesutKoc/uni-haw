#!/bin/bash
#script

mkdir ~/Bs_Prak
cd ~/Bs_Prak
pwd
whoami
ls-l

ls -x

ls -t

ls -r

ls -R

ls -l | sort -rnk5

cat << EOF > text1.txt
Hallo
EOF

cat << EOF > text2.txt
Hallo
EOF

cat << EOF > text3.txt
Hallo
EOF

ln -s text1.txt ltext1.txt
ln -s text2.txt ltext2.txt
ln -s text3.txt ltext3.txt

ls -l

cat << EOF > ltext1.txt

EOF

rm ltext1.txt

ls -l

rm text2.txt

ls -l


ls ?*?
ls text1?*
ls text[12]*
ls [text13]*
ls text[1-3]*

ls /ect/ | grep t$
ls -l /ect/ | grep ^t
ls -l /ect/ | grep „\<t“

pstree 
ps a
ps aux

pstree | grep „\<k“

