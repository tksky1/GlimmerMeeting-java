#!/usr/bin/bash

NODE=(192.168.79.12 192.168.79.13)

mvn package
docker build -t backend .
docker save backend -o .tarfile

for node in "${NODE[@]}"
do
  cat .tarfile | ssh $node 'docker load'
done

rm -f .tarfile
