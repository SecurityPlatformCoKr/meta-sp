#!/bin/sh

if [ -e /etc/sp-init-complete ]; then
    exit 0
fi

echo 'OPTIONS="-R @SPSyslogServer@:514"' >> /etc/default/busybox-syslog
sed -i -e "s/#ForwardToSyslog/ForwardToSyslog/" /etc/systemd/journald.conf

touch /etc/sp-init-complete
