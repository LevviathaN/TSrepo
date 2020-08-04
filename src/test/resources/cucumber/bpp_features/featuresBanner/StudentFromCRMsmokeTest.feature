@EndToEnd @Smoke
Feature: Student from CRM Smoke Test
  Description

  @Positive #TC-547
  Scenario: Student from CRM to Banner Qualification
    #Create Student
    When I execute "Log In To Salesforce" reusable step
    And I execute "Create Student Account" reusable step
    And I execute "Create Address" reusable step
    And I execute "Get Profile ID" reusable step
    And I execute "Add Qualification" reusable step
    And I click on the "Salesforce Student Accounts link" link by JS
    And I execute "Create Opportunity" reusable step replacing some steps
    |12|I click on the "Salesforce Account Creation Save button" link|
    And I click on the "EC_OPPORTUNITY_NAME" "Salesforce link"
    And I click on the "Product Catalog Items" "Salesforce related new button"
    And I click on the "New" "Salesforce dropdown option"
    And I fill the "Salesforce Offering Text Field" field with "MD_COMMON_VALIDDATA_SFOFFERING"
    And I click on the "Salesforce Search" button
    And I "check" "Salesforce Offering Checkbox" checkbox
    And I click on the "Salesforce Add Products" button
    Then I execute "Submit Application" reusable step
    #Login to Banner and check Qualification
    And I execute "window.open()" JS code
    And I switch to window with index "2"
    When I execute "Log In To Banner" reusable step
    And I execute "Navigate To Form" reusable step replacing some steps
    |1|I fill the "Banner Go To Form" field with "SOATEST"|
    When I fill the "Banner Id Text" field with "EC_BANNER_ID"
    Then I click on the "Banner Go" button
    Then I validate text "EC_QUALIFICATION_SUBJECT" to be displayed for "Banner SOATEST Test Score description" element
    Then Attribute "title" of "Banner SOATEST Test Score Source full name" should have value "EC_QUALIFICATION_INSTITUTION"
    Then I validate text "EC_G" to be displayed for "Banner SOATEST Test Score score number" element
    Then I remember "KW_AUTO_TODAY|MM/DD/YYYY" text as "EC_TODAY" variable
    Then I validate text "EC_TODAY" to be displayed for "Banner SOATEST Test Score date" element
    Then Attribute "title" of "Banner SOATEST Test Score Admission Request" should have value "Qual used for ADM Offer"
#    Then Attribute "aria-checked" of "Banner SOATEST Equivalency Indicator checkbox" should have value "true"
    #Second page for SOATEST form
    Then I click on the "Banner SOATEST Test Scores second tab" element
    Then Attribute "title" of "Banner SOATEST Status filed value" should have value "EC_STATUS"
    Then I click on the "Banner SOATEST Test Scores third tab" element
    Then Attribute "title" of "Banner SOATEST SAT Essay ID filed value" should have value "EC_TARIFF_POINT"

#  @Positive #TC-547
#  Scenario: Student from CRM to Banner Non Demographic
#    #Create Student
#    When I execute "Log In To Salesforce" reusable step
#    And I execute "Create Student Account" reusable step with some additional steps
#    |13|I click on the "Ethnic Code Select" "Salesforce dropdown field"|
#    |14|I click on the "10~ White" "Salesforce dropdown field option"|
#    |15|I click on the "Gender Identity" "Salesforce dropdown field"|
#    |16|I click on the "01~ Yes" "Salesforce dropdown field option"|
#    |17|I click on the "Religion" "Salesforce dropdown field"|
#    |18|I click on the "02~ Buddhist" "Salesforce dropdown field option"|
#    |19|I click on the "Sexual Orientation" "Salesforce dropdown field"|
#    |20|I click on the "01~ Bisexual" "Salesforce dropdown field option"|
#    |21|I capture text data "Salesforce Student Creation Capture Ethnic Code" as "EC_ETHNIC_CODE" variable|
#    |22|I capture text data "Salesforce Student Creation Capture Gender Identity" as "EC_GENDER_IDENTITY" variable|
#    |23|I capture text data "Salesforce Student Creation Capture Religion" as "EC_RELIGION" variable|
#    |24|I capture text data "Salesforce Student Creation Capture Sexual Orientation" as "EC_SEXUAL_ORIENTATION" variable|
#    And I execute "Create Address" reusable step
#    And I execute "Get Profile ID" reusable step
#    And I execute "Create Opportunity" reusable step
#    And I click on the "EC_OPPORTUNITY_NAME" "Salesforce link"
#    And I click on the "Product Catalog Items" "Salesforce related new button"
#    And I click on the "New" "Salesforce dropdown option"
#    And I fill the "Salesforce Offering Text Field" field with "MD_COMMON_VALIDDATA_SFOFFERING"
#    And I click on the "Salesforce Search" button
#    And I "check" "Salesforce Offering Checkbox" checkbox
#    And I click on the "Salesforce Add Products" button
#    Then I execute "Submit Application" reusable step
#    #Login to Banner
#    And I execute "window.open()" JS code
#    And I switch to window with index "2"
#    When I execute "Log In To Banner" reusable step
#    And I execute "Navigate To Form" reusable step replacing some steps
#      |1|I fill the "Banner Go To Form" field with "SKASPIN"|
#    When I fill the "Banner Id Text" field with "EC_BANNER_ID"
#    Then I click on the "Banner Go" button

  @Positive #TC-547
  Scenario: Student from CRM to Banner Identification Update
    #Create Student
    When I execute "Log In To Salesforce" reusable step
    And I execute "Create Student Account" reusable step
    And I wait for "3" seconds
    And I execute "Create Address" reusable step
    And I execute "Get Profile ID" reusable step
    And I execute "Create Opportunity" reusable step
    And I click on the "EC_OPPORTUNITY_NAME" "Salesforce link"
    And I click on the "Product Catalog Items" "Salesforce related new button"
    And I click on the "New" "Salesforce dropdown option"
    And I fill the "Salesforce Offering Text Field" field with "MD_COMMON_VALIDDATA_SFOFFERING"
    And I click on the "Salesforce Search" button
    And I "check" "Salesforce Offering Checkbox" checkbox
    And I click on the "Salesforce Add Products" button
    Then I execute "Submit Application" reusable step
    #Login to Banner
    And I execute "window.open()" JS code
    And I switch to window with index "2"
    When I execute "Log In To Banner" reusable step
    And I execute "Navigate To Form" reusable step replacing some steps
      |1|I fill the "Banner Go To Form" field with "SPAIDEN"|
    When I fill the "Banner Id Text" field with "EC_BANNER_ID"
    Then I click on the "Banner Go" button
    And I wait for "2" seconds
    #Current Identification block
    Then Attribute "title" of "Banner SPAIDEN Last Name filed value" should have value "EC_AUTO_LASTNAME"
    Then Attribute "title" of "Banner SPAIDEN First Name filed value" should have value "EC_AUTO_FIRSTNAME"
    And I switch to window with index "1"
    #Edit Student
    And I click on the "Salesforce More Actions Dropdown" button
    And I click on the "Edit" "Salesforce dropdown option"
    And I set "Updated[FIRSTNAME]" text to the "First Name" "Salesforce text field"
    And I set "Updated[LASTNAME]" text to the "Last Name" "Salesforce text field"
    And I click on the "Save" "button"
    And I wait for "35" seconds
    #Login to Banner
    And I execute "window.open()" JS code
    And I switch to window with index "2"
    And I am on "http://bpp-sis-admin-test.apolloglobal.int/applicationNavigator/seamless" URL
    And I execute "Navigate To Form" reusable step replacing some steps
      |1|I fill the "Banner Go To Form" field with "SPAIDEN"|
    When I fill the "Banner Id Text" field with "EC_BANNER_ID"
    Then I click on the "Banner Go" button
    And I wait for "2" seconds
    #Current Identification block
    Then Attribute "title" of "Banner SPAIDEN Last Name filed value" should have value "EC_UPDATED_LASTNAME"
    Then Attribute "title" of "Banner SPAIDEN First Name filed value" should have value "EC_UPDATED_FIRSTNAME"
    #Alternate Identification
    When I click on the "Banner SPAIDEN Alternate Identification tab" link
    Then Attribute "title" of "Banner SPAIDEN Alternate Last Name filed value" should have value "EC_AUTO_LASTNAME"
    Then Attribute "title" of "Banner SPAIDEN Alternate First Name filed value" should have value "EC_AUTO_FIRSTNAME"



#    Then I remember "KW_AUTO_ZIPCODE|#####" text as "EC_ZIP_CODE_ONE" variable
#    Then I remember "KW_AUTO_ZIPCODE|#####" text as "EC_ZIP_CODE_TWO" variable
#    Then I remember "KW_AUTO_STREET" text as "EC_STREET_ONE" variable
#    Then I remember "KW_AUTO_STREET" text as "EC_STREET_TWO" variable
#    #Address block
#    When I click on the "Banner SPAIDEN Address tab" link
#    Then Attribute "title" of "Banner SPAIDEN Address Type field value" should have value "Billing"
#    Then Attribute "title" of "Banner SPAIDEN Street Line One field value" should have value "EC_STREET_ONE"
#    Then Attribute "title" of "Banner SPAIDEN City field value" should have value "Ottawa"
#    Then Attribute "title" of "Banner SPAIDEN ZIP Code field value" should have value "EC_ZIP_CODE_ONE"
#    Then Attribute "title" of "Banner SPAIDEN Nation field value" should have value "Canada"
#    When I click on the "Banner Next Page" element
#    Then Attribute "title" of "Banner SPAIDEN Address Type field value" should have value "Mailing"
#    Then Attribute "title" of "Banner SPAIDEN Street Line One field value" should have value "EC_STREET_TWO"
#    Then Attribute "title" of "Banner SPAIDEN City field value" should have value "Manchester"
#    Then Attribute "title" of "Banner SPAIDEN ZIP Code field value" should have value "EC_ZIP_CODE_TWO"
#    Then Attribute "title" of "Banner SPAIDEN Nation field value" should have value "United Kingdom"
#

#
#    #Create Address
#    And I execute "Create Address" reusable step replacing some steps
#      |3|I click on the "Billing" "option"|
#      |4|I set "EC_STREET_ONE" text to the "Address Line 1" "Salesforce text field"|
#      |5|I set "Ottawa" text to the "City" "Salesforce text field"|
#      |6|I set "EC_ZIP_CODE_ONE" text to the "Postal Code" "Salesforce text field"|
#      |7|I set "Canada" text to the "Country" "Salesforce search field"|
#      |9|I click on the "Canada" "option"|
#    And I wait for "3" seconds
#    And I execute "Create Address" reusable step replacing some steps
#      |3|I click on the "Mailing" "option"|
#      |4|I set "EC_STREET_TWO" text to the "Address Line 1" "Salesforce text field"|
#      |5|I set "Manchester" text to the "City" "Salesforce text field"|
#      |6|I set "EC_ZIP_CODE_TWO" text to the "Postal Code" "Salesforce text field"|
#      |7|I set "United Kingdom" text to the "Country" "Salesforce search field"|
#      |9|I click on the "Kingdom" "option"|
