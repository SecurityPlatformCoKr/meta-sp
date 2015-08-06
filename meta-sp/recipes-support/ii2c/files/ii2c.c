#include <mraa.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

#define PATH_SYS_BOARD_NAME "/sys/devices/virtual/dmi/id/board_name"

int read1line(char *path, char *buf, int len)
{
    FILE *f;
    int l;
    f = fopen(path, "r");
    if (f == NULL) {
	buf[0] = '\0';
	return -1;
    }
    l = fread(buf, 1, len, f);
    fclose(f);
    if (l <= 0) {
	return -1;
    }
    return 0;
}

int find_i2cbus(void)
{
    struct stat statbuf;
    char boardname[20];
    const char gal2 [] = "GalileoGen2";
    const char edison1[] = "BODEGA BAY";
    const char edison2[] = "SALT BAY";
    int r = stat(PATH_SYS_BOARD_NAME, &statbuf);
    if (r < 0) {
	return 0;
    }
    r = read1line(PATH_SYS_BOARD_NAME, boardname, 20);
    if (r < 0) {
	return 0;
    }
    if (strncmp(boardname, gal2, strlen(gal2)) == 0) {
	return 0;
    } else if (strncmp(boardname, edison1, strlen(edison1)) == 0) {
	return 6;
    } else if (strncmp(boardname, edison2, strlen(edison2)) == 0) {
	return 6;
    }
    return 0;
}

int main(void)
{
    int i2cbus = find_i2cbus();

    mraa_init();
    mraa_i2c_init(i2cbus);
    return 0;
}
