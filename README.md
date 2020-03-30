# Sample Application

Sample application demonstrates how a project can be structured with RESTful services and a Web UI.

This project can be cloned as a template.

## Status
[![Build Status](https://travis-ci.com/BorderTech/sample-app.svg?branch=master)](https://travis-ci.com/BorderTech/sample-app)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=bordertech-sample-app&metric=alert_status)](https://sonarcloud.io/dashboard?id=bordertech-sample-app)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=bordertech-sample-app&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=bordertech-sample-app)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=bordertech-sample-app&metric=coverage)](https://sonarcloud.io/dashboard?id=bordertech-sample-app)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d637639668404d609802750a9e16c155)](https://www.codacy.com/gh/BorderTech/sample-app?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=BorderTech/sample-app&amp;utm_campaign=Badge_Grade)

## Build and Run

### Clone and Build project

Follow these commands to fetch the source and build:

1. git clone https://github.com/BorderTech/sample-app.git my-dir (first time only)
2. cd my-dir
3. mvn install -Pquick-build (Quick build profile has QA and Tests turned off)

### Run REST Service

Follow these commands to run a local instance of the REST Services:

1. cd app-lde
2. mvn lde-exec:run
3. Access swagger ui at http://localhost:8082/lde/launchswagger

### Run Web UI

Follow these commands to run a local instance of the Web UI:

1. cd web-lde
2. mvn lde-exec:run
3. Access web ui at http://localhost:8081/lde/app

#### Change Web UI to Use Local REST Services

By default the Web UI uses a Mocked version of the service layer. This can be overriden to use a REST Client implementation via a property file, environment variables or system properties.

##### Property file

Follow these steps to make the Web UI call the local instance of the REST Services via a property file:

1. cd web-lde
2. copy example_local_app.properties to local_app.properties
3. Uncomment the BT_WEB_ENV=LOCAL
4. Run Web UI
5. Run REST Service

##### Environment Variables

Environment Variables can also be used to make the Web UI use a REST client implementation.

To use the REST Client Implementation, set the following variable:

``` java
SET BT_WEB_ENV=LOCAL
```

To use a different REST endpoint, set the following variable:

``` java
SET BT_APP_REST_URI=http://myhost:8082/myapp/api
```

##### System properties

The configuration of the Web UI and REST Services can also be configured via System Properties.

Running the Web UI with the REST Client Implementation:

``` java
cd web-lde
mvn lde-exec:run -DBT_WEB_ENV=LOCAL
```

Running the Web UI with the REST Client Implementation and different REST URI:

``` java
cd web-lde
mvn lde-exec:run -DBT_WEB_ENV=LOCAL -DBT_APP_REST_URI=http://localhost:8888/lde/api
```

Running the REST Services on a different Port:

``` java
cd app-lde
mvn lde-exec:run -Dbordertech.lde.port.default=8888
```

### Smoke tests

Sample command lines for running smoke tests.

#### API Test
```
mvn test -Psmoke -pl rest-server-impl -Dserver.smoke.port=8086 -Dserver.smoke.host=http://localhost -Dserver.smoke.base=/lde/api/v1/
```

#### UI Test
```
mvn test -Psmoke -pl web-ui -Dbordertech.webfriends.selenium.launchServer=false -Dbordertech.webfriends.selenium.serverUrl=http://localhost:8081/lde
```
