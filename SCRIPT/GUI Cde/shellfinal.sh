#!/bin/sh
cd /home/regulus/workspace/OSproj/outputs/
for i in `ls -a *.txt.out1`
do
filename=$i
rm $filename
done
