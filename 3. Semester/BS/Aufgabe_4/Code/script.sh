#!/bin/bash

make -f Makefile
./install.sh
gcc main.c
./a.out