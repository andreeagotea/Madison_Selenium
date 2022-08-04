import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static java.lang.ProcessBuilder.Redirect.to;

public class Main {
    WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        Main main = new Main();
        main.Setup();
        main.navigateToHomePage();
//     main.homepage();
//     main.account();
//     main.languages();
//     main.search();
//     main.new_product_list();
//     main.navigateToPage("SALE");
//     main.addProductToCard();
//     main.removeProductToCart();
     main.registerNewUser();
    }

    public void homepage() throws InterruptedException {

        String title = driver.getTitle();
        System.out.println(title);

        String url = driver.getCurrentUrl();
        System.out.println(url);

        WebElement element = driver.findElement(By.className("logo"));
        element.click();
        Thread.sleep(3000);

        driver.navigate().to("http://qa1magento.dev.evozon.com/accessories.html");
        Thread.sleep(3000);

        driver.navigate().back();
        Thread.sleep(3000);

        driver.navigate().forward();
        Thread.sleep(3000);

        driver.navigate().refresh();

        driver.quit();
    }

    public void Setup() {

        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        driver = new ChromeDriver();

    }

    public void navigateToHomePage() {

        driver.get("http://qa2magento.dev.evozon.com/");

    }

    public void account() throws InterruptedException {

        WebElement element = driver.findElement(By.cssSelector(".skip-link.skip-account"));
        element.click();
        Thread.sleep(3000);

        driver.quit();

    }

    public void languages() throws InterruptedException {

        WebElement element = driver.findElement(By.cssSelector("#select-language"));
        element.click();
        Thread.sleep(3000);

        Select oSelect = new Select(driver.findElement(By.cssSelector("#select-language")));
        List<WebElement> elementCount = oSelect.getOptions();
        System.out.println(elementCount.size());
        Thread.sleep(3000);

        Select select = new Select(element);
        select.selectByIndex(2);

        driver.quit();
    }

    public void search() throws InterruptedException {

        WebElement element = driver.findElement(By.id("search"));
        element.sendKeys("dhbwdbwey");
        Thread.sleep(3000);
        element.clear();

        element.sendKeys("woman");
        element.submit();

        driver.quit();

    }

    public void new_product_list() throws InterruptedException {

        List<WebElement> elementCount = driver.findElements(By.cssSelector(".item.last"));
        System.out.println(elementCount.size());

        WebElement element = driver.findElement(By.className("product-name"));
        for (WebElement element_list : elementCount) {
            String linkText = element_list.getText().split("\n")[0]; //split - pentru a lua doar numele
            System.out.println(linkText);
        }

        driver.quit();

    }

    public void navigateToPage(String pageName) {

        //identifica lista ce trebuie parcursa
        List<WebElement> pageList = driver.findElements(By.cssSelector("#header-nav #nav li.level0")); //elem de tip ol care are clasa primary
        //parcurgerea listei
        for (WebElement page : pageList) {
            System.out.println(page.getText());
            // cauta elementul "Sale"
            if (page.getText().equals(pageName)) {
                page.click();
                break;
            }

        }
//        for(int i=0; i<pageList.size(); i++)
//        {
//            if(pageList.get(i).getText().equals(pageName)) {
//                pageList.get(i).click();
//                break;
//            }
//        }
        //driver.quit();
    }

    public void addProductToCard() throws InterruptedException {

        navigateToPage("VIP");

        Thread.sleep(3000);

        WebElement element = driver.findElement(By.id("product-collection-image-437"));
        element.click();

        WebElement element1 = driver.findElement(By.id("swatch22"));
        element1.click();

        WebElement element2 = driver.findElement(By.id("qty"));
        element2.clear();
        element2.sendKeys("2");

        WebElement element4 = driver.findElement(By.className("add-to-cart-buttons"));
        element4.click();

        Thread.sleep(2000);

    }

        public void removeProductToCart () throws InterruptedException {

            addProductToCard();

            navigateToPage("VIP");


            WebElement element = driver.findElement(By.id("product-collection-image-373"));
            element.click();

            WebElement element2 = driver.findElement(By.id("qty"));
            element2.clear();
            element2.sendKeys("4");

            WebElement element4 = driver.findElement(By.className("add-to-cart-buttons"));
            element4.click();

            WebElement removeProduct = driver.findElement(By.id("shopping-cart-table")).findElement(By.xpath("./tbody/tr/td[6]/a"));
            removeProduct.click();

            driver.quit();

        }

//
    public void registerNewUser() throws InterruptedException {

        account();

        WebElement myAccoutButton =driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(5) > a"));
        myAccoutButton.click();

        WebElement fillName = driver.findElement(By.id("firstname"));
        fillName.sendKeys("Gotea");

        WebElement fillLastName = driver.findElement(By.id("lastname"));
        fillLastName.sendKeys("Andreea");

        WebElement fillEmail= driver.findElement(By.id("email_address"));
        fillEmail.sendkeys("goteaandreea123@yahoo.com");

        WebElement fillPassword = driver.findElement(By.id("password"));
        fillPassword.sendKeys("1234");

        WebElement fillConfirmPassword = driver.findElement(By.id("confirmation"));
        fillConfirmPassword.sendKeys("1234");

        WebElement registerButton = driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button"));
        registerButton.click();
        driver.quit();

    }

    }




