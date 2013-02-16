/*
 *
 *  Created on: Feb 13,2013
 *      Author: surabhi
 */
/* convert cvs data to libsvm/svm-light format */
/**need to give csv file as input and it will create libsvm acceptable file with the name <input_filename>_out**/ 
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include<iostream>
#include <string>
char buf[10000000];

int main(int argc, char **argv)
{
	FILE *fp,*wp;
        char *output;
        std::string value;
        output=(char*)malloc(sizeof(char)*1000);
	if(argc!=2) { fprintf(stderr,"Usage %s input_filename \n",argv[0]); }
	if((fp=fopen(argv[1],"r"))==NULL)
	{
		fprintf(stderr,"Can't open input file %s\n",argv[1]);
	}
        output=strcat(argv[1],"_out");
        printf("enter +1 or -1");
        std::cin>>value;
        if((wp=fopen(output,"w+"))==NULL)
	{
		fprintf(stderr,"Can't open output file %s\n",output);
	}	
	while(fscanf(fp,"%[^\n]\n",buf)==1)   /*reg ex in C for EOL*/
	{
		int i=1,j;
		char *p=strtok(buf,",");
		const char *s1;
                s1=value.c_str(); 
                fprintf(wp, "%s ", s1);
                if(strcmp(p," ")==0 || strcmp(p,"")==0) 
                 i++; 
                 else
	           fprintf(wp, "%d:%s ",i++,p);
              
		while((p=strtok(NULL,","))){                            
                             if(strcmp(p," ")==0 || strcmp(p,"")==0)                           
                              i++;
                             else
			        fprintf(wp, "%d:%s ",i++,p);
                       }
		fprintf(wp,"\n");
	}
	return 0;
}
