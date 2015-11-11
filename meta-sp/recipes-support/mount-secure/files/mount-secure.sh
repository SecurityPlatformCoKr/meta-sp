#!/bin/sh

if [ x"$1" == "x--init" ]; then
	ecryptfs-generate-tpm-key -p 10 2> /etc/ecryptfs-opt.conf
	exit
fi

ECRYPTFSOPT=""
if [ -e /etc/ecryptfs-opt.conf ]; then
        . /etc/ecryptfs-opt.conf
fi

if [ x"$ECRYPTFSOPT" != "x" ]; then
        mount -t ecryptfs -o ${ECRYPTFSOPT} /secure /secure
fi

