#!/bin/sh  

./rpusvm-scale -x "-1:1" -y "-1:1" -s testshell.save $1 testshell.scaled

./rpusvm-predict testshell.scaled mixed2.rpusvm1 testshellmar.out1

Rscript execute.r
