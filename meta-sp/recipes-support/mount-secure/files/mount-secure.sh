#!/bin/sh

ECRYPTFSOPT=""
if [ -e /etc/ecryptfs-opt.conf ]; then
        . /etc/ecryptfs-opt.conf
fi

if [ x"$ECRYPTFSOPT" != "x" ]; then
        mount -t ecryptfs -o ${ECRYPTFSOPT} /secure /secure
fi

