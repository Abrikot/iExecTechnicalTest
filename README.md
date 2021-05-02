# iExecTechnicalTest

[![Build Status](https://travis-ci.com/Abrikot/iExecTechnicalTest.svg?branch=master)](https://travis-ci.com/Abrikot/iExecTechnicalTest)

## Overview

This project presents the result of the technical test offer by iExec. It is composed of a few steps, some of them being
optional.

The following features are currently available:

1. An API allows to create tasks and retrieve the count of tasks.
2. These tasks are stored in a database.

The following features are still to be added:

1. Adding a new endpoint GET /blockchain/tasks/count allowing to call the taskCount() function of a Smart-Contract
   already deployed on Goerli.
2. Adding a new endpoint POST /blockchain/tasks allowing to trigger the newTask() function on the same Smart-Contract.
   The BlockchainTask (with transaction ID and date) should be stored in MongoDB in a dedicated collection (
   BlockchainTask collection).

Besides, a bunch of tools has been set up, so it's easier to build and deploy:

- [a GitHub repository](https://github.com/Abrikot/iExecTechnicalTest);
- [a Docker repository](https://hub.docker.com/r/abrikot/iexec-technical-test/tags?page=1&ordering=last_updated);
- a Travis CI job to automatically build and upload a Docker image on every commit.

## Building & running

The project has been thought to be docker-izable, i.e. it can easily be started as a Docker container. It can also be
started as a more traditional Java application. Use the version that fits the most your use cases!

Before running the app, some properties need to be defined.

### Defining the properties

The application supports three ways to define some properties:

- Using variable environments as screaming snake case (e.g. `DB_NAME`);
- Using JVM options as screaming snake case (e.g. `DB_NAME`).
- Using Java application properties as lowercase separated by points (e.g. `db.name`). The first is most significant
  than the second, meaning that if a property is defined in both way, it will take the value of the variable
  environment.

The following properties can be defined to customize the application:

- DB_HOST: host name of the database. Default to `127.0.0.1`.
- DB_NAME: database name. Default to `iexec`.
- DB_PORT: database port. Default to `27017`.
- BLOCKCHAIN_ID: used blockchain id. Default to `5` (Goerli Tesnet blockchain id).
- BLOCKCHAIN_CONTRACT_TASKCOUNT_URL: TaskCount contract url. Default to `0xf837B595Fb53B3e8a1feBE0846d8a0e53f44e72a`.
- BLOCKCHAIN_PRIVATE_KEY: private key to use the blockchain. No default value so it should always be provided.
- BLOCKCHAIN_GAS_PRICE: gas price on the blockchain. Default to `2000000000`.
- BLOCKCHAIN_GAS_LIMIT: gas limit on the blockchain. Default to `1000000000`.

### Running as traditional Java application

#### How to build the traditional application

The build is done using gradle. It is as simple as:

```shell
# If Gradle is installed globally:
gradle assemble

# If Gradle is installed locally:
./gradlew assemble
```

#### How to run the traditional application

The application requires a MongoDB server running, either locally or remotely. Do not forget to provide your private
key.

```shell
# Running the application with a locally running MongoDB server:
java -DBLOCKCHAIN_PRIVATE_KEY=${PRIVATE_KEY} -jar build/libs/iexec-0.0.1-SNAPSHOT.jar

# Running the application with a remotely running MongoDB server:
java -DBLOCKCHAIN_PRIVATE_KEY=${PRIVATE_KEY} -DDB_HOST=${REMOTE_HOST} -jar build/libs/iexec-0.0.1-SNAPSHOT.jar
```

### Running as a Docker container

#### How to build the Docker image

The application still has to be built before being dockerized:

```shell
# If Gradle is installed globally:
gradle assemble

# If Gradle is installed locally:
./gradlew assemble
```

The Docker image is then built using the `Dockerfile` provided at the root of the repository:

```shell
docker build -t iexec .
```

#### How to run the Docker image

When running this project in a Docker container, it should be linked to a dockerized MongoDB instance. Do not forget to
provide your private key.

```shell
docker run --name mongo -p 27017:27017 -d -t mongo
docker run --rm --name iexec --link mongo --env BLOCKCHAIN_PRIVATE_KEY=${PRIVATE_KEY} -p 8080:8080 -t iexec
```

Note that you can change the MongoDB container name. In this case, you'll have to overload the `DB_HOST` environment
variable and to change the `link` parameter:

```shell
docker run --name ${MONGO_CONTAINER_NAME} -p 27017:27017 -d -t mongo
docker run --rm --name iexec --link ${MONGO_CONTAINER_NAME} --env DB_HOST=${MONGO_CONTAINER_NAME} --env BLOCKCHAIN_PRIVATE_KEY=${PRIVATE_KEY} -p 8080:8080 -t iexec
```