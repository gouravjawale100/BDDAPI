Feature: Create stepdefinitions method
Scenario: Verify if update playlist functionality is working
Given Get update playlist Payload
When user calls with PUT http request
Then the API call should get success with status code 200