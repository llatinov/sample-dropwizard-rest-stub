# sample-dropwizard-rest-stub #
This project is intended to demonstrate how to create RESTful server stub with Dropwizard.
More details can be found in the original blog post <a href="http://automationrhapsody.com/build-a-rest-stub-server-with-dropwizard/">Build a RESTful stub server with Dropwizard</a>.

## Run ##
1. Install and configure Maven
2. Run "mvn package" command inside project directory
3. Run JAR with following command

	`java -jar sample-dropwizard-rest-stub-1.0-SNAPSHOT.jar server ../config.yml`

4. Service endpoints are

    	/person/all
    	/person/get/{id}
    	/person/remove
    	/person/save