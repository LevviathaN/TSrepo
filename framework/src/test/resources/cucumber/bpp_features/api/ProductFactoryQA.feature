@REFERENCE-ITEM
Feature: Product Factory API Data Creation
  As an Admin I should be able to create items in PF using API

  @ISBN
  Scenario: Generate new ISBN
    When I generate new ISBN code

  @FinancialDimension #TC-1532
  Scenario: Create Financial Dimension
    When I create new Financial Dimension

  @VatRule #TC-1340
  Scenario: Create Vat Rule
    When I create new Vat Rule

  @ExamPreparation #TC-4692
  Scenario: Create Exam Preparation
    When I create new Exam Preparation

  @StudyMode #TC-4793
  Scenario: Create Study Mode
    When I create new Study Mode

  @CourseType #TC-701
  Scenario: Create Course Type
    When I create new Course Type

  @Vertical #TC-4811
  Scenario: Create Vertical
    When I create new Vertical

  @Body #TC-743
  Scenario: Create Body
    When I create new Body

  @Sitting #TC-835
  Scenario: Create Sitting
    When I create new Sitting

  @Paper #TC-772
  Scenario: Create Paper
    When I create new Paper

  @Level #TC-746
  Scenario: Create Level
    When I create new Level

  @BodyToLevels #TC-703
  Scenario: Link Body to Levels
    When I link Body to Levels

  @ChangePaperBody #TC-697
  Scenario: Change Paper Body
    When I change Paper Body

  @PaperToLevels #TC-716
  Scenario: Link Paper to Levels
    When I link Paper to Levels

  @Region #TC-1875
  Scenario: Create Region
    When I create new Region

  @Location #TC-775
  Scenario: Create Location
    When I create new Location

  @SessionDuration #TC-811
  Scenario: Create Session Duration
    When I create new Session Duration

  @PricingMatrix #TC-981
  Scenario: Create Pricing Matrix
    When I create new Pricing Matrix

  @Prices #TC-1032
  Scenario: Create Prices
    When I create new Prices

  @DigitalContent #TC-3148
  Scenario: Create Digital Content
    When I create new Digital Content

  @UniversityProgrammes #TC-5387
  Scenario: Create University Programmes
    When I create new University Programmes

  @ProgrammeCohorts #TC-5391
  Scenario: Create Programme Cohorts
    When I create new Programme Cohorts

  @ModuleSections #TC-5407
  Scenario: Create Module Sections
    When I create new Module Sections

  @StockSite
  Scenario: Create Stock Site
    When I create new Stock Site

  @MaterialType #TC-1435
  Scenario: Create Material Type
    When I create new Material Type

  @Clients #TC-919
  Scenario: Create Clients
    When I create new Clients

  @Streams #TC-2929
  Scenario: Create Streams
    When I create new Streams

  @DeactivationReason #TC-975
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

  @ChangeSessionTimings
  Scenario: Change Session Timings
    When I change Session Timings

  @GetInstanceSteps
  Scenario: Get Instance Steps
    When I get Instance Steps

  @ChangeInstanceSteps
  Scenario: Change Instance Steps
    When I change Instance Steps

  @CalculateCoursePrice
  Scenario: Calculate Course Price
    When I calculate Course Price

  @ActivateCourse
  Scenario: Activate Course
    When I activate Course

  @ActivateInstance
  Scenario: Activate Instance
    When I activate Instance

  @CreateCourseBulkOperation
  Scenario: Create Course Bulk Operation
    When I create Course Bulk Operation

  @BulkWebPublish #TC-685
  Scenario: Execute Bulk Web Publishing
    When I execute Bulk Web Publishing