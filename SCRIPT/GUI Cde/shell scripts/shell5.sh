#!/bin/sh  

filename = $1

rpusvm-scale -x "-1:1" -y "-1:1" -s $filename.save $filename $filename.scaled

rpusvm-predict $filename.scaled cadata.rpusvm1 test.out1
