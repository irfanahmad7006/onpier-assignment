package application;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigFileReader;
import utils.SelUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.temporal.ValueRange;
import java.util.Locale;
import java.util.Properties;

public class OnpierApp {
    public OnpierApp(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;
    private ConfigFileReader configReader = new ConfigFileReader();
    private Properties prop = configReader.initiateProperties();

    //Fahrzeugklassen
    By ePKW = By.xpath("//span[contains(text(),'E-PKW')]");
    By eZweiräder = By.xpath("//span[contains(text(),'E-Zweiräder')]");
    By eTransporter = By.xpath("//span[contains(text(),'E-Transporter')]");


    //Prämienmodelle
    By sofortPrämie = By.xpath("//p[@class='text-xl font-bold text-center underline-card-title mt-[30px] h-auto p-0'][contains(text(),'Sofort-Prämie')]/following-sibling::div[2]/div/button/span[contains(text(),'Prämie beantragen')]");
    By flexPrämie = By.xpath("//p[@class='text-xl font-bold text-center underline-card-title mt-[30px] h-auto p-0'][contains(text(),'Flex-Prämie')]/following-sibling::div[2]/div/button/span[contains(text(),'Prämie beantragen')]");

    //Fahrzeugschein Vorderseite
    By fahrzeugscheinVorderseite = By.xpath("//app-file-upload[@filename='teil1Page1']/label[2]/div");

    //Fahrzeugschein Rückseite
    By fahrzeugscheinRückseite = By.xpath("//app-file-upload[@filename='teil1Page2']/label[2]/div");

    By btnWeiter = By.xpath("//app-step1[@class='ng-star-inserted']//div[@class='flex justify-center items-center'][normalize-space()='Weiter']");

    By privatPerson = By.xpath("//input[@id='mat-radio-2-input']");
    By unternehmen = By.xpath("//label[@for='mat-radio-3-input']");

    //Salutations
    By anrede = By.xpath("//div[@id='mat-select-value-1']");
    By herr = By.xpath("//mat-option[@id='mat-option-0']");
    By frau = By.xpath("//mat-option[@id='mat-option-1']");
    By divers = By.xpath("//mat-option[@id='mat-option-2']");

    By fName = By.xpath("//input[@placeholder='Max']");
    By lname = By.xpath("//input[@placeholder='Mustermann']");
    By email = By.xpath("//input[@placeholder='max.mustermann@muster.de']");
    By accountOwnerName = By.xpath("//input[@placeholder='Max Mustermann']");
    By iban = By.xpath("//input[@placeholder='z.B. DE45 7890 8965 5643 3454 00']");

    By companyNm = By.xpath("//input[@placeholder='Musterunternehmen GmbH']");
    By vatID = By.xpath("//input[@placeholder='z.B. DE123456789']");

    By compNMERR = By.xpath("//p[contains(text(),'Bitte geben Sie Ihren Unternehmensnamen inkl. Unte')]");
    By vatERR = By.xpath("//p[contains(text(),'Bitte geben Sie eine gültige Umsatzsteuer-ID oder ')]");
    By fnERR = By.xpath("//p[normalize-space()='Bitte geben Sie Ihren Vornamen ein.']");
    By lnERR = By.xpath("//p[normalize-space()='Bitte geben Sie Ihren Nachnamen ein.']");
    By emailERR = By.xpath("//p[normalize-space()='Bitte geben Sie eine gültige E-Mail-Adresse ein.']");
    By acntOwnERR = By.xpath("//p[contains(text(),'Bitte geben Sie Vor- und Nachname des Kontoinhaber')]");
    By ibanERR = By.xpath("//p[contains(text(),'Bitte geben Sie eine vollständige IBAN ein (z. B. ')]");

    public String getTitle() {
        return driver.getTitle();
    }

    public void selectFahrzeugklassen(String vehicleClass) {

        if (vehicleClass.equalsIgnoreCase("E-PKW")) {
            SelUtils.explicitWaitClickable(driver, ePKW);
            driver.findElement(ePKW).click();
        } else if (vehicleClass.equalsIgnoreCase("E-Zweiräder")) {
            SelUtils.explicitWaitClickable(driver, eZweiräder);
            driver.findElement(eZweiräder).click();
        } else if (vehicleClass.equalsIgnoreCase("E-Transporter")) {
            SelUtils.explicitWaitClickable(driver, eTransporter);
            driver.findElement(eTransporter).click();
        } else {
            try {
                throw new Exception("Please enter the correct vehicle class as mention on the application");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void selectPrämienmodell(String priemium) {
        if (priemium.equalsIgnoreCase("Flex-Prämie")) {
            SelUtils.explicitWaitClickable(driver, flexPrämie);
            driver.findElement(flexPrämie).click();
        } else if (priemium.equalsIgnoreCase("Sofort-Prämie")) {
            SelUtils.explicitWaitClickable(driver, sofortPrämie);
            driver.findElement(sofortPrämie).click();
        } else {
            try {
                throw new Exception("Please enter the correct premium model as mentioned on the application");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadRegistrationDetails(String fPath, String lPath) throws AWTException, InterruptedException {
        SelUtils.explicitWaitVisible(driver, fahrzeugscheinVorderseite);
        driver.findElement(fahrzeugscheinVorderseite).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Robot rb = new Robot();

        StringSelection firstPage = new StringSelection(fPath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(firstPage, null);

        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

        driver.findElement(fahrzeugscheinRückseite).click();
        Thread.sleep(3000);

        StringSelection lastPage = new StringSelection(lPath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(lastPage, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        SelUtils.explicitWaitVisible(driver, btnWeiter);
        driver.findElement(btnWeiter).click();
    }

    public void userSelectsCustomerType(String individualOrBusiness) {
        if (individualOrBusiness.equalsIgnoreCase("Privatperson")) {
            driver.findElement(privatPerson).click();
        } else if (individualOrBusiness.equalsIgnoreCase("Unternehmen")) {
            driver.findElement(unternehmen).click();
        } else {
            try {
                throw new Exception("Please enter the correct Customer Type as mentioned on the application");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void userSelectSalutation(String salutation) {
        SelUtils.explicitWaitClickable(driver, anrede);
        driver.findElement(anrede).click();
        if (salutation.equalsIgnoreCase("Herr")) {
            SelUtils.explicitWaitVisible(driver, herr);
            driver.findElement(herr).click();
        } else if (salutation.equalsIgnoreCase("Frau")) {
            SelUtils.explicitWaitVisible(driver, frau);
            driver.findElement(frau).click();
        } else if (salutation.equalsIgnoreCase("Drivers")) {
            SelUtils.explicitWaitVisible(driver, divers);
            driver.findElement(divers).click();
        } else {
            try {
                throw new Exception("Please enter the correct Salutation as mentioned on the application");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setFirstName(String fnm) {
        SelUtils.explicitWaitVisible(driver, fName);
        driver.findElement(fName).sendKeys(fnm);
    }

    public void setLastName(String lnm) {
        SelUtils.explicitWaitVisible(driver, lname);
        driver.findElement(lname).sendKeys(lnm);
    }

    public void setEmail(String email) {
        SelUtils.explicitWaitVisible(driver, this.email);
        driver.findElement(this.email).sendKeys(email);
    }

    public void setAccountOwn(String accOwner) {
        SelUtils.explicitWaitVisible(driver, this.accountOwnerName);
        driver.findElement(this.accountOwnerName).sendKeys(accOwner);
    }

    public void setIban(String ibn) {
        SelUtils.explicitWaitVisible(driver, iban);
        driver.findElement(iban).sendKeys(ibn);
        driver.findElement(fName).click();
    }

    public void setCompanyNm(String compName) {
        driver.findElement(companyNm).sendKeys(compName);
    }

    public void setVatID(String vat) {
        driver.findElement(vatID).sendKeys(vat);
    }



    public String getCompNMERR() {
        return driver.findElement(compNMERR).getText();
    }

    public String getVatERR() {
        return driver.findElement(vatERR).getText();
    }

    public String getFnERR() {
        return driver.findElement(fnERR).getText();
    }

    public String getLnERR() {
        return driver.findElement(lnERR).getText();
    }

    public String getEmailERR() {
        return driver.findElement(emailERR).getText();
    }

    public String getAcntOwnERR() {
        return driver.findElement(acntOwnERR).getText();
    }

    public String getIbanERR() {
        return driver.findElement(ibanERR).getText();
    }

}