// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class FuncionalidadesTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void funcionalidades() {
    driver.get("http://localhost:8090/SistemaForum/");
    driver.manage().window().setSize(new Dimension(584, 697));
    driver.findElement(By.id("login")).click();
    driver.findElement(By.id("login")).sendKeys("aluno");
    driver.findElement(By.id("senha")).click();
    driver.findElement(By.id("senha")).sendKeys("aluno");
    driver.findElement(By.cssSelector(".bg-blue-500")).click();
    driver.findElement(By.linkText("Ir para a Tela de Inserir Tópico")).click();
    driver.findElement(By.id("titulo")).click();
    driver.findElement(By.id("titulo")).click();
    driver.findElement(By.id("titulo")).click();
    {
      WebElement element = driver.findElement(By.id("titulo"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.id("titulo")).click();
    driver.findElement(By.id("titulo")).sendKeys("Topico 2 Aluno");
    driver.findElement(By.id("conteudo")).click();
    driver.findElement(By.id("conteudo")).click();
    driver.findElement(By.id("conteudo")).click();
    driver.findElement(By.id("conteudo")).sendKeys("Topico 2 Aluno");
    driver.findElement(By.cssSelector(".bg-blue-500")).click();
    driver.findElement(By.linkText("Ir para a Tela Ranking")).click();
    {
      WebElement element = driver.findElement(By.cssSelector("html"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector("html"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector("html"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.linkText("Voltar para Tela Tópicos")).click();
  }
}
