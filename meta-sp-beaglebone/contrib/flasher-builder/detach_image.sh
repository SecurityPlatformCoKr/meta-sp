#!/bin/bash

ID=`id -u`
if [ ${ID} != "0" ]; then
    echo "id not root. Please run with 'sudo'. Aborting..."
    exit 1
fi

LOOPDEV='loop0'

if [ "$1" != "" ]; then
    LOOPDEV=$1
fi

mount | grep -q ${LOOPDEV}p1 
if [ "$?" == "0" ]; then
    umount /dev/mapper/${LOOPDEV}p1
fi
mount | grep -q ${LOOPDEV}p2
if [ "$?" == "0" ]; then
    umount /dev/mapper/${LOOPDEV}p2
fi
if [ -e /dev/mapper/${LOOPDEV}p1 ]; then
    kpartx -d /dev/${LOOPDEV}
fi

losetup -a | grep -q ${LOOPDEV}
if [ "$?" == "0" ]; then
    losetup -d /dev/${LOOPDEV}
fi

losetup -a

