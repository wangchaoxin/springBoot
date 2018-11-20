#!/bin/bash
docker rmi $( docker images | grep none|awk '{print $3}')
echo remove unused images
git pull
containerId=$(docker images|grep "^vms"|awk '{print $3}')
if [[ -z "$containerId" ]];then
	echo can not find container;
	exit 1; 
fi
docker build -t vms-file-sevice .
if [[ "$?" != "0" ]];then
	echo build the docker image fail;
	exit 1; 
fi

docker tag ${containerId} docker.artifactory.kaisquare.com/vms/vms-file-service
if [[ "$?" != "0" ]];then
	echo tag the docker image fail; 
	exit 1; 
fi
docker push  docker.artifactory.kaisquare.com/vms/vms-file-service
if [[ "$?" == "0" ]];then
	echo push the docker image success 
	exit 0; 
fi
