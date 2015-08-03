#include <mraa.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>

int i2c_n_exists(int n) {
    int r;
    char fnamebuf[16];
    struct stat statbuf;

    if (n < 0 || n > 10) {
	return 0;
    }
    snprintf(fnamebuf, 15, "/dev/i2c-%d", n);
    r = stat(fnamebuf, &statbuf);
    if (r == 0) {
	return 1;
    }
    return 0;
}

int main(void)
{
    mraa_i2c_context i2c;
    mraa_result_t r;

    int ns[] = {0, 6, -1};
    int i; 
    
    for (i=0; ns[i] > 0; ++i) {
	if (i2c_n_exists(ns[i])) {
	    i2c = mraa_i2c_init(ns[i]);
	}
    }

}
