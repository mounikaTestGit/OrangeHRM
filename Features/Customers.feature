Feature: Customers

  Background: Below are common steps for every scenario
    Given User Launch Chrome Browser
    When  User Opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and password as "admin"
    And Click on Login
    Then User can view Dashboard

  @sanity
  Scenario: Add new Customer
    When User clicks on Customers Menu
    And click on Customers Menu Item
    And click on Add new Button
    Then User can view Add new Customer Page
    When User enter customer info
    And click on save button
    Then User can view confirmation message "The new customer has been added successfully."
    And close browser

    @regression
    Scenario: Search Customer by EmailId
      When User clicks on Customers Menu
      And click on Customers Menu Item
      And Enter customer Email
      When Click on search Button
      Then User should found Email in the search table
      And close browser

      @regression
      Scenario: Search Customer by First Name and Last Name
        When User clicks on Customers Menu
        And click on Customers Menu Item
        And Enter customer FirstName
        And Enter customer LastName
        When Click on search Button
        Then User should found Name in the search table
        And close browser

