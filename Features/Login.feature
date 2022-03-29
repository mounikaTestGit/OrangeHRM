Feature: Login

@sanity
  Scenario: Successful Login with Valid Credentials

    Given User Launch Chrome Browser
    When User Opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and password as "admin"
    And Click on Login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on log out link
    Then Page Title should be "Your store. Login"
    And close browser

  @retesting
Scenario Outline: Login Data Driven

  Given User Launch Chrome Browser
  When User Opens URL "https://admin-demo.nopcommerce.com/login"
  And User enters Email as "<email>" and password as "<password>"
  And Click on Login
  Then Page Title should be "Dashboard / nopCommerce administration"
  When User click on log out link
  Then Page Title should be "Your store. Login"
  And close browser

  Examples:
    | email | password |

    |    admin@yourstore.com   |    admin |
    |   admin@yourstore.com    |   admin |
    |   admin@yourstore.com    |    admin |


