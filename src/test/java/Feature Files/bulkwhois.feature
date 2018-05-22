Feature: Automate BULKWHOIS website

  Background:
    Given User Launch the browser


  Scenario: Extract data and write into File for bulkwhois
    Given User goto bulkwhois URL of application
    And read data from file for bulkwhois
