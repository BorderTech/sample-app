Feature: Retrieve client details
  User wants to retrieve a client

Background: Client service available
  Given A client retrieve service is available

Scenario: Retrieve a valid client
  When User retrieves client "ORG1"
  Then User gets "ORG1" client

Scenario: Retrieve an invalid client
  When User retrieves a client that does not exist
  Then User gets client not found exception for retrieve

Scenario: Retrieve a client has a system error
  When User retrieves client that causes a service exception
  Then User gets service exception for retrieve
