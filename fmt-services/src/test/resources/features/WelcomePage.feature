Feature: Welcome Page Features

  Scenario: User navigates to the System's main page
    Given The system is up and running
    When A user navigates to the home page
    Then The user receives the welcome message "Welcome to the Feedback Management Tool"
