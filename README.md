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

By default the Web UI uses a Mocked version of the service layer. This can be overriden to use a REST Client implementation.

Follow these steps to make the Web UI call the local instance of the REST Services:

1. cd web-lde
2. copy example_local_app.properties to local_app.properties
3. Uncomment the BT_WEB_ENV=LOCAL
4. Run Web UI
5. Run REST Service

##### Environment Variables

Environment Variables can also be used to make the Web UI use a REST client implementation.

To use the REST Client Implementation:

``` java
SET BT_WEB_ENV=LOCAL
```

To override the default REST endpoint `http://localhost:8082/lde/api`:

``` java
SET BT_APP_REST_URI=http://myhost:8082/myapp/api
```

Note - These properties can also be set as system properties.

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
