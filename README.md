# sample-dropwizard-rest-stub #
This project is intended to demonstrate how to create RESTful server stub with Dropwizard as well as other  All topics that this project is used for are:
* <a href="http://automationrhapsody.com/build-a-rest-stub-server-with-dropwizard/">Build a RESTful stub server with Dropwizard</a>
* <a href="http://automationrhapsody.com/build-dropwizard-project-gradle/" target="_blank">Build a Dropwizard project with Gradle</a>

## Build ##
 * Open cmd.exe and navigate to project's root folder.
 * Build project and package JAR with following command

`gradlew clean build shadowJar`

## Run ##
Run JAR with following command

`java -jar build/sample-dropwizard-rest-stub.jar server config.yml`

You can run project directly from Gradle with following command

`gradlew run`

## Functionalities ##

There are several functionalities implemented in the stub. See linked posts for more details how to use them.

### Persons ###

Database where you can add, get or remove persons with JSON showing RESTful web services functionality described in <a href="http://automationrhapsody.com/build-a-rest-stub-server-with-dropwizard/" target="_blank">Build a RESTful stub server with Dropwizard</a> post. GET endpoints are: http://localhost:9000/person/all, http://localhost:9000/person/get/{id}, http://localhost:9000/person/remove. POST endpoint is: http://localhost:9000/person/save.