#! /bin/sh

docker save mauxkconsumer > tmp_mauxkconsumer.tar
microk8s ctr image import tmp_mauxkconsumer.tar
rm -f tmp_mauxkconsumer.tar

microk8s ctr images ls | grep mauxkconsumer


