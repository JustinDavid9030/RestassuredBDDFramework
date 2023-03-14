Feature: Validating login function

#Scenario: Verify login to G2 Qa4 portal using 
#	Given User is on g2 qa portal
#	  And User login using "username" and "password" with post http requesst
    #When login is successful with status code 200
    #Then "status" code in response body is "OK"
    
Scenario: Complete Receiving for a lot
	Given User got the access token by loging in
	When User want to complete receiving for a "79778823"
	Then Lot Stage is updated