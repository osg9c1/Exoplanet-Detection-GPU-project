#!/bin/sh
cd $1
for j in `ls -a *.txt`
do
name1=$j
cp -i $name1 /home/regulus/workspace/OSproj/
done
cd /home/regulus/workspace/OSproj/
for i in `ls -a *.txt`
do
filename=$i
./rpusvm-scale -x "-1:1" -y "-1:1" -s test.save $filename test.scaled
./rpusvm-predict test.scaled newmixed.rpusvm1 $filename.out1
done
for k in `ls -a *.out1`
do
name2=$k
mv -i $name2 /home/regulus/workspace/OSproj/outputs/
done

