#! /bin/sh

docker save mauxkproducer > tmp_mauxkproducer.tar
microk8s ctr image import tmp_mauxkproducer.tar
rm -f tmp_mauxkproducer.tar

microk8s ctr images ls | grep mauxkproducer


