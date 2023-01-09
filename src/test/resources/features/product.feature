Feature: Product API functionalities

  Scenario: Call GET Products API
    When GET Products API was invoked to retrieve product list
    Then size of the product list should match
      |listSize|
      |7       |
    And the list should contain the products
      | productName  |
      | TV Set       |
      | Game Console |
      | Sofa         |
      | Icecream     |
      | Beer         |
      | Phone        |
      | Watch        |

  Scenario Outline: Call GET Product API passing Product Id
    When Product Id <productID> is passed in the API call to retrieve the product
    Then Product id <productID> and Name <productName> should match the following values
    Examples:
      | productID | productName  |
      | 1         | TV Set       |
      | 2         | Game Console |
      | 3         | Sofa         |
