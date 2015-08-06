include recipes-core/images/edison-image.bb
IMAGE_INSTALL += "busybox-udhcpd \
		  sp-dev-config \
		  busybox-syslog \
		  trousers tpm-tools kernel-module-tpm-i2c-atmel \
		  uhttpd \
		  luci luci-i18n-english \
		 "
