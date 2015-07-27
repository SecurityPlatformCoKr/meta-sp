include recipes-core/images/edison-image.bb
IMAGE_INSTALL += "busybox-udhcpd \
		  sp-dev-config \
		  busybox-syslog \
		  \
		 "
