@PF-API
Feature: Product Factory API Data Creation
  As an Admin I should be able to create items in PF using API

  @FinancialDimensionCourseType #TC-1532
  Scenario: Create Financial Dimensions record Course Type item
    When I create Financial Dimensions record for Course Type item

  @FinancialDimensionMaterialType #TC-1532
  Scenario: Create Financial Dimensions record Material Type item
    When I create Financial Dimensions record for Material Type item

  @FinancialDimensionLocation #TC-1532
  Scenario: Create Financial Dimensions record Location item
    When I create Financial Dimensions record for Location item

  @FinancialDimensionBody #TC-1532
  Scenario: Create Financial Dimensions record Body item
    When I create Financial Dimensions record for Body item

  @FinancialDimensionRegion #TC-1532
  Scenario: Create Financial Dimensions record Region item
    When I create Financial Dimensions record for Region item

  @Region #TC-1875
  Scenario: Create new Region
    When I create new Region

  @DigitalContent #TC-3148
  Scenario: Create Digital Content
    When I create new Digital Content

  @CourseInstance
  Scenario: Create Course Instance
    When I create new Instance