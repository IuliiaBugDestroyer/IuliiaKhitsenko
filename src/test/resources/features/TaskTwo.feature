Feature: Table Page

  Background:
    Given I open JDI GitHub site
    And I login with the username "Roman" and password "Jdi1234"
    When I go to User Table page from Service dropdown
    Then "User Table" page should be opened

  Scenario: User Table Page test
And 6 Number Type Dropdowns should be displayed on Users Table on User Table Page
And 6 Usernames should be displayed on Users Table on User Table Page
And 6 Description texts under images should be displayed on Users Table on User Table Page
And 6 checkboxes should be displayed on Users Table on User Table Page
And User table should contain following values:
  | Number | User             | Description                      |
  | 1      | Roman            | Wolverine                        |
  | 2      | Sergey Ivan      | Spider Man                       |
  | 3      | Vladzimir        | Punisher                         |
  | 4      | Helen Bennett    | Captain America some description |
  | 5      | Yoshi Tannamuri  | Cyclope some description         |
  | 6      | Giovanni Rovelli | Hulk some description            |
And droplist should contain values in column Type for user Roman
  | Dropdown Values |
  | Admin           |
  | User            |
  | Manager         |

  Scenario: Vip checkbox logs
    When I select vip checkbox for "Sergey Ivan"
    Then 1 log row has "Vip: condition changed to true" text in log section
