Feature: Order API functionalities

  Scenario: Call GET Orders API
    Given There are no orders yet
    When GET Orders API was invoked to retrieve the order list
    Then size of the order list should match
      |listSize|
      |0       |

  Scenario: Call POST Orders API
    Given Order form was prepared
    When POST Orders API was invoked
    Then response status code should be equal to CREATED
    And response should contain the Order with status PAID
    And response should contain the Product Order with quantity equals to 2
