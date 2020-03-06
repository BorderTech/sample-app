# Sample Application

Sample application demonstrates how a project can be structured with RESTful services and a Web UI.

This project can be cloned as a template.

# Build and Run

## Clone and Build project

Follow these commands to fetch the source and build:

1. git clone https://github.com/BorderTech/sample-app.git my-dir (first time only)
2. cd my-dir
3. mvn install -Pquick-build (Quick build profile has QA and Tests turned off)

## Run REST Service

Follow these commands to run a local instance of the REST Services:

1. cd app-lde
2. mvn lde-exec:run
3. Access swagger ui at http://localhost:8082/lde/launchswagger

## Run Web UI

Follow these commands to run a local instance of the Web UI:

1. cd web-lde
2. mvn lde-exec:run
3. Access web ui at http://localhost:8081/lde/app

### Change Web UI to Use Local REST Services

By default the Web UI uses a Mocked version of the service layer.

Follow these steps to make the Web UI call the local instance of the REST Services:

1. cd web-lde
2. copy example_local_app.properties to local_app.properties
3. Uncomment the property bordertech.config.environment=LOCAL
4. Run Web UI
5. Run REST Service
