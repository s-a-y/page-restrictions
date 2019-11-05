package tests;

import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PublishedPage;
import pages.RestrictionsPopUp;

import static org.junit.Assert.*;

public class ModifyRestrictionsTest extends BaseTest {

    @Test
    public void myFirstTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLogin();
        HomePage homePage = loginPage.login(System.getProperty("test.email1"), System.getProperty("test.password1"));
        // driver.get("https://nvyu.atlassian.net/wiki/spaces/TESTSPACE/pages/65575/bla2");
        PublishedPage page = homePage.navigateToPublishedPage(System.getProperty("test.page_url"));

        //initial check

        assertTrue(page.isRestrictionButtonDisplayed());
        RestrictionsPopUp popup = page.clickRestrictionButton();
        assertTrue(popup.isEditRestrictionsDropDownDisplayed());
        assertTrue(popup.isCancelButtonEnabled());

        // remove all restrictions/

        page = popup.setPermissions("Anyone can view and edit");

        //check that restrictions are set to "Anyone can view and edit"
        assertTrue(page.isRestrictionButtonDisplayed());
        popup = page.clickRestrictionButton();
        assertEquals("Anyone can view and edit", popup.checkSelectedPermissions());

        //set new permissions
        page = popup.setUserRestrictions("n5", "Viewing and editing restricted", "Can view and edit");

        //check new permissions as admin user
        assertTrue(page.isRestrictionButtonDisplayed());
        popup = page.clickRestrictionButton();
        popup.isUserAdded("n5");
        assertEquals("Can view and edit", popup.checkAddedUsePermissions("n5"));
        page = popup.clickCancelButton();

        //login with added user and check that he can access the page

        page.logOut();

        homePage = loginPage.login(System.getProperty("test.email2"), System.getProperty("test.password2"));
        page = homePage.navigateToPublishedPage(System.getProperty("test.page_url"));
        assertEquals("bla2", page.title());

        //login with user who was not added - not able to access the page

        page.logOut();
        homePage = loginPage.login(System.getProperty("test.email3"), System.getProperty("test.password3"));
        page = homePage.navigateToPublishedPage(System.getProperty("test.page_url"));
        assertTrue(page.isRestrictedTitleShown());
    }
}
