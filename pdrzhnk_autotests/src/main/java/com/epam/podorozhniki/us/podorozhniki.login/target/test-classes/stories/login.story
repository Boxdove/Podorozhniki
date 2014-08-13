Narrative:
In order to use all of the system's features
As a user
I want to login into the system


Scenario: 	Test succesful login into the podorozhniki for registered user.
Given main page http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main
When user has entered login and password into login form
And user has pushed login button
Then user has to see logout button
Then user has to see username 

Scenario: 	Test failed login into the podorozhniki for registered user.
Given main page http://evbyminsd7238.minsk.epam.com:8080/pdrzh/main
When user has entered incorrect login and password into login form
And user has pushed login button
Then user has to see message in the error block



