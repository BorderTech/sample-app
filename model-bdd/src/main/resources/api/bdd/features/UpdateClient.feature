Feature: Update client details
  User wants to update a client

Background: Client service available
  Given A client update service is available

Scenario: Update a client
  When User retrieves client "ORG1"
   And User updates client name to "foo"
   And User submits client "ORG1"
  Then User gets updated client "ORG1"

Scenario: Update a client with validation error
  When User retrieves client "ORG1"
   And User updates client name to blank
   And User submits client "ORG1"
  Then User gets validation exception for "ORG1"

Scenario: Update an invalid client
  When User submits a client that does not exist
  Then User gets client not found exception for update

Scenario: Update a client has a system error
  When User retrieves client "ORG1"
   And User submits a client that receives a service exception
  Then User gets service exception for update
