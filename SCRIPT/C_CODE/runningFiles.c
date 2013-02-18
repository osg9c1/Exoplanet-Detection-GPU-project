#include <stdio.h>
int main ()
{

FILE *fp = fopen ( "filenames.txt", "r" );
if ( fp != NULL )
{
char line [ 40 ]; 
while ( fgets ( line, sizeof line, file ) != NULL ) /* read a line */
{
//convert(line);
fputs ( line, stdout ); /* write the line */
}
fclose ( file );
}
else
{
printf("error in file opening");
}
return 0;
}
