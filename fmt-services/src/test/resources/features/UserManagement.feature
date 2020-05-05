Feature: User Management

  Scenario Outline: Verify Create Account Functionality
    Given The user is on the Create New Account page
    When The user inputs the required information "<name>" "<email>" "<password>"
    Then The system should try to create an account
    And The user should receive a notification with response "<response>"

    Examples:
      | name     | email              | password      | response              |
      | John Doe | john.doe@email.com | john_password | Created               |
      | Foo Bar  | john.doe@email.com | foo_password  | Internal Server Error |

