@PF-API
Feature: Product Factory API Data Creation
  As an Admin I should be able to create items in PF using API

  @ISBN
  Scenario: Generate new ISBN
    When I generate new ISBN code

  @FinancialDimension
  Scenario: Create Financial Dimension
    When I create new Financial Dimension

  @VatRule
  Scenario: Create Vat Rule
    When I create new Vat Rule

  @ExamPreparation
  Scenario: Create Exam Preparation
    When I create new Exam Preparation

  @StudyMode
  Scenario: Create Study Mode
    When I create new Study Mode

  @CourseType
  Scenario: Create Course Type
    When I create new Course Type

  @Vertical
  Scenario: Create Vertical
    When I create new Vertical

  @Body
  Scenario: Create Body
    When I create new Body

  @Sitting
  Scenario: Create Sitting
    When I create new Sitting

  @Paper
  Scenario: Create Paper
    When I create new Paper

  @Level
  Scenario: Create Level
    When I create new Level

  @BodyToLevels
  Scenario: Link Body to Levels
    When I link Body to Levels

  @ChangePaperBody
  Scenario: Change Paper Body
    When I change Paper Body

  @PaperToLevels
  Scenario: Link Paper to Levels
    When I link Paper to Levels