#!/bin/sh
#

echo 'Removing container gft-parking-mysql...'
docker stop 'gft-parking-mysql'
docker rm 'gft-parking-mysql'

echo 'Removing container gft-parking...'
docker stop 'gft-parking'
docker rm 'gft-parking'

IMAGE_NAME='com.gft/gft-parking:0.0.1-SNAPSHOT'

echo 'Creating MySQL image named gft-parking-mysql'
    docker run -d \
    --name gft-parking-mysql \
    -e MYSQL_ROOT_PASSWORD=root \
    -e MYSQL_DATABASE=gft-parking \
    -e MYSQL_USER=user \
    -e MYSQL_PASSWORD=user \
    -p 3306:3306 \
    mysql:latest

echo 'Waiting 15s'
sleep 15s

echo 'Docker images'
docker images

echo 'Listing container names'
docker inspect --format='{{.Name}}' $(docker ps -aq --no-trunc)

echo ''
echo 'Logs for gft-parking MySql'
docker logs gft-parking-mysql

echo 'Installing Curl...'
yum install -y curl
echo 'Downloading Flyway...'
curl -OL https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/4.2.0/flyway-commandline-4.2.0-linux-x64.tar.gz

tar -xzf flyway-commandline-4.2.0-linux-x64.tar.gz
rm flyway-commandline-4.2.0-linux-x64.tar.gz
chmod +x flyway-4.2.0/flyway

if [ "$FLYWAY_URL" = "" ]; then
	FLYWAY_URL="jdbc:mysql://localhost:3306/gft-parking?useSSL=false"
fi

echo 'Calling flyway'
flyway-4.2.0/flyway migrate -sqlMigrationPrefix=v -url=$FLYWAY_URL -user=root -password=root -locations=filesystem:/var/lib/jenkins/workspace/gft-parking/database/db/

echo 'Linking containers...'

CONTAINER_ID=`docker run \
    -p 11010:8080 \
    --name gft-parking \
    --link gft-parking-mysql:mysql \
    -e 'SPRING_PROFILES_ACTIVE=default,container' \
    -d $IMAGE_NAME`

echo 'Container ID:' $CONTAINER_ID

echo 'Waiting 30s'
sleep 30s

echo ''
echo 'Logs for gft-parking'
docker logs gft-parking
docker logs $CONTAINER_ID

echo 'Success'
