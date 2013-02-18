/*
 * This program displays the names of all files in the current directory.
 */

#include <dirent.h> 
#include <stdio.h> 

int main(void)
{
  DIR           *d;
  struct dirent *dir;
  d = opendir("Untitled Folder");
 FILE *fp;
fp=fopen("filenames.txt","w");
  if (d)
  {
    while ((dir = readdir(d)) != NULL)
    { fprintf(fp,"%s\n",dir->d_name);
      printf("%s\n", dir->d_name);
    }

    closedir(d);
  }
  fclose(fp);

  return(0);
}
