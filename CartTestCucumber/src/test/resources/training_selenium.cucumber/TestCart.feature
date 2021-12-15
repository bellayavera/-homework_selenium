
Feature: Add and remove products from the cart


  Example: Add products to the cart
    When I open the litecart and add 3 items to the cart
    Then There are 3 items in the cart

  Example: Remove products from the cart
    When I open the cart page and remove all items from the cart
    Then There are 0 item in the cart

