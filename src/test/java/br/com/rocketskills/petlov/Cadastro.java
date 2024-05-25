package br.com.rocketskills.petlov;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

class Cadastro {

	WebDriver driver;

	@BeforeEach
	void start() {
	  driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@AfterEach
	void finish() {
		driver.close();
	}

	@Test
	@DisplayName("Deve poder cadastrar um ponto de doação")
	void createPoint() {

		driver.get("https://petlov.vercel.app/signup");
		WebElement title = driver.findElement(By.cssSelector("h1"));

		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(d -> title.isDisplayed());

		assertEquals("Cadastro de ponto de doação", title.getText(), "Verificando o título da página de cadastro");
		
		WebElement name =	driver.findElement(By.cssSelector("input[name='name']"));
		name.sendKeys("Kamilla Point");
		
		WebElement email =	driver.findElement(By.cssSelector("input[name='email']"));
		email.sendKeys("kamilla@email.com");
		
		WebElement cep = driver.findElement(By.cssSelector("input[name='cep']"));
		cep.sendKeys("88111380");

		WebElement cepButton = driver.findElement(By.cssSelector("input[value='Buscar CEP']"));
		cepButton.click();

		WebElement addressNumber = driver.findElement(By.cssSelector("input[name='addressNumber']"));
		addressNumber.sendKeys("1234");

		WebElement addressDetails = driver.findElement(By.cssSelector("input[name='addressDetails']"));
		addressDetails.sendKeys("Ao lado da padaria");

		driver.findElement(By.xpath("//span[text()=\"Cachorros\"]/..")).click();

		driver.findElement(By.className("button-register")).click();

		WebElement result = driver.findElement(By.cssSelector("#success-page p"));

		Wait<WebDriver> waitResult = new WebDriverWait(driver, Duration.ofSeconds(2));
		waitResult.until(d -> result.isDisplayed());

		String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";

		assertEquals(target, result.getText(), "Verificando mensagem de sucesso");
	}
}
