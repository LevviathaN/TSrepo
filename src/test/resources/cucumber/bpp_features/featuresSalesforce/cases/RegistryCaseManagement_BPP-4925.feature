@Salesforce @RegistryCaseManagement @Cases
Feature: Registry Case Management
  As a Registry Salesforce Classic User switching to Lightning Experience,
  I want Registry Case Management to perform as expected

  @EndToEnd #TC-2535
  Scenario: Registry Case Management End To End
    Given I execute "Log In To Salesforce" reusable step
    And I execute "Create ID Card Registry Case" reusable step
    And I validate text "CONTAINS=Student Records" to be displayed for "Salesforce Case Owner Data Field" element
    And I capture text data "Salesforce Case Number Data Field" as "EC_CASENUMBER" variable
    Then I click on the "Salesforce Edit Case Status" button by JS
    And I click on the "Salesforce Case Status Dropdown" element
    And I click on the "In Progress" "Salesforce cases status option"
    And I click on the "Save" "Product Factory button title"
    And I wait for "2" seconds
    And I validate text "In Progress" to be displayed for "Salesforce Case Status Data Field" element
    Then I click on the "Salesforce Edit Case Status" button by JS
    And I click on the "Salesforce Case Status Dropdown" element
    And I click on the "Ready to Close" "Salesforce cases status option"
    And I click on the "Save" "Product Factory button title"
    And I wait for "2" seconds
    And I validate text "Ready to Close" to be displayed for "Salesforce Case Status Data Field" element

  @AssignmentRules
  Scenario: Registry Case Management Verify Case Assignment Assessments and IOS
    Given I execute "Log In To Salesforce" reusable step
    And I execute "Validate All Registry Cases Types" reusable step
    #Assesments
    And I execute "Create Assesments Registry Case" reusable step
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=School Programme Support" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 11 | I click on the "Overseas Exams" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Overseas Exams" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 11 | I click on the "Transcripts" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Exam Queries" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 11 | I click on the "Examination Date /Venue/Time" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Exam Operations" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 11 | I click on the "Feedback" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Academic Admin" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 11 | I click on the "Results" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Exam Results" to be displayed for "Salesforce Case Owner Data Field" element
     #IOS
    And I execute "Create IOS Registry Case" reusable step replacing some steps
      | 11 | I click on the "New" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Student Records" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create IOS Registry Case" reusable step
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Student Records" to be displayed for "Salesforce Case Owner Data Field" element

  @AssignmentRules
  Scenario: Registry Case Management Verify Case Assignment Attendance and VLE
    Given I execute "Log In To Salesforce" reusable step
        #Attendance and Absences
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 9  | I click on the "Attendance & Absences" "Salesforce cases dropdown option"            |
      | 11 | I click on the "Name missing from class register" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Bus & Tech Attendance" to be displayed for "Salesforce Case Owner Data Field" element
    #Book an Appointment
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 9  | I click on the "Book an Appointment" "Salesforce cases dropdown option"          |
      | 11 | I click on the "International Student Advice" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=International Student Advice" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 9  | I click on the "Book an Appointment" "Salesforce cases dropdown option" |
      | 11 | I click on the "Personal Tutor" "Salesforce cases dropdown option"      |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=School Programme Support" to be displayed for "Salesforce Case Owner Data Field" element
   #VLE
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 9  | I click on the "VLE" "Salesforce cases dropdown option"                   |
      | 11 | I click on the "Cant login to the VLE" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Admin Automation" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 9  | I click on the "VLE" "Salesforce cases dropdown option"                                 |
      | 11 | I click on the "Cant see submission link on the VLE" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Academic Admin" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 9  | I click on the "VLE" "Salesforce cases dropdown option"                      |
      | 11 | I click on the "Missing or wrong modules" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Student Records" to be displayed for "Salesforce Case Owner Data Field" element

  @AssignmentRules
  Scenario: Registry Case Management Verify Case Assignment Rules Letter_Key_Oyster_Timetable_Complaint
    Given I execute "Log In To Salesforce" reusable step
    #Letter Production
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 9  | I click on the "Letter Production" "Salesforce cases dropdown option"   |
      | 11 | I click on the "Academic References" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Academic Admin" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Letter Production Registry Case" reusable step replacing some steps
      | 11 | I click on the "Academic References" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Academic Admin" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Letter Production Registry Case" reusable step replacing some steps
      | 11 | I click on the "Schengen" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=International Student Advice" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Letter Production Registry Case" reusable step replacing some steps
      | 11 | I click on the "Confirmation of completion letter" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Exam Queries" to be displayed for "Salesforce Case Owner Data Field" element
    #Locker Key
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 9  | I click on the "Locker Key" "Salesforce cases dropdown option"       |
      | 11 | I click on the "Withheld Results" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Student Records" to be displayed for "Salesforce Case Owner Data Field" element
    #Oyster Card
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 9  | I click on the "Oyster Card" "Salesforce cases dropdown option" |
      | 11 | I click on the "Other" "Salesforce cases dropdown option"       |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Student Records" to be displayed for "Salesforce Case Owner Data Field" element
    #Personal Details
    And I execute "Create Assesments Registry Case" reusable step replacing some steps
      | 9  | I click on the "Personal Details" "Salesforce cases dropdown option"          |
      | 11 | I click on the "Change of contact details" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Student Records" to be displayed for "Salesforce Case Owner Data Field" element
    #Registartion and Induction
    And I execute "Create Registration and Induction Registry Case" reusable step
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=School Programme Support" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Registration and Induction Registry Case" reusable step replacing some steps
      | 11 | I click on the "Exemptions" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Academic Admin" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Registration and Induction Registry Case" reusable step replacing some steps
      | 11 | I click on the "Not Received Joining Instructions" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Student Records" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Registration and Induction Registry Case" reusable step replacing some steps
      | 11 | I click on the "International Query" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=International Student Advice" to be displayed for "Salesforce Case Owner Data Field" element
    #Timetable
    And I execute "Create Timetable Registry Case" reusable step
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=School Programme Support" to be displayed for "Salesforce Case Owner Data Field" element
    And I execute "Create Timetable Registry Case" reusable step replacing some steps
      | 11 | I click on the "Group number" "Salesforce cases dropdown option" |
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Student Records" to be displayed for "Salesforce Case Owner Data Field" element
    #Complaint
    And I execute "Create Complaints Registry Case" reusable step
    And I should scroll to the "top" of the page
    And I validate text "CONTAINS=Head of Student Experience" to be displayed for "Salesforce Case Owner Data Field" element