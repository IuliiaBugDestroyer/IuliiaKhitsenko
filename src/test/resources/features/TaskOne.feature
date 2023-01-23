Feature: Different Elements Page logs

  Scenario: All moves are logged on Different Elements Page
    Given I open JDI GitHub site
    Then I login with the username "Roman" and password "Jdi1234"
    When I open Different Elements Page from Service dropdown in header
    And I click on "Water" and "Wind" checkboxes
    And I click on "Selen" radiobutton
    And I select "Yellow" color in dropdown
    Then Checkbox Water should be selected and logged
    And Checkbox Wind should be selected and logged
    And Radiobutton Selen should be selected and logged
    And Dropdown Yellow should be selected and logged
