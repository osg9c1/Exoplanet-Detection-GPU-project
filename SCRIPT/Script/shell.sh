#script to replace null vaules,quotes and spaces
#go into the folder where the files are present and run this shell script


#!/bin/sh  
for i in `ls -a *.txt`  
do  
filename=$i  
 sed -i 's/"//g' $filename
sed -i 's/ //g' $filename
sed -i 's/NULL/ /g' $filename

done
