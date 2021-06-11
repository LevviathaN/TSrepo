@API
Feature: Product Factory API Data Creation
  As an Admin I should be able to create items in PF using API

  @FinancialDimension @FD
  Scenario: Create Financial Dimension
    When I create new Financial Dimension

  @ISBN
  Scenario: Generate new ISBN
    When I generate new ISBN code

  @VatRule @VR
  Scenario: Create Vat Rule
    When I create new Vat Rule

  @ExamPreparation @EP
  Scenario: Create Exam Preparation
    When I create new Exam Preparation

  @StudyMode @SM
  Scenario: Create Study Mode
    When I create new Study Mode

  @CourseType @CT
  Scenario: Create Course Type
    When I create new Course Type