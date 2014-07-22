Narrative:
In order to use all of the system's features
As a user
I want to verify presence of routes on the main page


Scenario: 	Verifying of presence of routes' list on the main page
Given main page http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main
When user logs into system
Then user should see valid list of routes
