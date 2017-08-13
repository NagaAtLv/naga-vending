Naga-Vending Machine

This is a implementation of the below requirement

User Story:

Part 1  ->  Implement the logic for a vending machine that will return the optimal change for a given number of pence.
Part 2  ->  Implement a method to get the change for a given number of pence based on a limited supply of coins. 





-- Project Dependencies I have used --- Java 1.8 Junit 4.11

I have created Simple Gradle project that uses Maven repository to download dependencies.
in-order to run Gradle project we should have "GRADLE_HOME" set in our System properties
Gradle project can run with below commands

Building an Application: gradle clean build
Testing an Application gradle test.

I have created Coin enum to comprise [ONE_POUND(100), FIFTY_PENCE(50), TWENTY_PENCE(20), TEN_PENCE(10), FIVE_PENCE(
			5), TWO_PENCE(2), ONCE_PENNY(1)]denomination's			
and i have created ChangeBuilderService to provide implementation of Vending Machine requirement as specified in requirement.

I have written JUnit test class [ChangeBuilderServiceTest] for ChangeBuilderService to test all possible Scenario's.

I have created inventory properties files under resources folder one for normail application one for Unit testing.

I have also created InsufficientCoinsException class, Service throws this exception when Vending machine unable to supply required coins.



 
