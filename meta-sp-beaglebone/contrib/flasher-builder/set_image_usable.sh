#!/bin/bash
IMG=$1

if [ "x" == "x${IMG}" ]; then
    echo "NO image"
    exit 1
fi

ID=`id -u`
if [ ${ID} != "0" ]; then
    echo "id not root. Please run with 'sudo'. Aborting..."
    exit 1
fi

LOOP0="loop0"

gen_mappeddev() {
    LOOP0ALL=`kpartx -av ${IMG} | awk -F' ' '{print $3}'`
    if [ "$?" != "0" ]; then echo "KPARTX ${IMG} FAILED. Aborting..."; exit 1; fi
    LOOP0=`echo ${LOOP0ALL} | awk -F' ' '{print $1}' | sed -e "s/p1$//"`
    echo ${LOOP0}
}

del_mappeddev() {
    kpartx -d ${IMG}
}

gen_mappeddev

mkdir -p 1
mkdir -p 2

mount /dev/mapper/${LOOP0}p1 1
mount /dev/mapper/${LOOP0}p2 2
