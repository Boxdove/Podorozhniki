Narrative:
In order to use all of the system's features
As a new user
I want to register myself in the system

Scenario: 	Successful registration of user.
Given main page of podorozniki
And registration page of podorozniki
When user has entered required information 
And user has clicked on the checkbox
And user has pushed confirm button
Then user has to see user name
And user has to see logout button


Scenario: Registration of user with existed login
Given main page of podorozniki
And registration page of podorozniki
When user has entered required information 
And user has clicked on the checkbox
And user has pushed confirm button
Then user user has to see error message
And user is deleted from database


