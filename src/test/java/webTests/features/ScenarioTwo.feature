Feature: Login With Valid Credentials And Complete Purchase Process

  Scenario: Navigate To Sauce Demo Login Page And Login With Correct username And Passwords Then Assert That Products Appeared

    Given I Open The Browser
    When  I Navigate To Url
    And   I Enter Correct User Name And Password
    Then  I Should Land Products Page And Products Appear
    When I Click Filter Icon
    And I Click Add To Cart
    Then Shopping Cart Will Have Red Icon With Total Added Products
    When I Click Shopping Cart Icon
    Then I Should Land Cart Page
    And I Should Found The Products I chose in Products page
    When I Click Checkout Button
    Then I Should Land Checkout: Your Information
    When I Fill My information
    And I click Continue
    Then I Should Land Checkout: Overview
    And I Should Find Items Total Price Without Taxes
    And I Should Find Url Matches checkout-step-two.html
    When I Click Finish
    Then I Should Land Checkout Complete Page
    And I Should See Thank You Message



