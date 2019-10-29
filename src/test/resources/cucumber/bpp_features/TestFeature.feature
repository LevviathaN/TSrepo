@PFTest
@Ignore
@Old
Feature: Product Factory
  As a product setter
  I want to be able to log into Product Factory
  In order to (just cause)



  Scenario: BPP University Hub Breadcrumbs
  #//////////////////////////////////////////////////////////////////////////////////////////
  #//////////////////////////////////////////////////////////////////////////////////////////

    #----------------------------Business Process - Direct App Student Registration----------------------------
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" BPP URL
    When I click on the BPP element located by "DIRECT_APP_REGISTER_LINK_LOGIN_PAGE"
    When I fill the "DIRECT_APP_FIRST_NAME_FIELD" BPP field with "Auto<KW_AUTO_FIRSTNAME>"
    When I capture data from "DIRECT_APP_FIRST_NAME_FIELD" BPP element into "EC_FIRSTNAME" variable
    When I fill the "DIRECT_APP_SURNAME_FIELD" BPP field with "Auto<KW_AUTO_LASTNAME>"
    When I capture data from "DIRECT_APP_SURNAME_FIELD" BPP element into "EC_LASTNAME" variable
    When I SmartClient click on BPP element "CleanUp_DIRECTAPP_ACCEPT_AND_CLOSE_COOKIES_TOP_RIBBON_BUTTON"
    When I fill the "DIRECT_APP_EMAIL_FIELD" BPP field with "Auto<KW_AUTO_FIRSTNAMEFILE><KW_AUTO_LASTNAMEFILE>\@harakirimail.com"
    When I capture data from "DIRECT_APP_EMAIL_FIELD" BPP element into "EC_EMAIL_ADDRESS" variable
    When I fill the "DIRECT_APP_TELEPHONE_NUMBER_FIELD" BPP field with "0913<KW_AUTO_RANDOMNUMBER|#######>"
    When I fill the "DIRECT_APP_PASSWORD_FIELD" BPP field with "A@polloGlobal2018"
    When I capture data from "DIRECT_APP_PASSWORD_FIELD" BPP element into "EC_PASSWORD" variable
    When I click on the BPP element located by "DIRECT_APP_I_AM_HAPPY_TO_RECEIVE_INFORMATION_FROM_BPP_RADIO_BTN"
    When I click on the BPP element located by "DIRECT_APP_REGISTER_BUTTON"

    #----------------------------Business Process - Direct App Student Logout----------------------------
    When I click on the BPP element located by "DIRECTAPP_USER_ICON_MENU"
    When I click on the BPP element located by "DIRECTAPP_LOGOUT_LINK"

    #----------------------------Business Process - Totara Login User Walkaround----------------------------
    Given I am on "MD_COMMON_LINKS_TOTARALOGINURL" BPP URL
    When I SmartClient click on BPP element "CleanUp_TOTARA_LOGOUT_LOGGED_USER_BUTTON"
    Given I am on "MD_COMMON_LINKS_TOTARALOGINURL" BPP URL
    When I fill the "TOTARA_USERNAME_INPUT" BPP field with "MD_COMMON_CREDENTIALS_TOTARAADMINUSER"
    When I fill the "TOTARA_PASSWORD_INPUT" BPP field with "MD_COMMON_CREDENTIALS_TOTARAADMINPASSWORD"
    When I click on the BPP element located by "TOTARA_LOGIN_BUTTON"

    #----------------------------Business Process - Totara Enrol Student to ICAEW Course----------------------------
    When I click on the BPP element located by "TOTARA_FIRST_COURSE_LINK"
    When I click on the BPP element located by "TOTARA_USER_EXPANDABLE_MENU"
    When I click on the BPP element located by "TOTARA_ENROLLED_USERS_LINK"
    When I click on the BPP element located by "TOTARA_ENROL_USERS_BUTTON"
    When I fill the "TOTARA_ENROL_USERS_SEARCH_FIELD" BPP field with "EC_EMAIL_ADDRESS"
    When I click on the BPP element located by "TOTARA_ENROL_USERS_SEARCH_BUTTON"
    When I click on the BPP element located by "TOTARA_ENROL_BUTTON"
    When I click on the BPP element located by "TOTARA_FINISH_ENROLING_USERS_BUTTON"
    When I wait for "3" seconds at BPP page

    #----------------------------Business Process - Totara Logout----------------------------
    When I click on the BPP element located by "TOTARA_ACCOUNT_EXPANDABLE_MENU"
    When I click on the BPP element located by "TOTARA_ACCOUNT_LOGOUT_LINK"

    #----------------------------Business Process - Login Student BuildEmpire----------------------------
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" BPP URL
    When I fill the "DIRECTAPP_STUDENT_EMAIL_TEXT_FIELD" BPP field with "EC_EMAIL_ADDRESS"
    When I fill the "DIRECTAPP_STUDENT_PASSWORD_TEXT_FIELD" BPP field with "EC_PASSWORD"
    When I click on the BPP element located by "DIRECT_APP_LOGIN_BUTTON"

    #----------------------------Business Process - Student Breadcrumbs validation----------------------------
    When I verify that element located by "STUDENT_APPLICATION_HOME_NOT_VISIBLE_BREADCRUMBS_LINK" is not visible on BPP page
    When I capture data from "STAGING_MOODLE_MY_LEARNING_LINK" BPP element into "EC_MY_LEARNING" variable
    When I click on the BPP element located by "STAGING_MOODLE_MY_LEARNING_LINK"
    When I verify that element located by "STUDENT_BREADCRUMBS_MY_LEARNING_NOT_A_LINK" is not visible on BPP page
    When I verify that BPP element "STUDENT_APPLICATION_SECOND_LEVEL_BREADCRUMBS_LINK" contains text "EC_MY_LEARNING"
    When I capture data from "BE_STUDENT_MY_LEARNING_PAGE_OPEN_FIRST_COURSE_NAME_STICKER" BPP element into "EC_COURSE" variable
    When I click on the BPP element located by "BE_STUDENT_MY_LEARNING_PAGE_OPEN_FIRST_COURSE_STICKER"
    When I verify that BPP element "STUDENT_APPLICATION_THIRD_LEVEL_BREADCRUMBS_LINK" contains text "EC_COURSE"
    When I capture data from "BE_STUDENT_MY_LEARNING_PAGE_FIRST_COURSE_SECTION_NAME_STICKER" BPP element into "EC_COURSE_SECTION" variable
    When I click on the BPP element located by "BE_STUDENT_MY_LEARNING_OPEN_FIRST_TOPIC"
    When I verify that BPP element "STUDENT_APPLICATION_FOURTH_LEVEL_BREADCRUMBS_LINK" contains text "EC_COURSE_SECTION"
    When I capture data from "STUDENT_APPLICATION_THIRD_LEVEL_BREADCRUMBS_LINK" BPP element into "EC_THRID_BREADCRUMB" variable
    When I click on the BPP element located by "STUDENT_APPLICATION_THIRD_LEVEL_BREADCRUMBS_LINK"
    When I verify that BPP element "BE_AUTOMATION_VALIDATE_TOPIC_HEADER" contains text "EC_THRID_BREADCRUMB"
    When I capture data from "STUDENT_APPLICATION_SECOND_LEVEL_BREADCRUMBS_LINK" BPP element into "EC_SECOND_BREADCRUMB" variable
    When I click on the BPP element located by "STUDENT_APPLICATION_SECOND_LEVEL_BREADCRUMBS_LINK"
    When I verify that BPP element "BE_AUTOMATION_VALIDATE_TOPIC_HEADER" contains text "EC_SECOND_BREADCRUMB"
    When I click on the BPP element located by "STUDENT_APPLICATION_FIRST_LEVEL_BREADCRUMBS_LINK"
    When I wait for "3" seconds at BPP page
    When I verify that BPP element "BE_AUTOMATION_VALIDATE_TOPIC_HEADER" contains text "CONTAINS=EC_FIRSTNAME"
    When I click on the BPP element located by "DIRECT_APP_MY_APPLICATION_TAB"
    When I click on the BPP element located by "DIRECTAPP_WHAT_ARE_YOU_APPLYING_FOR_DROPDOWN_ICON"
    When I click on the BPP element located by "DIRECTAPP_BPP_UNIVERSITY_PROGRAMMES_DROPDOWN_OPTION"
    When I click on the BPP element located by "DIRECTAPP_PROGRAMMES_PROGRAMM_DROPDOWN_ICON"
    When I wait for "2" seconds at BPP page
    When I click on the BPP element located by "DIRECTAPP_PROGRAMME_ACCA_PROFESSIONAL_LEVEL_PAPERS"
    When I click on the BPP element located by "DIRECTAPP_PROGRAMMES_LOCATION_DROPDOWN_ICON"
    When I click on the BPP element located by "DIRECTAPP_LOCATION_BPP_UNIVERSITY_BIRMINGHAM"
    When I click on the BPP element located by "DIRECTAPP_PROGRAMMES_START_DATE_DROPDOWN_ICON"
    When I click on the BPP element located by "DIRECTAPP_START_DATE_"
    When I click on the BPP element located by "DIRECTAPP_CREATE_APPLICATION_BUTTON"
    When I verify that BPP element "DIRECTAPP_START_NEW_APPLICATION_LINK" contains text "Start new application"
    When I verify that BPP element "DIRECTAPP_START_APPLICATION_BUTTON" contains text "Start Application"
    When I click on the BPP element located by "DIRECTAPP_START_APPLICATION_BUTTON"
    When I capture data from "STUDENT_APPLICATION_THIRD_LEVEL_BREADCRUMBS_LINK" BPP element into "EC_THIRD_APP_BREADCRUMBS" variable
    When I verify that BPP element "BPP_APPLICATION_PROGRAMME_PAGE_PROGRAMME_NAME_DESCRIPTION" contains text "CONTAINS=EC_THIRD_APP_BREADCRUMBS"
    When I capture data from "DIRECTAPP_ELIGIBILITY_CRITERIA_SECTION" BPP element into "EC_FOURST_BREADCRUMB" variable
    When I click on the BPP element located by "DIRECTAPP_ELIGIBILITY_CRITERIA_SECTION"
    When I verify that BPP element "STUDENT_APPLICATION_FOURTH_LEVEL_BREADCRUMBS_LINK" contains text "CONTAINS=EC_FOURST_BREADCRUMB"
    When I wait for "3" seconds at BPP page

    #----------------------------Business Process - Direct App Student Logout----------------------------
    When I click on the BPP element located by "DIRECTAPP_USER_ICON_MENU"
    When I click on the BPP element located by "DIRECTAPP_LOGOUT_LINK"

    #----------------------------Business Process - Login Student BuildEmpire----------------------------
    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" BPP URL
    When I fill the "DIRECTAPP_STUDENT_EMAIL_TEXT_FIELD" BPP field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINUSER"
    When I fill the "DIRECTAPP_STUDENT_PASSWORD_TEXT_FIELD" BPP field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINPASSWORD"
    When I click on the BPP element located by "DIRECT_APP_LOGIN_BUTTON"

    #----------------------------Business Process - Line Manager Breadcrumbs validation----------------------------
    When I verify that element located by "STUDENT_APPLICATION_HOME_NOT_VISIBLE_BREADCRUMBS_LINK" is not visible on BPP page
    When I capture data from "DIRECTAPP_TRAINING_MANAGER_MY_APPROVALS_TAB" BPP element into "EC_MY_APPROVALS" variable
    When I click on the BPP element located by "DIRECTAPP_TRAINING_MANAGER_MY_APPROVALS_TAB"
    When I verify that BPP element "STUDENT_APPLICATION_SECOND_LEVEL_BREADCRUMBS_LINK" contains text "EC_MY_APPROVALS"
    When I click on the BPP element located by "DIRECT_APP_MANAGER_REVIEW_APPLICATION_BUTTON"
    When I capture data from "DIRECTAPP_ELIGIBILITY_CRITERIA_SECTION" BPP element into "EC_ELIGIBILITY" variable
    When I click on the BPP element located by "DIRECTAPP_ELIGIBILITY_CRITERIA_SECTION"
    When I verify that BPP element "STUDENT_APPLICATION_THIRD_LEVEL_BREADCRUMBS_LINK" contains text "EC_ELIGIBILITY"
    When I wait for "3" seconds at BPP page



#  Scenario: BPP University Hub Timeline Info And News
#  #//////////////////////////////////////////////////////////////////////////////////////////
#  #//////////////////////////////////////////////////////////////////////////////////////////
#
#    #----------------------------Business Process - Direct App Student Registration----------------------------
#    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" BPP URL
#    When I click on the BPP element located by "DIRECT_APP_REGISTER_LINK_LOGIN_PAGE"
#    When I fill the "DIRECT_APP_FIRST_NAME_FIELD" BPP field with "Auto<KW_AUTO_FIRSTNAME>"
#    When I capture data from "DIRECT_APP_FIRST_NAME_FIELD" BPP element into "EC_FIRSTNAME" variable
#    When I fill the "DIRECT_APP_SURNAME_FIELD" BPP field with "Auto<KW_AUTO_LASTNAME>"
#    When I capture data from "DIRECT_APP_SURNAME_FIELD" BPP element into "EC_LASTNAME" variable
#    When I SmartClient click on BPP element "CleanUp_DIRECTAPP_ACCEPT_AND_CLOSE_COOKIES_TOP_RIBBON_BUTTON"
#    When I fill the "DIRECT_APP_EMAIL_FIELD" BPP field with "Auto<KW_AUTO_FIRSTNAMEFILE><KW_AUTO_LASTNAMEFILE>@harakirimail.com"
#    When I capture data from "DIRECT_APP_EMAIL_FIELD" BPP element into "EC_EMAIL_ADDRESS" variable
#    When I fill the "DIRECT_APP_TELEPHONE_NUMBER_FIELD" BPP field with "0913<KW_AUTO_RANDOMNUMBER|#######>"
#    When I fill the "DIRECT_APP_PASSWORD_FIELD" BPP field with "A@polloGlobal2018"
#    When I capture data from "DIRECT_APP_PASSWORD_FIELD" BPP element into "EC_PASSWORD" variable
#    When I click on the BPP element located by "DIRECT_APP_I_AM_HAPPY_TO_RECEIVE_INFORMATION_FROM_BPP_RADIO_BTN"
#    When I click on the BPP element located by "DIRECT_APP_REGISTER_BUTTON"
#
#    #----------------------------Business Process - Login Admin BuildEmpire----------------------------
#    Given I am on "MD_COMMON_LINKS_BUILDEMPIREADMINURL" BPP URL
#    When I fill the "DIRECTAPP_ADMIN_EMAIL_TEXT_FIELD" BPP field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINUSER"
#    When I fill the "DIRECTAPP_ADMIN_PASSWORD_TEXT_FIELD" BPP field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINPASSWORD"
#    When I click on the BPP element located by "DIRECT_APP_LOGIN_BUTTON"
#
#    #----------------------------Business Process - Create New Piece Of News----------------------------
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_WEBSITE_DROPDOWN"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_NEWS"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_NEWS_ADD_BUTTON"
#    When I wait for "3" seconds at BPP page
#    When I fill the "BPP_DIGITAL_ADMIN_WEBSITE_NEWS_NEW_TITLE" BPP field with "AutoTest Breaking News"
#    When I fill the "BPP_DIGITAL_ADMIN_WEBSITE_NEWS_NEW_CONTENT" BPP field with "Definitely important information to see"
#    When I fill the "BPP_DIGITAL_ADMIN_WEBSITE_NEWS_NEW_AUTHOR" BPP field with "Automation Team"
#    When I fill the "BPP_DIGITAL_ADMIN_WEBSITE_NEWS_NEW_URL" BPP field with "https://www.google.com"
#    When I fill the "BPP_DIGITAL_ADMIN_WEBSITE_NEWS_NEW_VISIBILITY_GROUPS" BPP field with "BPP University"
#    When I press the "MD_COMMON_KEYBOARD_ENTER" key on the keyboard for BPP
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_NEWS_NEW_SAVE_BUTTON"
#
#    #----------------------------Business Process - Admin Logout----------------------------
#    When I click on the BPP element located by "DIRECTAPP_ADMIN_USER_ICON_AREA"
#    When I click on the BPP element located by "DIRECT_APP_ADMIN_LOGOUT_LINK"
#
#    #----------------------------Business Process - Login Student BuildEmpire If Account Exists----------------------------
#    Given I am on "MD_COMMON_LINKS_BUILDEMPIRELOGINURL" BPP URL
#    When I fill the "DIRECTAPP_STUDENT_EMAIL_TEXT_FIELD" BPP field with "EC_EMAIL_ADDRESS"
#    When I fill the "DIRECTAPP_STUDENT_PASSWORD_TEXT_FIELD" BPP field with "EC_PASSWORD"
#    When I click on the BPP element located by "DIRECT_APP_LOGIN_BUTTON"
#    When I SmartClient click on BPP element "CleanUp_DIRECTAPP_ACCEPT_AND_CLOSE_COOKIES_TOP_RIBBON_BUTTON"
#
#    #----------------------------Business Process - Student Validate News----------------------------
#    When I verify that BPP element "BUILDEMPIRE_HOME_NEWS_AND_INFORMATION_TITLE" contains text "AutoTest Breaking News"
#    When I verify that BPP element "BUILDEMPIRE_HOME_NEWS_AND_INFORMATION_CONTENT" contains text "Definitely important information to see"
#    When I verify that BPP element "BUILDEMPIRE_HOME_NEWS_AND_INFORMATION_AUTHOR" contains text "CONTAINS=Automation Team"
#    When I validate that BPP element "BUILDEMPIRE_HOME_NEWS_AND_INFORMATION_URL" has "href=https://www.google.com/"
#
#    #----------------------------Business Process - Login Admin BuildEmpire----------------------------
#    Given I am on "MD_COMMON_LINKS_BUILDEMPIREADMINURL" BPP URL
#    When I fill the "DIRECTAPP_ADMIN_EMAIL_TEXT_FIELD" BPP field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINUSER"
#    When I fill the "DIRECTAPP_ADMIN_PASSWORD_TEXT_FIELD" BPP field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINPASSWORD"
#    When I click on the BPP element located by "DIRECT_APP_LOGIN_BUTTON"
#
#    #----------------------------Business Process - Edit Last Piece Of News----------------------------
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_WEBSITE_DROPDOWN"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_NEWS"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_WEBSITE_NEWS_EDIT_BUTTON"
#    When I wait for "3" seconds at BPP page
#    When I fill the "BPP_DIGITAL_ADMIN_WEBSITE_NEWS_NEW_TITLE" BPP field with "AutoTest Breaking News"
#    When I fill the "BPP_DIGITAL_ADMIN_WEBSITE_NEWS_NEW_CONTENT" BPP field with "Definitely important information to see"
#    When I fill the "BPP_DIGITAL_ADMIN_WEBSITE_NEWS_NEW_AUTHOR" BPP field with "Automation Team"
#    When I fill the "BPP_DIGITAL_ADMIN_WEBSITE_NEWS_NEW_URL" BPP field with "https://www.google.com"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_WEBSITE_NEWS_NEW_REMOVE_VISIBILITY_GROUP_BUTTON"
#    When I fill the "BPP_DIGITAL_ADMIN_WEBSITE_NEWS_NEW_VISIBILITY_GROUPS" BPP field with "Albania"
#    When I press the "MD_COMMON_KEYBOARD_ENTER" key on the keyboard for BPP
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_NEWS_NEW_SAVE_BUTTON"
#
#    #----------------------------Business Process - Admin Logout----------------------------
#    When I click on the BPP element located by "DIRECTAPP_ADMIN_USER_ICON_AREA"
#    When I click on the BPP element located by "DIRECT_APP_ADMIN_LOGOUT_LINK"
#
#    #----------------------------Business Process - Login Student BuildEmpire If Account Exists----------------------------
#    Given I am on "MD_COMMON_LINKS_BUILDEMPIRELOGINURL" BPP URL
#    When I fill the "DIRECTAPP_STUDENT_EMAIL_TEXT_FIELD" BPP field with "EC_EMAIL_ADDRESS"
#    When I fill the "DIRECTAPP_STUDENT_PASSWORD_TEXT_FIELD" BPP field with "EC_PASSWORD"
#    When I click on the BPP element located by "DIRECT_APP_LOGIN_BUTTON"
#    When I SmartClient click on BPP element "CleanUp_DIRECTAPP_ACCEPT_AND_CLOSE_COOKIES_TOP_RIBBON_BUTTON"
#
#    #----------------------------Business Process - Student Validate No News----------------------------
#    When I verify that element located by "BUILDEMPIRE_HOME_NEWS_AND_INFORMATION_TITLE" is not visible on BPP page
#
#    #----------------------------Business Process - Login Admin BuildEmpire----------------------------
#    Given I am on "MD_COMMON_LINKS_BUILDEMPIREADMINURL" BPP URL
#    When I fill the "DIRECTAPP_ADMIN_EMAIL_TEXT_FIELD" BPP field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINUSER"
#    When I fill the "DIRECTAPP_ADMIN_PASSWORD_TEXT_FIELD" BPP field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINPASSWORD"
#    When I click on the BPP element located by "DIRECT_APP_LOGIN_BUTTON"
#
#    #----------------------------Business Process - Delete Last Piece Of News----------------------------
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_WEBSITE_DROPDOWN"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_NEWS"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_WEBSITE_NEWS_DELETE_BUTTON"
#    When I confirm BPP JS Alert "Are you sure?"
#    When I wait for "3" seconds at BPP page
#
#
#
#  Scenario: BPP University Hub Web Pages
#  #//////////////////////////////////////////////////////////////////////////////////////////
#  #//////////////////////////////////////////////////////////////////////////////////////////
#
#    #----------------------------Business Process - Login Admin BuildEmpire----------------------------
#    Given I am on "MD_COMMON_LINKS_BUILDEMPIREADMINURL" BPP URL
#    When I fill the "DIRECTAPP_ADMIN_EMAIL_TEXT_FIELD" BPP field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINUSER"
#    When I fill the "DIRECTAPP_ADMIN_PASSWORD_TEXT_FIELD" BPP field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINPASSWORD"
#    When I click on the BPP element located by "DIRECT_APP_LOGIN_BUTTON"
#
#    #----------------------------Business Process - BPP Digital Create CMS Page----------------------------
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_WEBSITE_DROPDOWN"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_VLE_CMS_PAGES_LINK"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_BLOG_POSTS_ADD_BUTTON"
#
#    #----------------------------Business Process - BPP Digital Validate Show In Hub Option----------------------------
#    When I verify that BPP element "ADMIN_BUILD_EMPIRE_CMS_PAGE_SHOW_IN_NAVIGATION" contains text "Show in navigation"
#    When I verify that BPP element "ADMIN_BUILD_EMPIRE_CMS_PAGE_SHOW_IN_SITEMAP" contains text "Show in sitemap"
#    When I verify that BPP element "ADMIN_BUILD_EMPIRE_CMS_PAGE_SHOW_IN_HUB" contains text "Show in hub"
#    When I verify that BPP element "ADMIN_BUILD_EMPIRE_CMS_PAGE_SHOW_IN_WEBSITE" contains text "Show in website"
#    When I switch "ADMIN_BUILD_EMPIRE_CMS_PAGE_SHOW_IN_HUB_CHECKBOX" BPP checkbox to "True"
#    When I verify that BPP element "ADMIN_BUILD_EMPIRE_CMS_PAGE_ROLES_DROPDOWN_" contains text "Roles"
#    When I verify that BPP element "ADMIN_BUILD_EMPIRE_CMS_PAGE_VISIBILITY_GROUPS_DROPWODN" contains text "Visibility groups"
#    When I wait for "3" seconds at BPP page
#
#    #----------------------------Business Process - BPP Digital Submit CMS Page----------------------------
#    When I fill the "BPP_DIGITAL_ADMIN_CMS_PAGE_NAME_FIELD" BPP field with "AutomationParent<KW_AUTO_RANDOMNUMBER|#####>"
#    When I capture data from "BPP_DIGITAL_ADMIN_CMS_PAGE_NAME_FIELD" BPP element into "EC_CMS_PAGE_NAME_ONE" variable
#    When I fill the "BPP_DIGITAL_ADMIN_CMS_PAGE_PATH_FIELD" BPP field with "Parent-<KW_AUTO_RANDOMNUMBER|#####>"
#    When I capture data from "BPP_DIGITAL_ADMIN_CMS_PAGE_PATH_FIELD" BPP element into "EC_CMS_PAGE_URL_ONE" variable
#    When I select "" from BPP dropdown "BPP_DIGITAL_ADMIN_CMS_PAGE_PARENT_DROPDOWN"
#    When I fill the "BPP_DIGITAL_ADMIN_CMS_PAGE_TITLE_FIELD" BPP field with "Automation Page Title"
#    When I capture data from "BPP_DIGITAL_ADMIN_CMS_PAGE_TITLE_FIELD" BPP element into "EC_CMS_PAGE_TITLE_ONE" variable
#    When I fill the "BPP_DIGITAL_ADMIN_CMS_PAGE_SUMMARY_FIELD" BPP field with "Automation Page Summary"
#    When I capture data from "BPP_DIGITAL_ADMIN_CMS_PAGE_SUMMARY_FIELD" BPP element into "EC_CMS_PAGE_SUMMARY_ONE" variable
#    When I fill the "BPP_DIGITAL_ADMIN_CMS_PAGE_META_DESCRIPTION_FIELD" BPP field with "Automation Meta Description"
#    When I capture data from "BPP_DIGITAL_ADMIN_CMS_PAGE_META_DESCRIPTION_FIELD" BPP element into "EC_CMS_PAGE_META_DESCRIPTIION_ONE" variable
#    When I switch "BPP_DIGITAL_ADMIN_CMS_PAGE_SHOW_IN_NAV_FIELD" BPP checkbox to "EC_CMS_PAGE_META_DESCRIPTIION_ONE"
#    When I switch "BPP_DIGITAL_ADMIN_CMS_PAGE_SHOW_IN_SITEMAP_FIELD" BPP checkbox to "EC_CMS_PAGE_META_DESCRIPTIION_ONE"
#    When I fill the "BPP_DIGITAL_ADMIN_CMS_PAGE_LINK_TITLE_FIELD" BPP field with "Automatiion Link Title"
#    When I capture data from "BPP_DIGITAL_ADMIN_CMS_PAGE_LINK_TITLE_FIELD" BPP element into "EC_CMS_LINK_TITLE_ONE" variable
#    When I fill the "BPP_DIGITAL_ADMIN_CMS_PAGE_LINK_DESCRIPTION_FIELD" BPP field with "Automation Link Description"
#    When I capture data from "BPP_DIGITAL_ADMIN_CMS_PAGE_LINK_DESCRIPTION_FIELD" BPP element into "EC_CMS_LINK_DESCRIPTION_ONE" variable
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_BLOG_POST_SAVE_BUTTON"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_BLOG_POST_ALERT_MESSAGE" contains text "CONTAINS=CMS Page was successfully created."
#
#    #----------------------------Business Process - BPP Digital Publish CMS Page----------------------------
#    When I execute "window.scrollTo(0,0)" JS script for BPP
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_BLOG_POST_PUBLISH_BUTTON"
#    When I wait for "1" seconds at BPP page
#    When I confirm BPP JS Alert "Are you sure want to publish this page?"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_BLOG_POST_ALERT_MESSAGE" contains text "CONTAINS=Page successfully published."
#
#    #----------------------------Business Process - BPP Digital Validate CMS Page Components Availability----------------------------
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_CMS_PAGE_ADD_COMPONENT_DROPDOWN"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_BLOCKQUOTE_WITH_CAPTION_OPTION" contains text "Blockquote With Caption"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_CALL_OUT_OPTION" contains text "Call Out"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_CIRCLES_OPTION" contains text "Circles"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_DOWNLOAD_CARDS_OPTION" contains text "Download Cards"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_FULL_WIDTH_TEXT_BLOCK_OPTION" contains text "Full Width Text Block"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_IMAGE_TEXT_BUTTON_OPTION" contains text "Image Text Button"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_IMAGES_OPTION" contains text "Images"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_LOGO_CREDS_OPTION" contains text "Logo Creds"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_PEOPLE_OPTION" contains text "People"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_QUICK_LINKS_OPTION" contains text "Quick Links"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_SPOTLIGHT_OPTION" contains text "Spotlight"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_VIDEO_OPTION" contains text "Video"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_VIDEO_SPOTLIGHT_OPTION" contains text "Video Spotlight"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_CMS_PAGE_ADD_COMPONENT_DROPDOWN"
#
#    #----------------------------Business Process - BPP Digital Add Blockquote With Caption Component----------------------------
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_CMS_PAGE_ADD_COMPONENT_DROPDOWN"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_CMS_PAGE_BLOCKQUOTE_WITH_CAPTION_OPTION"
#    When I fill the "BPP_DIGITAL_ADMIN_BLOCKQUOTE_DESCRIPTION_TEXTAREA" BPP field with "Lorem ipsum Blockquote"
#    When I capture data from "BPP_DIGITAL_ADMIN_BLOCKQUOTE_DESCRIPTION_TEXTAREA" BPP element into "EC_BLOCKQUOTE_CONTENT" variable
#    When I fill the "BPP_DIGITAL_ADMIN_BLOCKQUOTE_CAPTION_FIELD" BPP field with "BlockquoteCaption<KW_AUTO_FIRSTNAME>"
#    When I capture data from "BPP_DIGITAL_ADMIN_BLOCKQUOTE_CAPTION_FIELD" BPP element into "EC_BLOCKQUOTE_CAPTION" variable
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_BLOCKQUOTE_SELECT_IMAGE_BUTTON"
#    When I switch to "2" BPP window
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_MAGIC_CIRCLE_FIRMS_SELECT_BUTTON"
#    When I switch to "0" BPP window
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_BLOG_POST_SAVE_BUTTON"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_COMPONENTS_FIRST_ITEM" contains text "CONTAINS=Blockquote with caption"
#
#    #----------------------------Business Process - BPP Digital Navigate to CMS Page Breadcrumb Link----------------------------
#    When I click on the BPP element located by "ADMIN_BUILD_EMPIRE_CMS_PAGE_SHOW_IN_NAVIGATION"
#
#    #----------------------------Business Process - BPP Digital Create CMS Page----------------------------
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_WEBSITE_DROPDOWN"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_VLE_CMS_PAGES_LINK"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_BLOG_POSTS_ADD_BUTTON"
#
#    #----------------------------Business Process - BPP Digital Validate Show In Hub Option----------------------------
#    When I verify that BPP element "ADMIN_BUILD_EMPIRE_CMS_PAGE_SHOW_IN_NAVIGATION" contains text "Show in navigation"
#    When I verify that BPP element "ADMIN_BUILD_EMPIRE_CMS_PAGE_SHOW_IN_SITEMAP" contains text "Show in sitemap"
#    When I verify that BPP element "ADMIN_BUILD_EMPIRE_CMS_PAGE_SHOW_IN_HUB" contains text "Show in hub"
#    When I verify that BPP element "ADMIN_BUILD_EMPIRE_CMS_PAGE_SHOW_IN_WEBSITE" contains text "Show in website"
#    When I switch "ADMIN_BUILD_EMPIRE_CMS_PAGE_SHOW_IN_HUB_CHECKBOX" BPP checkbox to "True"
#    When I verify that BPP element "ADMIN_BUILD_EMPIRE_CMS_PAGE_ROLES_DROPDOWN_" contains text "Roles"
#    When I verify that BPP element "ADMIN_BUILD_EMPIRE_CMS_PAGE_VISIBILITY_GROUPS_DROPWODN" contains text "Visibility groups"
#    When I wait for "3" seconds at BPP page
#
#    #----------------------------Business Process - BPP Digital Submit CMS Page----------------------------
#    When I fill the "BPP_DIGITAL_ADMIN_CMS_PAGE_NAME_FIELD" BPP field with "AutomationChild<KW_AUTO_RANDOMNUMBER|#####>"
#    When I capture data from "BPP_DIGITAL_ADMIN_CMS_PAGE_NAME_FIELD" BPP element into "EC_CMS_PAGE_NAME_TWO" variable
#    When I fill the "BPP_DIGITAL_ADMIN_CMS_PAGE_PATH_FIELD" BPP field with "Child-<KW_AUTO_RANDOMNUMBER|#####>"
#    When I capture data from "BPP_DIGITAL_ADMIN_CMS_PAGE_PATH_FIELD" BPP element into "EC_CMS_PAGE_URL_TWO" variable
#    When I select "EC_CMS_PAGE_NAME_ONE" from BPP dropdown "BPP_DIGITAL_ADMIN_CMS_PAGE_PARENT_DROPDOWN"
#    When I fill the "BPP_DIGITAL_ADMIN_CMS_PAGE_TITLE_FIELD" BPP field with "Automation Page Title"
#    When I capture data from "BPP_DIGITAL_ADMIN_CMS_PAGE_TITLE_FIELD" BPP element into "EC_CMS_PAGE_TITLE_TWO" variable
#    When I fill the "BPP_DIGITAL_ADMIN_CMS_PAGE_SUMMARY_FIELD" BPP field with "Automation Page Summary"
#    When I capture data from "BPP_DIGITAL_ADMIN_CMS_PAGE_SUMMARY_FIELD" BPP element into "EC_CMS_PAGE_SUMMARY_TWO" variable
#    When I fill the "BPP_DIGITAL_ADMIN_CMS_PAGE_META_DESCRIPTION_FIELD" BPP field with "Automation Meta Description"
#    When I capture data from "BPP_DIGITAL_ADMIN_CMS_PAGE_META_DESCRIPTION_FIELD" BPP element into "EC_CMS_PAGE_META_DESCRIPTIION_TWO" variable
#    When I switch "BPP_DIGITAL_ADMIN_CMS_PAGE_SHOW_IN_NAV_FIELD" BPP checkbox to "EC_CMS_PAGE_META_DESCRIPTIION_TWO"
#    When I switch "BPP_DIGITAL_ADMIN_CMS_PAGE_SHOW_IN_SITEMAP_FIELD" BPP checkbox to "EC_CMS_PAGE_META_DESCRIPTIION_TWO"
#    When I fill the "BPP_DIGITAL_ADMIN_CMS_PAGE_LINK_TITLE_FIELD" BPP field with "Automatiion Link Title"
#    When I capture data from "BPP_DIGITAL_ADMIN_CMS_PAGE_LINK_TITLE_FIELD" BPP element into "EC_CMS_LINK_TITLE_TWO" variable
#    When I fill the "BPP_DIGITAL_ADMIN_CMS_PAGE_LINK_DESCRIPTION_FIELD" BPP field with "Automation Link Description"
#    When I capture data from "BPP_DIGITAL_ADMIN_CMS_PAGE_LINK_DESCRIPTION_FIELD" BPP element into "EC_CMS_LINK_DESCRIPTION_TWO" variable
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_BLOG_POST_SAVE_BUTTON"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_BLOG_POST_ALERT_MESSAGE" contains text "CONTAINS=CMS Page was successfully created."
#
#    #----------------------------Business Process - BPP Digital Publish CMS Page----------------------------
#    When I execute "window.scrollTo(0,0)" JS script for BPP
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_BLOG_POST_PUBLISH_BUTTON"
#    When I wait for "1" seconds at BPP page
#    When I confirm BPP JS Alert "Are you sure want to publish this page?"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_BLOG_POST_ALERT_MESSAGE" contains text "CONTAINS=Page successfully published."
#
#    #----------------------------Business Process - BPP Digital Validate CMS Page Components Availability----------------------------
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_CMS_PAGE_ADD_COMPONENT_DROPDOWN"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_BLOCKQUOTE_WITH_CAPTION_OPTION" contains text "Blockquote With Caption"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_CALL_OUT_OPTION" contains text "Call Out"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_CIRCLES_OPTION" contains text "Circles"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_DOWNLOAD_CARDS_OPTION" contains text "Download Cards"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_FULL_WIDTH_TEXT_BLOCK_OPTION" contains text "Full Width Text Block"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_IMAGE_TEXT_BUTTON_OPTION" contains text "Image Text Button"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_IMAGES_OPTION" contains text "Images"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_LOGO_CREDS_OPTION" contains text "Logo Creds"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_PEOPLE_OPTION" contains text "People"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_QUICK_LINKS_OPTION" contains text "Quick Links"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_SPOTLIGHT_OPTION" contains text "Spotlight"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_VIDEO_OPTION" contains text "Video"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_VIDEO_SPOTLIGHT_OPTION" contains text "Video Spotlight"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_CMS_PAGE_ADD_COMPONENT_DROPDOWN"
#
#    #----------------------------Business Process - BPP Digital Add Blockquote With Caption Component----------------------------
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_CMS_PAGE_ADD_COMPONENT_DROPDOWN"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_CMS_PAGE_BLOCKQUOTE_WITH_CAPTION_OPTION"
#    When I fill the "BPP_DIGITAL_ADMIN_BLOCKQUOTE_DESCRIPTION_TEXTAREA" BPP field with "Lorem ipsum Blockquote"
#    When I capture data from "BPP_DIGITAL_ADMIN_BLOCKQUOTE_DESCRIPTION_TEXTAREA" BPP element into "EC_BLOCKQUOTE_CONTENT" variable
#    When I fill the "BPP_DIGITAL_ADMIN_BLOCKQUOTE_CAPTION_FIELD" BPP field with "BlockquoteCaption<KW_AUTO_FIRSTNAME>"
#    When I capture data from "BPP_DIGITAL_ADMIN_BLOCKQUOTE_CAPTION_FIELD" BPP element into "EC_BLOCKQUOTE_CAPTION" variable
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_BLOCKQUOTE_SELECT_IMAGE_BUTTON"
#    When I switch to "2" BPP window
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_MAGIC_CIRCLE_FIRMS_SELECT_BUTTON"
#    When I switch to "0" BPP window
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_BLOG_POST_SAVE_BUTTON"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_CMS_PAGE_COMPONENTS_FIRST_ITEM" contains text "CONTAINS=Blockquote with caption"
#
#    #----------------------------Business Process - Admin Logout----------------------------
#    When I click on the BPP element located by "DIRECTAPP_ADMIN_USER_ICON_AREA"
#    When I click on the BPP element located by "DIRECT_APP_ADMIN_LOGOUT_LINK"
#
#    #----------------------------Business Process - Direct App Student Registration----------------------------
#    Given I am on "MD_COMMON_LINKS_BUILDEMPIREURL" BPP URL
#    When I click on the BPP element located by "DIRECT_APP_REGISTER_LINK_LOGIN_PAGE"
#    When I fill the "DIRECT_APP_FIRST_NAME_FIELD" BPP field with "Auto<KW_AUTO_FIRSTNAME>"
#    When I capture data from "DIRECT_APP_FIRST_NAME_FIELD" BPP element into "EC_FIRSTNAME" variable
#    When I fill the "DIRECT_APP_SURNAME_FIELD" BPP field with "Auto<KW_AUTO_LASTNAME>"
#    When I capture data from "DIRECT_APP_SURNAME_FIELD" BPP element into "EC_LASTNAME" variable
#    When I SmartClient click on BPP element "CleanUp_DIRECTAPP_ACCEPT_AND_CLOSE_COOKIES_TOP_RIBBON_BUTTON"
#    When I fill the "DIRECT_APP_EMAIL_FIELD" BPP field with "Auto<KW_AUTO_FIRSTNAMEFILE><KW_AUTO_LASTNAMEFILE>@harakirimail.com"
#    When I capture data from "DIRECT_APP_EMAIL_FIELD" BPP element into "EC_EMAIL_ADDRESS" variable
#    When I fill the "DIRECT_APP_TELEPHONE_NUMBER_FIELD" BPP field with "0913<KW_AUTO_RANDOMNUMBER|#######>"
#    When I fill the "DIRECT_APP_PASSWORD_FIELD" BPP field with "A@polloGlobal2018"
#    When I capture data from "DIRECT_APP_PASSWORD_FIELD" BPP element into "EC_PASSWORD" variable
#    When I click on the BPP element located by "DIRECT_APP_I_AM_HAPPY_TO_RECEIVE_INFORMATION_FROM_BPP_RADIO_BTN"
#    When I click on the BPP element located by "DIRECT_APP_REGISTER_BUTTON"
#
#    #----------------------------Business Process - Validate CMS Page on the Hub----------------------------
#    When I verify that BPP element "STUDENT_BUILD_EMPIRE_PARENT_CMS_PAGE_ON_LEFT_NAV_BAR" contains text "EC_CMS_PAGE_NAME_ONE"
#    When I click on the BPP element located by "STUDENT_BUILD_EMPIRE_PARENT_CMS_PAGE_ON_LEFT_NAV_BAR"
#    When I wait for "3" seconds at BPP page
#    When I verify that BPP element "STUDENT_APPLICATION_FIRST_LEVEL_BREADCRUMBS_LINK" contains text "Home"
#    When I verify that BPP element "STUDENT_APPLICATION_SECOND_LEVEL_BREADCRUMBS_LINK" contains text "EC_CMS_PAGE_NAME_ONE"
#    When I verify that BPP element "STUDENT_BUILD_EMPIRE_BLOCKQUOTE_ELEMENT_ON_CMS" contains text "Lorem ipsum Blockquote"
#    When I verify that BPP element "STUDENT_BUILD_EMPIRE_CHILD_CMS_PAGE_ON_LEFT_NAV_BAR" contains text "EC_CMS_PAGE_NAME_TWO"
#    When I click on the BPP element located by "STUDENT_BUILD_EMPIRE_CHILD_CMS_PAGE_ON_LEFT_NAV_BAR"
#    When I wait for "3" seconds at BPP page
#    When I verify that BPP element "STUDENT_APPLICATION_FIRST_LEVEL_BREADCRUMBS_LINK" contains text "Home"
#    When I verify that BPP element "STUDENT_APPLICATION_SECOND_LEVEL_BREADCRUMBS_LINK" contains text "EC_CMS_PAGE_NAME_ONE"
#    When I verify that BPP element "STUDENT_APPLICATION_THIRD_LEVEL_BREADCRUMBS_LINK" contains text "EC_CMS_PAGE_NAME_TWO"
#    When I verify that BPP element "STUDENT_BUILD_EMPIRE_BLOCKQUOTE_ELEMENT_ON_CMS" contains text "Lorem ipsum Blockquote"
#
#    #----------------------------Business Process - Direct App Student Logout----------------------------
#    When I click on the BPP element located by "DIRECTAPP_USER_ICON_MENU"
#    When I click on the BPP element located by "DIRECTAPP_LOGOUT_LINK"
#
#    #----------------------------Business Process - Login Admin BuildEmpire----------------------------
#    Given I am on "MD_COMMON_LINKS_BUILDEMPIREADMINURL" BPP URL
#    When I fill the "DIRECTAPP_ADMIN_EMAIL_TEXT_FIELD" BPP field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINUSER"
#    When I fill the "DIRECTAPP_ADMIN_PASSWORD_TEXT_FIELD" BPP field with "MD_COMMON_CREDENTIALS_BUILDEMPIRENEILADMINPASSWORD"
#    When I click on the BPP element located by "DIRECT_APP_LOGIN_BUTTON"
#
#    #----------------------------Business Process - BPP Digital Nvigate CMS Page----------------------------
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_WEBSITE_DROPDOWN"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_VLE_CMS_PAGES_LINK"
#
#    #----------------------------Business Process - BPP Digital Delete Automation CMS Page----------------------------
#    When I execute "window.scrollTo(0,0)" JS script for BPP
#    When I fill the "BPP_DIGITAL_ADMIN_SEARCH_BY_NAME_FIELD" BPP field with "EC_CMS_PAGE_NAME_ONE"
#    When I press the "ENTER" key on the keyboard for BPP
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_CMS_PAGE_EDIT_FIRST_ITEM_BUTTON"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_ARCHIVE_BUTTON"
#    When I click on the BPP element located by "BPP_DIGITAL_CONFIRM_ARCHIVE_BUTTON"
#    When I wait for "2" seconds at BPP page
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_BLOG_POST_ALERT_MESSAGE" contains text "CONTAINS=Page successfully archived."
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_DELETE_BUTTON"
#    When I wait for "2" seconds at BPP page
#    When I confirm BPP JS Alert "Are you sure?"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_BLOG_POST_ALERT_MESSAGE" contains text "CONTAINS=CMS Page was successfully removed."
#    When I execute "window.scrollTo(0,0)" JS script for BPP
#    When I fill the "BPP_DIGITAL_ADMIN_SEARCH_BY_NAME_FIELD" BPP field with "EC_CMS_PAGE_NAME_TWO"
#    When I press the "ENTER" key on the keyboard for BPP
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_CMS_PAGE_EDIT_FIRST_ITEM_BUTTON"
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_ARCHIVE_BUTTON"
#    When I click on the BPP element located by "BPP_DIGITAL_CONFIRM_ARCHIVE_BUTTON"
#    When I wait for "2" seconds at BPP page
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_BLOG_POST_ALERT_MESSAGE" contains text "CONTAINS=Page successfully archived."
#    When I click on the BPP element located by "BPP_DIGITAL_ADMIN_DELETE_BUTTON"
#    When I wait for "2" seconds at BPP page
#    When I confirm BPP JS Alert "Are you sure?"
#    When I verify that BPP element "BPP_DIGITAL_ADMIN_BLOG_POST_ALERT_MESSAGE" contains text "CONTAINS=CMS Page was successfully removed."
