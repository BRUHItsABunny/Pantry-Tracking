@echo off
docker build -t protos ./_resources/proto
docker run --name="protos_run" protos
docker cp protos_run:proto/. .
docker rm protos_run
docker rmi protos
echo "BAT done"