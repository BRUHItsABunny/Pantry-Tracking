FROM bms-proto-builder:latest AS builder
WORKDIR /proto

# Copy our repo
COPY . ./

# Setup general includes
ENV PROTO_INC "-I ./ \
  -I ../"

ENV PROTOC_CMD "protoc ${PROTO_INC} --java_out=./app/src/main/java/ --kotlin_out=./app/src/main/java/ ./*.proto"
# Generate files
RUN mkdir -p app/src/main/java/
RUN ${PROTOC_CMD}

# We do not want to copy these files over again
RUN rm Dockerfile pantrytracker.proto

CMD ["/bin/sh", "-c", "echo Docker done"]