#!/bin/sh

if [ -e /etc/sp-init-complete ]; then
    exit 0
fi

echo 'OPTIONS="-R 192.168.2.16:514"' >> /etc/default/busybox-syslog
sed -i -e "s/#ForwardToSyslog/ForwardToSyslog/" /etc/systemd/journald.conf

touch /etc/sp-init-complete
