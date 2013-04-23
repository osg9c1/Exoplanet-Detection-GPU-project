%{
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>
FILE *out;
float entry[5];
char output_file[100];
char *ch,filename[100],*file,*name;
int i=0,power=0;
double sap_flux,sap_bkg,radius=0,per_large=0,sap_flux_err=0,total_dist=0,dist=0;
float total=0,record=0;

%}

sapFlux                    [4][":"][0-9]*["."][0-9]*[E][+|-][0-9]*
sapFlux2                    [4][":"][0-9]*["."][0-9]*[" "]+
sapFlux3                    [4][":"][0-9]*["."][0-9]*[e][+|-][0-9]*
sapFluxErr                 [5][":"][0-9]*["."][0-9]*[E][+|-][0-9]*
sapFluxErr2                 [5][":"][0-9]*["."][0-9]*[" "]+
sapFluxErr3                 [5][":"][0-9]*["."][0-9]*[e][+|-][0-9]*
sapBkg                     [6][":"][0-9]*["."][0-9]*[E][+|-][0-9]*
sapBkg2                     [6][":"][0-9]*["."][0-9]*[" "]+
sapBkg3                     [6][":"][0-9]*["."][0-9]*[e][+|-][0-9]*
mom_cen                    [16][":"][0-9]*["."][0-9]*[E][+|-][0-9]*
mom_cen2                    [16][":"][0-9]*["."][0-9]*
mom_cen3                    [16][":"][0-9]*["."][0-9]*[e][+|-][0-9]*
others                     [0-9]*
other2                     [":" "." "E" "+" "-" "e"]
other3                     [-]
END		           [E][N][D]
%%
{sapFlux}                     {strtok(yytext,":");ch=strtok(NULL,"E");sap_flux=atof(ch);ch=strtok(NULL,"E");
                              power=atoi(ch);sap_flux=sap_flux*pow(10,power);record++;power=0;}
{sapFlux2}                     {strtok(yytext,":");ch=strtok(NULL," ");sap_flux=atof(ch);record++;power=0;}
{sapFlux3}                     {strtok(yytext,":");ch=strtok(NULL,"e");sap_flux=atof(ch);ch=strtok(NULL,"E");
                              power=atoi(ch);sap_flux=sap_flux*pow(10,power);record++;power=0;}
{sapFluxErr}                  {strtok(yytext,":");ch=strtok(NULL,"E");sap_flux_err=atof(ch);ch=strtok(NULL,"E");
                              power=atoi(ch);sap_flux_err=sap_flux_err*pow(10,power);sap_flux=sap_flux-sap_flux_err;power=0;}
{sapFluxErr2}                  {strtok(yytext,":");ch=strtok(NULL," ");sap_flux=sap_flux-sap_flux_err;power=0;}
{sapFluxErr3}                  {strtok(yytext,":");ch=strtok(NULL,"e");sap_flux_err=atof(ch);ch=strtok(NULL,"E");
                              power=atoi(ch);sap_flux_err=sap_flux_err*pow(10,power);sap_flux=sap_flux-sap_flux_err;power=0;}
{sapBkg}                      {strtok(yytext,":");ch=strtok(NULL,"E");sap_bkg=atof(ch);ch=strtok(NULL,"E");
                              power=atoi(ch);sap_bkg=sap_bkg*pow(10,power);total=total+(sap_flux/(sap_bkg+sap_flux));sap_flux=0;sap_bkg=0;}
{sapBkg2}                      {strtok(yytext,":");ch=strtok(NULL," ");sap_bkg=atof(ch);total=total+(sap_flux/(sap_bkg+sap_flux));sap_flux=0;sap_bkg=0;}
{sapBkg3}                      {strtok(yytext,":");ch=strtok(NULL,"e");sap_bkg=atof(ch);ch=strtok(NULL,"E");
                              power=atoi(ch);sap_bkg=sap_bkg*pow(10,power);total=total+(sap_flux/(sap_bkg+sap_flux));sap_flux=0;sap_bkg=0;}
{mom_cen}                     {strtok(yytext,":");ch=strtok(NULL,"E");dist=atof(ch);ch=strtok(NULL,"E");
                              power=atoi(ch);dist=dist*pow(10,power)*pow(10,6);total_dist=total_dist+(dist*3600);dist=0;power=0;}
{mom_cen2}                     {strtok(yytext,":");ch=strtok(NULL," ");dist=atof(ch);total_dist=total_dist+(dist*3600*pow(10,6));dist=0;}
{mom_cen3}                     {strtok(yytext,":");ch=strtok(NULL,"e");dist=atof(ch);ch=strtok(NULL,"E");
                              power=atoi(ch);dist=dist*pow(10,power)*pow(10,6);total_dist=total_dist+(dist*3600);dist=0;power=0;}
{END}                         {radius = total/record;per_large=(((radius-0.009160316)/radius)*100)-98;
                               entry[4]=1;entry[2]=2.7;total_dist=total_dist/record;if(total_dist>0){entry[0]=total_dist;}else{entry[4]=-1;}if(per_large>0){entry[1]=per_large;}else{entry[4]=-1;}if(radius>0){entry[3]=radius;}else{entry[4]=-1;}if(entry[4]>0){for(i=0;i<3;i++){fprintf(out,"%lf,",entry[i]);}fprintf(out,"c%s,",name);fprintf(out,"%s,",file);fprintf(out,"%lf\n",entry[3]);}fclose(out);exit(0);}
{others}                      {printf("");}
{other2}                      {printf("");}
{other3}                      {printf("");}


%%
main(int argc,char **argv)
{


out = fopen("output1.csv","a+");
if(argc>1)
{
strcpy(filename,argv[1]);
yyin=fopen(argv[1],"r");
name=argv[2];
file=strtok(filename,".");
}
else
yyin=stdin;
yylex();

printf("\n");

}
int yywrap()
{
return 0;
}
