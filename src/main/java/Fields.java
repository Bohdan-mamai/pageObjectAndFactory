import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class Fields {

    @FindBy(xpath = ConfigData.COLORS_FROM_FILTER_MENU)
    public List<WebElement> colorStatisticsList; // rename to colors from filter

    @FindBy(xpath = ConfigData.NUMBER_OF_COLORS)
    public List<WebElement> numberOfColors;

    @FindBy(xpath = ConfigData.COLOR_ON_MAIN_PAGE)
    public List<WebElement> colorFromMainPage;

    private List<Integer> numbersOfColorsFromFilter = new ArrayList<>();
    private List<Integer> numberOfColorsFromMainPage = new ArrayList<>();

    private WebDriver driver;

    Fields(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    Fields openSortableLink(String link){
        driver.get(link);
        return this;
    }

    Fields addNumberOfColorsFromLeftTab(){
        for (int i = 0; i < numberOfColors.size(); i++) {
            String result = numberOfColors.get(i).getText().replaceAll("[(,)]","");
            int number = Integer.parseInt(result);
            numbersOfColorsFromFilter.add(number);
        }
        return this;
    }

    Fields calculateColorsFromMainPage(){
        for (int i = 0; i < colorStatisticsList.size(); i++) {
            int colorsCounter = 0;
            String first = colorStatisticsList.get(i).getAttribute("style").replaceAll("\\s+","");
            for (int j = 0; j < colorFromMainPage.size(); j++) {
                String second = colorFromMainPage.get(j).getAttribute("style").replaceAll("\\s+","");
                if (second.equals(first)){
                    colorsCounter++;
                }
            }
            numberOfColorsFromMainPage.add(colorsCounter);
        }
        return this;
    }

    Fields printColorNumbers(){
        for (int i = 0; i < numberOfColorsFromMainPage.size(); i++) {
            Assert.assertEquals(numbersOfColorsFromFilter.get(i),numberOfColorsFromMainPage.get(i));
            System.out.println(numbersOfColorsFromFilter.get(i) + " = " + numberOfColorsFromMainPage.get(i));
        }
        return this;
    }
}
