Feature: Creating an account

  Scenario Outline: Making an account
    Given I am at the create an account page using "<Browser>"
    When I create an account i have to fill in "<DoB>"
    And Fill in my "<FirstName>" and "<LastName>"
    And Fill in my "<Email>" and confirm it
    And Create a "<Password>" and re-enter it "<PassAgain>"
    And Agree to the terms and conditions "<Agreement>"
    And Click the confirm and join button
    Then The account is successfully created "<AccountCreated>"

    Examples:
      |Browser|    DoB   |FirstName|LastName|         Email           | Password  | PassAgain |Agreement|AccountCreated|
      |chrome |25/03/2000|  Alice  |  Wendy |alice.wendy@mailnesia.com|Test123Word|Test123Word|   false |      no      |
      |firefox|25/03/2000|  Alice  |        |alice.wendy@mailnesia.com|Test123Word|Test123Word|   true  |      no      |
      |chrome |25/03/2000|  Alice  |  Wendy |alice.wendy@mailnesia.com|Test123Word|Test124Word|   true  |      no      |
      |firefox|25/03/2000|  Alice  |  Wendy |alice.wendy@mailnesia.com|Test123Word|Test123Word|   true  |      yes     |
