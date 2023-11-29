Feature: Validating Playlist API

Scenario: Verify if create playlist is working
Given create playlist payload
When user calls with "POST" http request
Then the API call executed with status code 201

Scenario: Verify if fetch playlist functionality is working

	Given Get a playlist Payload
	When user calls with http request
	Then the API call got success with status code 200
	
Scenario: Verify if update playlist functionality is working

Given Get update playlist Payload
When user calls with PUT http request
Then the API call should get success with status code 200