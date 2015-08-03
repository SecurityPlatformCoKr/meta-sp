#!/bin/make

DESTDIR ?= ""

all:  ii2c

ii2c: ii2c.c
	${CC} -o ii2c ii2c.c -lmraa

install: ii2c
	install -d ${DESTDIR}/usr/bin
	install -m 0755 ii2c ${DESTDIR}/usr/bin/
