package com.magento.softwaretestingboard.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

public class EcommerceShoppingFlowTest extends BaseTest {


    //Test 1: Register an Account
    @Test(priority = 1)
    public void registerAccount() {
            homePage.navigateToHomePage();
            // Assert that the user is on 'HomePage' page
            Assert.assertTrue(homePage.isOnHomePage(),
                    "The user is not on the 'HomePage' page.");

            homePage.clickCreateAccount();

            // Generate unique First and Last Names for each test run
            createAccountPage.enterFirstName(getRandomFirstName);
            createAccountPage.enterLastName(getRandomLastName);

            // Generate unique email and password for each test run
            createAccountPage.enterEmail(getRandomEmail);
            createAccountPage.enterPassword(getRandomPassword);
            createAccountPage.enterConfirmPassword(getRandomPassword);

            createAccountPage.clickCreateAccountButton();

            // Assert successful registration and redirection to the 'My Account' page.
            Assert.assertEquals(myAccountPage.getSuccessMessage(), "Thank you for registering with Main Website Store.",
                    "Registration was not successful. Expected success message is missing.");
            Assert.assertTrue(myAccountPage.isOnMyAccountPage(),
                    "After successful registration, the user was not redirected to the 'My Account' page.");

            // Assert the presence and accuracy of user 'Account Information'
            // Using '.replaceAll("\\s+", " ").trim()' to remove line break between the last name and email, and ensure a consistent format for comparison.
            Assert.assertEquals(myAccountPage.getAccountContactInformation().replaceAll("\\s+", " ").trim(),
                    getRandomFirstName + " " + getRandomLastName + " " + getRandomEmail,
                    "The 'Account Information' displayed does not match the expected user information.");

            // Assert the presence of the "Sign Out" link on the Customer Menu.
            Assert.assertEquals(myAccountPage.isSignOutLinkVisible(), "Sign Out",
                    "The 'Sign Out' link is NOT present on the Customer Menu.");
            System.out.println("Login is Successful....");

        }

}