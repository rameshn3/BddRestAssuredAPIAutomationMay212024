#Author: rameshn3@gmail.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: GetAPI Functionality
 
  Scenario: Get Api to list all the users
    Given provide valid endpoint to fetch all users
    When send the request to the server
    Then I validate the response statuscode 200
   

  Scenario Outline: getApi to search with different page numbers using scenario outline
    Given provide valid endpoint to fetch all users
    When send the request to the server with page number as <page>
    Then I verify the response statusCode <status>
    And I verify the first user record having email as "<email>"

    Examples: 
      | page  | status | email  |
      | 1     | 200    | george.bluth@reqres.in |
      | 2     | 200    |michael.lawson@reqres.in|

      
 Scenario: Get Api to list single user
    Given provide valid endpoint to fetch single user
    When send the request to the server to fetch single user
    Then I validate the single user response statuscode 200
    And I verify the response body fields
    		|2|janet.weaver@reqres.in|Janet|Weaver|
      
   Scenario: Get Api for single user not found
    Given provide valid endpoint to fetch single user not found
    When send the request to the server to fetch single user not found
    Then I validate the single user not found response statuscode
    				|404|
      
      
      
      