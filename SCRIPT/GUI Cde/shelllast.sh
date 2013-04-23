#!/bin/sh
cd /home/regulus/workspace/OSproj/lists/
Rscript script.r
cd /home/regulus/workspace/OSproj/
rm output1.csv 
echo "Distance,radius_ratio,const,Name,kplr_id,radius" >> output1.csv
set $index = 1
for i in `ls -a *.txt`  
do  
index=$(($index + 1))
filename=$i 
cat << EOF >> $filename
END
EOF
name=${i%.*}
./a.out $filename $index
done
for i in `ls -a *.txt`
do
filename=$i
rm $filename
done
rm /home/regulus/worspace/OSproj/dist/examples/output1.csv
cp -i output1.csv /home/regulus/worspace/OSproj/dist/examples/
