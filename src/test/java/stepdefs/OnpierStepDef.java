package stepdefs;

import application.OnpierApp;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.ConfigFileReader;

import java.awt.*;
import java.util.Properties;

public class OnpierStepDef {
    private ConfigFileReader configReader = new ConfigFileReader();
    ;
    private Properties prop = configReader.initiateProperties();
    ;
    private OnpierApp onpier = new OnpierApp(DriverFactory.getDriver());

    @Given("User is on Onpier home page with title {string}")
    public void user_is_on_onpier_home_page_with_title(String title) {
        String expectedTitle = onpier.getTitle();
        Assert.assertEquals(expectedTitle, title);
    }

    @Given("User selects the vehicle class type {string}")
    public void user_selects_the_vehicle_class_type(String vehicleClass) {
        onpier.selectFahrzeugklassen(vehicleClass);
    }

    @Given("User selects the priemium model {string}")
    public void user_selects_the_priemium_model(String premium) {
        onpier.selectPrämienmodell(premium);
    }

    @When("User uploads vehicle registration details and click next button")
    public void user_uploads_vehicle_registration_details_and_click_next_button() throws InterruptedException, AWTException {
        onpier.uploadRegistrationDetails(prop.getProperty("registrationFirst"), prop.getProperty("registrationLast"));
//        Thread.sleep(10000000);
    }

    @When("User selects a radio button based on {string}")
    public void user_selects_a_radio_button_based_on(String custType) {
        onpier.userSelectsCustomerType(custType);
    }

    @Then("User selects salutation based on {string}")
    public void user_selects_salutation_based_on(String salutation) {
        onpier.userSelectSalutation(salutation);
    }

    @Then("User enters {string} as first name and {string} as last name")
    public void user_enters_as_first_name_and_as_last_name(String fnm, String lnm) {
        onpier.setFirstName(fnm);
        onpier.setLastName(lnm);

    }

    @Then("User enters {string} as first name and {string} as last name and email id {string}")
    public void user_enters_as_first_name_and_as_last_name_and_email_id(String fnm, String lnm, String email) {
        onpier.setFirstName(fnm);
        onpier.setLastName(lnm);
        onpier.setEmail(email);

    }

    @Then("User enters bank details {string} owner name and {string} iban")
    public void user_enters_bank_details_owner_name_and_iban(String acountOwnNm, String iBan) {
        onpier.setAccountOwn(acountOwnNm);
        onpier.setIban(iBan);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Then("User enters Company name {string} and VAT {string}")
    public void user_enters_company_name_and_vat(String compNm, String vat) {
        onpier.setCompanyNm(compNm);
        onpier.setVatID(vat);
    }

    @Then("User verifies the company name {string}")
    public void user_verifies_the_company_name(String key) {
        String expErrTxt = prop.getProperty(key);
        String actErrTxt = onpier.getCompNMERR();
        Assert.assertEquals(expErrTxt,actErrTxt);
    }

    @Then("User verifies the vat {string}")
    public void user_verifies_the_vat(String key) {
        String expErrTxt = prop.getProperty(key);
        String actErrTxt = onpier.getVatERR();
        Assert.assertEquals(expErrTxt,actErrTxt);
    }

    @Then("User verifies the first name {string}")
    public void user_verifies_the_first_name(String key) {
        String expErrTxt = prop.getProperty(key);
        String actErrTxt = onpier.getFnERR();
        Assert.assertEquals(expErrTxt,actErrTxt);
    }

    @Then("User verifies the last name {string}")
    public void user_verifies_the_last_name(String key) {
        String expErrTxt = prop.getProperty(key);
        String actErrTxt = onpier.getLnERR();
        Assert.assertEquals(expErrTxt,actErrTxt);
    }

    @Then("User verifies the email {string}")
    public void user_verifies_the_email(String key) {
        String expErrTxt = prop.getProperty(key);
        String actErrTxt = onpier.getEmailERR();
        Assert.assertEquals(expErrTxt,actErrTxt);
    }

    @Then("User verifies the account owner {string}")
    public void user_verifies_the_account_owner(String key) {
        String expErrTxt = prop.getProperty(key);
        String actErrTxt = onpier.getAcntOwnERR();
        Assert.assertEquals(expErrTxt,actErrTxt);
    }

    @Then("User verifies the IBAN {string}")
    public void user_verifies_the_iban(String key) {
        String expErrTxt = prop.getProperty(key);
        String actErrTxt = onpier.getIbanERR();
        Assert.assertEquals(expErrTxt,actErrTxt);
    }


}
