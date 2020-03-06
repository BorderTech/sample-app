Feature: Retrieve application
  User wants to retrieve an application

Background: Retrieve application available
  Given A retrieve application service is available

Scenario: Retrieve a valid application
  When User retrieves application "testid"
  Then User gets "testid" application

Scenario: Retrieve an invalid application
  When User retrieves application "error"
  Then User gets application exception for "error"
