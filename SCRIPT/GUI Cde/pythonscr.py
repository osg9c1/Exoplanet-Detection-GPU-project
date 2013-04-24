#author:surabhi pandey
#code converts all the fits files in the folder named path to csv file and save it in folder outpath
#please give appropriate path for achieveing the same
import pyfits
import numpy
import os  
path = 'PYTHON/'
outpath='OUTPUT_F/'
outfile=''
i=0;
listing = os.listdir(path)
for infile in listing:
   f = pyfits.open('PYTHON/'+infile) # open a FITS file
   scidata = f[1].data
   a=numpy.asarray(scidata)
   outfile= os.path.splitext(infile)[0]+".csv"
   i=i+1
   numpy.savetxt(outpath+outfile,a,delimiter=",")

