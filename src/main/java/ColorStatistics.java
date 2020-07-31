import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;

public class ColorStatistics {

    private WebDriver driver;

    private DesiredCapabilities capabilities = DesiredCapabilities.chrome();

    @BeforeTest(alwaysRun = true)
    public void browserSetup() {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:\\Users\\Bohdan_Mamai\\Desktop\\ONAPP\\Package2_shared_cart\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        ChromeOptions options = new ChromeOptions();
        options.merge(capabilities);
        driver = new ChromeDriver(service, options);
    }

    @Test(description = "Just first test, JIRA binding can be here")
    public void openSortable() {
        Fields fields = new Fields(driver); // rename fields

        fields.openSortableLink(ConfigData.PAGE_DRESS)
                .addNumberOfColorsFromLeftTab()
                .calculateColorsFromMainPage()
                .printColorNumbers();
    }


    @AfterTest(alwaysRun = true)
    public void browseTearDown() {
        driver.quit();
    }

}
