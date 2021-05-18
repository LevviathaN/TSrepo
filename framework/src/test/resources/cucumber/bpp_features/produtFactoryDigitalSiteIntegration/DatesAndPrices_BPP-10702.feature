Feature: Dates & Prices - Dates and Prices 'pods' (front-end) component
  As a admin user
  I want to be able to view the "dates & prices" component in the same page with the marketing data
  so that the user can read the information for a particular course and navigate through the available papers.

  @Runn
  Scenario: Add Dates n Prices Component to CMS Page
    Given I execute "Log In to Hub as Admin" reusable step
    And I am on "https://admin-stage-bppdigital.bppuniversity.com/admin/cms/pages/2752/edit" URL
#    And I wait for "7" seconds
    # Add Accordion Component:
    When I click on the "Add component" "BPP Digital Search Label Button"
    And I click on the "Dates And Prices" "BPP Digital Admin Sub Menu Item links"
    And I click on the "Body name" "BPP Digital dropdown"
    And I click on the "Institute Chartered Accountants of England & Wales (ICAEW)" "BPP Digital dropdown option"
    And I click on the "Level" "BPP Digital dropdown"
    And I click on the "Professional Level" "BPP Digital dropdown option"
    And I click on the "Save" "element by value"
    And I wait for "3" seconds
    And I am on "http://web-stage-bppdigital.bppuniversity.com/courses/autodatesandprices" URL

    # Verify Papers
    And I should see the "Audit & Assurance" "BPP Digital View Dates and Prices button"
    And I should see the "Financial Accounting and Reporting" "BPP Digital View Dates and Prices button"
    And I should see the "Financial Management" "BPP Digital View Dates and Prices button"
    And I should see the "Tax Compliance" "BPP Digital View Dates and Prices button"

    And I click on the "Financial Management" "BPP Digital View Dates and Prices button"
    And I click on the "Wed 17 Mar 2021" "element"
    And I should see the "Wed 17 March" "element"
    And I should see the "Thu 18 March" "element"
    And I should see the "Fri 19 March" "element"
    And I should see the "Mon 22 March" "element"
    And I validate text "CONTAINS=4" to be displayed for "BPP Digital Dates and Prices Number of Sessions field" element
    And I wait for "3" seconds

    # Verify Paper Filters
    And I hover over the "BPP Digital Dates and Prices Papers dropdown" button
    And I click on the "Tax Compliance" "BPP Digital dropdown item"
    And I should see the "Thu 25 Mar 2021" "element"
    And I should see the "Tue 30 Mar 2021" "element"
    And I should see the "Sat 15 May 2021" "element"

    # Log in to Product Factory UAT
    Given I am on "MD_COMMON_LINKS_PRODUCTFACTORYURLNEW" URL
    When I click on the "Sign in with Auth0" button
    And I fill the "Product Factory Email" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYUSER"
    And I fill the "Product Factory Password" field with "MD_COMMON_CREDENTIALS_PRODUCTFACTORYPASSWORD"
    And I click on the "Product Factory Login" button

    # Amend Body
#    When I click on the "Programme" "Product Factory navigation item"
#    When I click on the "Bodies" "Product Factory navigation sub item"
#    And I click on the "ICAEW" "Product Factory edit button"
#    And I set "ICAEWU" text to the "Short Name" "Product Factory text field"
#    And I set "Institute Chartered Accountants of England & Wales Updated" text to the "Name" "Product Factory text field"
#    And I click on the "Save" "Product Factory button"

    # Find Course in Product factory
    When I click on the "Products" "Product Factory navigation item"
    When I click on the "Courses" "Product Factory navigation sub item"
    And I click on the "Additional Filters" "button"
    And I click on the "Body" "Product Factory dropdown"
    And I click on the "ICAEW - Institute Chartered Accountants of England & Wales" "Product Factory dropdown option"
    And I click on the "Level" "Product Factory dropdown"
    And I click on the "Professional Level - Professional Level" "Product Factory dropdown option"
    And I click on the "CourseType" "Product Factory dropdown"
    And I click on the "Revision - OCR Live" "Product Factory dropdown option"
    And I set "Tax Compliance" text to the "Paper" "Product Factory text field"
    And I click on the "Done" "Product Factory button"
    And I click on the "ICAEW" "Product Factory edit button"

    # Cleanup
    And I am on "https://admin-stage-bppdigital.bppuniversity.com/admin/cms/pages/2752/edit" URL
    And I click on the "Dates and prices" "BPP Digital Admin Pages Delete Button for Specific Component name"
    And I wait for "3" seconds
    
    # Verify Body Short Name changed in Admin
#    When I click on the "Add component" "BPP Digital Search Label Button"
#    And I click on the "Dates And Prices" "BPP Digital Admin Sub Menu Item links"
#    And I click on the "Body name" "BPP Digital dropdown"
#    Then I should see the "Institute Chartered Accountants of England & Wales Updated (ICAEWU)" "BPP Digital dropdown option"

    # Verify absence of papers
    When I click on the "Add component" "BPP Digital Search Label Button"
    And I click on the "Dates And Prices" "BPP Digital Admin Sub Menu Item links"
    And I click on the "Body name" "BPP Digital dropdown"
    And I click on the "AutoBodyName (AutoBodyShortName)" "BPP Digital dropdown option"
    And I click on the "Level" "BPP Digital dropdown"
    And I click on the "AutoLevelName" "BPP Digital dropdown option"
    And I click on the "Save" "element by value"
    And I wait for "3" seconds
    And I am on "http://web-stage-bppdigital.bppuniversity.com/courses/autodatesandprices" URL
    Then I should see the "No papers are available at this time." "exact element"

    # Cleanup
    And I am on "https://admin-stage-bppdigital.bppuniversity.com/admin/cms/pages/2752/edit" URL
    And I click on the "Dates and prices" "BPP Digital Admin Pages Delete Button for Specific Component name"
    And I wait for "3" seconds

    # Revert body name changes in Product Factory
#    When I click on the "Programme" "Product Factory navigation item"
#    When I click on the "Bodies" "Product Factory navigation sub item"
#    And I click on the "ICAEWU" "Product Factory edit button"
#    And I set "ICAEW" text to the "Short Name" "Product Factory text field"
#    And I set "Institute Chartered Accountants of England & Wales" text to the "Name" "Product Factory text field"
#    And I click on the "Save" "Product Factory button"