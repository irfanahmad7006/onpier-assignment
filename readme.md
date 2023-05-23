selenium-cucumber-java-maven
=================

This is an assignment task from Onpier.de
It is cross-platform, open source and free.
It allows you to automate your test cases with minimal coding.

Writing a test
--------------
* The cucumber features goes in the `featuresFiles` library and should have the ".feature" extension.
* You can start out by looking at `src/test/resources/featureFiles/OnPier_Assignment.feature`. You can extend this feature or make your own features using some of the predefined steps that comes with selenium-cucumber.

Execution
-----------------
* You can start execution from `featuresFiles/Task 5 Automation framework.feature` directly for the feature execution.
* You can start execution from `src/test/java/runner/RunTest.java` for sequential execution.

Test-Data & Config
-----------------
* `Note:` Please provide the vehicle registration images path in the `config.properties` file otherwise all test will fail on new machines.
*  You can set the browser and application url from `config.properties` file.


Maven Dependencies
-----------------
* cucumber-java - 6.11.0
* cucumber-junit - 6.11.0
* junit - 4.13.2
* webdrivermanager - 5.3.0
* selenium-java - 4.8.2
