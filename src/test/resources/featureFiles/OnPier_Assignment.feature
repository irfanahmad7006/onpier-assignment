Feature: Task Automation framework for Onpier application

  Scenario: User fill the form with correct data (Happy Path for a private person)
    Given User is on Onpier home page with title "THG Prämie"
    And User selects the vehicle class type "E-PKW"
    And User selects the priemium model "Sofort-Prämie"
    When User uploads vehicle registration details and click next button
    And User selects a radio button based on "Privatperson"
    Then User selects salutation based on "herr"
    And User enters "John" as first name and "Smith" as last name and email id "test.sample@opier.de"
    And User enters bank details "John Smith" owner name and "DE21 1234 5698 7654 3210" iban

  Scenario: User fill the form with incorrect data (Negative scenrio for private person)
    Given User is on Onpier home page with title "THG Prämie"
    And User selects the vehicle class type "E-PKW"
    And User selects the priemium model "Sofort-Prämie"
    When User uploads vehicle registration details and click next button
    And User selects a radio button based on "Privatperson"
    Then User selects salutation based on "herr"
    And User enters "1234" as first name and "1234" as last name and email id "12345"
    And User enters bank details "123456" owner name and "12345678" iban
    And User verifies the first name "firstNameErrorMsg"
    And User verifies the last name "lastNameErrorMsg"
    And User verifies the email "emailErrorMsg"
    And User verifies the account owner "accountOwnErrorMsg"
    And User verifies the IBAN "iBanErrorMsg"

  Scenario: User fill the form with correct data (Happy scenario for company)
    Given User is on Onpier home page with title "THG Prämie"
    And User selects the vehicle class type "E-PKW"
    And User selects the priemium model "Sofort-Prämie"
    When User uploads vehicle registration details and click next button
    And User selects a radio button based on "Unternehmen"
    Then User selects salutation based on "herr"
    And User enters Company name "Onpier GmbH" and VAT "DE 123456789"
    And User enters "John" as first name and "Smith" as last name and email id "test.sample@gmail.com"
    And User enters bank details "John Smith" owner name and "DE21 1234 5698 7654 3210" iban

  Scenario: User fill the form with incorrect data (Negative scenario for company)
    Given User is on Onpier home page with title "THG Prämie"
    And User selects the vehicle class type "E-PKW"
    And User selects the priemium model "Sofort-Prämie"
    When User uploads vehicle registration details and click next button
    And User selects a radio button based on "Unternehmen"
    Then User selects salutation based on "herr"
    And User enters Company name "" and VAT "12345"
    And User enters "1234" as first name and "1234" as last name and email id "test"
    And User enters bank details "12356" owner name and "12345" iban
    And User verifies the company name "companyNameErrorMsg"
    And User verifies the vat "vatErrorMsg"
    And User verifies the first name "firstNameErrorMsg"
    And User verifies the last name "lastNameErrorMsg"
    And User verifies the email "emailErrorMsg"
    And User verifies the account owner "accountOwnErrorMsg"
    And User verifies the IBAN "iBanErrorMsg"