
@tag
Feature: Purchase the order from Ecommerce Website
	I want to use this template for my feature file
	
	Background:
	Given I landed on Ecommerce Page
	
 	@Regression
 	Scenario Outline: Positive Test of submitting the order
 		Given Logged in with username <username> and password <password>
 		When I add product <productName> to Cart
 		And Checkout <productName> and submit the order 
 		Then "Thankyou for the order." message displayed on the confirmation page 		
 	
 		Examples:
 			| username					| password | productName |
 			| pramoddshirale@gmail.com	| P@ssw0rd | ZARA COAT 3 |