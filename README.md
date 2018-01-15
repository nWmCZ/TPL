# The TPL project

## Requirements
- Git
- Gradle

## Clone
- cd /opt/
- git clone https://github.com/nWmCZ/TPL.git
- cd /opt/TPL

## Build JAR
- gradle clean build

## Run JAR
- java -jar /opt/TPL/build/libs/TPL-0.0.1-SNAPSHOT.jar

## Edit your application properties
- fssf.inDir = for ECP fssf channel
- fssf.outDir = for ECP fssf channel
- fssf.outAckDir = for storing outgoing ACKs

## Run docker container
- docker run -d -p 8080:8080 -v /root/work/tpl/application.properties:/tpl/application.properties novst/tpl

## Labeling
- http://label-schema.org/rc1/

## MicroBadges
- https://microbadger.com/

## Hooks and automated build
- https://docs.docker.com/docker-hub/builds/#create-an-automated-build
- https://docs.docker.com/docker-cloud/builds/advanced/#custom-build-phase-hooks
