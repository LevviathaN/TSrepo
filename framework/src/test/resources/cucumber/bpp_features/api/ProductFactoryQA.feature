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

  @Region
  Scenario: Create Region
    When I create new Region

  @Location
  Scenario: Create Location
    When I create new Location

  @SessionDuration
  Scenario: Create Session Duration
    When I create new Session Duration

  @PricingMatrix
  Scenario: Create Pricing Matrix
    When I create new Pricing Matrix

  @Prices
  Scenario: Create Prices
    When I create new Prices

  @DigitalContent
  Scenario: Create Digital Content
    When I create new Digital Content

  @UniversityProgrammes
  Scenario: Create University Programmes
    When I create new University Programmes

  @ProgrammeCohorts
  Scenario: Create Programme Cohorts
    When I create new Programme Cohorts

  @ModuleSections
  Scenario: Create Module Sections
    When I create new Module Sections

  @StockSite
  Scenario: Create Stock Site
    When I create new Stock Site

  @MaterialType
  Scenario: Create Material Type
    When I create new Material Type

  @Clients
  Scenario: Create Clients
    When I create new Clients

  @Streams
  Scenario: Create Streams
    When I create new Streams

  @DeactivationReason
  Scenario: Create Deactivation Reason
    When I create new Deactivation Reason

  @Course
  Scenario: Create Course
    When I create new Course

  @Instance
  Scenario: Create Instance
    When I create new Instance

  @ChangeInstance
  Scenario: Change Instance Capacity
    When I change Instance Capacity

  @GetInstanceSessions
  Scenario: Get Instance Sessions
    When I get Instance Sessions