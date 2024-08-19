Feature: Login With Different Credentials And Verify Error Messages

  @TC1
  Scenario: Navigate To Sauce Demo Login Page And Login With Wrong username And Passwords Assert The Error Message

    Given I Open The Browser
    When  I Navigate To Url
    And   I Enter Wrong User Name And Password
    Then  I Receive Wrong User name Error Message


    @TC2
    Scenario: Navigate To Sauce Demo Login Page And Login With only username then Assert The Error Message
      Given I Open The Browser
      When  I Navigate To Url
      And   I Enter Only User Name
      Then  I Receive Missing Password is Required Error Message

  @TC3
  Scenario: Navigate To Sauce Demo Login Page And Login With only Password then Assert The Error Message
    Given I Open The Browser
    When  I Navigate To Url
    And   I Enter Only Password
    Then  I Receive Missing User name is Required Error Message

