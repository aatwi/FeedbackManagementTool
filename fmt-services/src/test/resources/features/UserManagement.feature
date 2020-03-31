Feature: User Management

  Scenario: John Doe sends a request to create an account
    When The user inputs the required information "John Doe" john.doe@email.com john_password
    Then An account should be created in the system
    And The user should receive a Success notification

