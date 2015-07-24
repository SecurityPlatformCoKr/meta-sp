#!/bin/sh

PATH="/usr/bin:/bin:/usr/sbin"

USBSVC=`/usr/bin/connmanctl services | grep gadget_ | sed -e "s/.*gadget_/gadget_/"`

if [ "${USBSVC}" == "" ]; then
    exit 1
fi

connmanctl config ${USBSVC} --ipv4 manual 192.168.0.1 255.255.255.0 192.168.0.1
connmanctl config ${USBSVC} --autoconnect yes
connmanctl connect ${USBSVC}

