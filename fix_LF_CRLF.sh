#!/bin/sh
git status --untracked-files=all -s | grep "^??" | cut -c 4- | while read line; do dos2unix -D "${line}"; done
git status --untracked-files=all -s | grep "^ M" | cut -c 4- | while read line; do dos2unix -D "${line}"; done