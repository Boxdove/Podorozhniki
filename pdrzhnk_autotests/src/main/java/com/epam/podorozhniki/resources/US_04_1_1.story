US_04_1_1: Login into the system.

Narrative:
In order to use all of the system's features
As a user
I want to login into the system

Scenario: 	Test login into the Podorozhniki system for register user .
Given main podorozhniki page
When the user has entered login information into login form
And login button are pressed
Then the user should see logout button
And the user name should be displayed above the photo field

Scenario: 	Test login into the Podorozhniki system for non-register user .
Given main podorozhniki page
When the user has entered incorrect login information into login form
And login button are pressed
Then the user should see error message under field login

