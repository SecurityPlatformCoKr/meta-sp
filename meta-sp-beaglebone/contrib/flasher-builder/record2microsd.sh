#!/bin/bash

echo "Usage: $0 image-file-name [device-name]"
if [ "$1" == "" -o "$1" == "--help" -o "$1" == "-h" ]; then
    exit
fi

ID=`id -u`
if [ ${ID} != "0" ]; then
    echo "id not root. Please run with 'sudo'. Aborting..."
    exit 1
fi

if [ "$1" == "" ]; then
    exit 1
fi

DEV=`dmesg | tail -20 | grep "Attached SCSI removable disk" | sed -e "s/[]\[]/ /g" | awk -F ' ' '{print $4}'`

echo "Recommendation: ${DEV}"

IMG=$1
if [ "$2" != "" ]; then
    DEV=$2
else
    while true; do
	read -p "Use this Device? [Y/n] " yn
	case $yn in
	    [Yy]* ) break;;
	    [Nn]* ) exit;;
	    * ) break;;
	esac
    done
fi

if [ ! -b /dev/${DEV} ]; then
    echo "ERROR: Check /dev/${DEV}"
    exit
fi

umount /dev/${DEV}p1
umount /dev/${DEV}p2

SIZE=`stat ${IMG} --printf "%s"`
echo "dd if=${IMG} | pv -s ${SIZE} | dd of=/dev/${DEV}"
dd if=${IMG} | pv -s ${SIZE} | dd of=/dev/${DEV}
