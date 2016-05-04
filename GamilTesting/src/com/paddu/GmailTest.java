package com.paddu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GmailTest {
	WebDriver driver;
@BeforeClass
public void openBrowser()
{
	driver=new FirefoxDriver();
	driver.get("http://www.gmail.com");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(3l,TimeUnit.SECONDS);
}
@Test(enabled=false)
public void signInGmail() throws InterruptedException
{
	//Enter User Name and lick Next
	driver.findElement(By.xpath("//*[@id='Email']")).clear();;
	driver.findElement(By.xpath("//*[@id='Email']")).sendKeys("pr.alla786");
	driver.findElement(By.name("signIn")).click();
	//Enter Password and Click SignIn button 
	driver.findElement(By.xpath("//*[@id='Passwd']")).clear();
	driver.findElement(By.xpath("//*[@id='Passwd']")).sendKeys("99514543");
	driver.findElement(By.id("signIn")).click();
	Thread.sleep(5000l);
	//Verify Whether Inbox is present or Not
	Assert.assertTrue(driver.findElements(By.partialLinkText("Inbox")).size()>0, "Inbox should be Exist");
	driver.manage().timeouts().implicitlyWait(3l,TimeUnit.SECONDS);
	//SignOut 
	driver.findElement(By.cssSelector("span[class='gb_2a gbii']")).click();
	driver.findElement(By.id("gb_71")).click();
	driver.manage().timeouts().implicitlyWait(3l,TimeUnit.SECONDS);
	//Verify, SignIn button present or not after SignOut Gmail
	Assert.assertTrue(driver.findElements(By.id("signIn")).size()>0, "SignIn button should be Exist");
	
	
}
@Test
public void sendAndReceiveEmail() throws InterruptedException
{
	//Enter User Name and lick Next
		driver.findElement(By.xpath("//*[@id='Email']")).clear();;
		driver.findElement(By.xpath("//*[@id='Email']")).sendKeys("pr.alla786");
		driver.findElement(By.name("signIn")).click();
		//Enter Password and Click SignIn button 
		driver.findElement(By.xpath("//*[@id='Passwd']")).clear();
		driver.findElement(By.xpath("//*[@id='Passwd']")).sendKeys("99514543");
		driver.findElement(By.id("signIn")).click();
		Thread.sleep(5000l);
		//Verify Whether Inbox is present or Not
		Assert.assertTrue(driver.findElements(By.partialLinkText("Inbox")).size()>0, "Inbox should be Exist");
		driver.manage().timeouts().implicitlyWait(3l,TimeUnit.SECONDS);
		//Click Compose button
		driver.findElement(By.cssSelector("div[role='button'][gh='cm']")).click();
		//Enter Receiver email address into To Text Area
		driver.findElement(By.cssSelector("textarea[name='to']")).clear();
		driver.findElement(By.cssSelector("textarea[name='to']")).sendKeys("pr.alla786@gmail.com");
		//Enter Subject into Subject Text Area
		final String subject="Text Email to my Self";
		driver.findElement(By.cssSelector("input[name='subjectbox']")).clear();
		driver.findElement(By.cssSelector("input[name='subjectbox']")).sendKeys(subject);
		//Enter Text/Message into Body Area
		final String body="Hello, Tester! Hope you are doing well!";
		driver.findElement(By.cssSelector("div[aria-label='Message Body']")).clear();
		driver.findElement(By.cssSelector("div[aria-label='Message Body']")).sendKeys(body);
		driver.manage().timeouts().implicitlyWait(3l,TimeUnit.SECONDS);
		//Click Send Button
		driver.findElement(By.cssSelector("div[aria-label*='Send']")).click();
		//Verify whether Email received or not in Inbox
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.linkText("Inbox (1)")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("div[class='y6'] span[id] b")).click();
		WebElement subjectArea=driver.findElement(By.cssSelector("h2[class='hP']"));
		Assert.assertEquals(subject, subjectArea.getText(), "Email Subject text should be the Same");
		WebElement bodyArea=driver.findElement(By.cssSelector("div[class='nH aHU'] div[dir='ltr']"));
		Assert.assertEquals(body, bodyArea.getText(), "Email body text should be the Same");
		driver.manage().timeouts().implicitlyWait(3l,TimeUnit.SECONDS);
		//SignOut Gmail
		driver.findElement(By.cssSelector("span[class='gb_2a gbii']")).click();
		driver.findElement(By.id("gb_71")).click();
		driver.manage().timeouts().implicitlyWait(3l,TimeUnit.SECONDS);
		//Verify, SignIn button present or not after SignOut Gmail
		Assert.assertTrue(driver.findElements(By.id("signIn")).size()>0, "SignIn button should be Exist");

		
		
	
}
@AfterClass
public void tearDown()
{
	driver.close();
}
}
