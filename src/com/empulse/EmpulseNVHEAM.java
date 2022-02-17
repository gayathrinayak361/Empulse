 package com.empulse;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EmpulseNVHEAM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		try {
			String url = "https://www.empulseudp.com/NVHEAM/";
			Date datestart = new Date();
			driver.get(url);
			String beforelogin = driver.getPageSource();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			FileWriter fw = new FileWriter("test2(NVHEAM)_log.txt", true);
			System.out.println("Before Login 404 error :" + beforelogin);
			Date datestop_before = new Date();
			if (driver.getTitle().contains("404") || beforelogin.contains("Access Denied")) {
				System.out.println("\nWe are in first if block");
				if (driver.getTitle().contains("404")) {
					fw.write(dateFormat.format(datestart) + "\t" + "Failure - (404 error received before login)\t" + url
							+ "\n");
					Date datestop = new Date();
					long diff = datestop.getTime() - datestart.getTime();
					long diffSeconds = diff / 1000;         
					long diffMinutes = diff / (60 * 1000);
					if (diffSeconds > 60){
						diffSeconds = diffSeconds - 60;
					};
					fw.write("Total time to fail login page: "+ diffMinutes+ " minutes "+diffSeconds+" seconds\n");
					fw.close();
				} else if (beforelogin.contains("Access Denied")) {
					fw.write(dateFormat.format(datestart) + "\t" + "Failure - Access Denied" + url + "\n");
					Date datestop = new Date();
					long diff = datestop.getTime() - datestart.getTime();
					long diffSeconds = diff / 1000;         
					long diffMinutes = diff / (60 * 1000);
					if (diffSeconds > 60){
						diffSeconds = diffSeconds - 60;
					};
					fw.write("Total time to fail login page: "+ diffMinutes+ " minutes "+diffSeconds+" seconds\n");

					fw.close();
				}
			} else {
				System.out.println("Entered else block");
				driver.findElement(By.xpath("//input[@formcontrolname='username']"))
						.sendKeys("sherlin.thomas@empulseglobal.com");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("123456");
				Thread.sleep(2000);
				// driver.findElements(By.xpath("//span[@aria-hidden='true']"))
				driver.findElement(By.xpath("//button[text()='Login']")).click();
				Thread.sleep(2000);
				// driver.close();
				String afterloging = driver.getPageSource();
				System.out.println(afterloging);
				if (driver.getTitle().contains("404") || beforelogin.contains("Access Denied")) {
					System.out.println("\nWe are in first if block");
					if (driver.getTitle().contains("404")) {
						fw.write(dateFormat.format(datestart) + "\t" + "Failure - (404 error received after login)\t" + url
								+ "\n");
						Date datestop_after = new Date();
						long diff_before = datestop_before.getTime() - datestart.getTime();
						long diffSeconds_before = diff_before / 1000;         
						long diffMinutes_before = diff_before / (60 * 1000);
						long diff_after = datestop_after.getTime() - datestop_before.getTime();
						long diffSeconds_after = diff_after / 1000;         
						long diffMinutes_after = diff_after / (60 * 1000);
						if (diffSeconds_after > 60){
							diffSeconds_after = diffSeconds_after - 60;
						} else if (diffSeconds_before > 60) {
							diffSeconds_before = diffSeconds_before - 60;
						};
						fw.write("Total time to load login page: "+ diffMinutes_before+ " minutes "+diffSeconds_before+" seconds\n");
						fw.write("Total time to fail after login: "+ diffMinutes_after+ " minutes "+diffSeconds_after+" seconds\n");
						
						fw.close();
					} else if (beforelogin.contains("Access Denied")) {
						fw.write(dateFormat.format(datestart) + "\t" + "Failure - (Access Denied after login)\t" + url + "\n");
						Date datestop_after = new Date();
						long diff_before = datestop_before.getTime() - datestart.getTime();
						long diffSeconds_before = diff_before / 1000;         
						long diffMinutes_before = diff_before / (60 * 1000);
						long diff_after = datestop_after.getTime() - datestop_before.getTime();
						long diffSeconds_after = diff_after / 1000;         
						long diffMinutes_after = diff_after / (60 * 1000);
						if (diffSeconds_after > 60){
							diffSeconds_after = diffSeconds_after - 60;
						} else if (diffSeconds_before > 60) {
							diffSeconds_before = diffSeconds_before - 60;
						};
						fw.write("Total time to load login page: "+ diffMinutes_before+ " minutes "+diffSeconds_before+" seconds\n");
						fw.write("Total time to fail after login: "+ diffMinutes_after+ " minutes "+diffSeconds_after+" seconds\n");
						
						fw.close();
					}
				} else {
					System.out.println("Success block");
					fw.write(
							dateFormat.format(datestart) + "\t" + "Success - (no error encountered)\t" + url
							+ "\n");
					Date datestop_after = new Date();
					long diff_before = datestop_before.getTime() - datestart.getTime();
					long diffSeconds_before = diff_before / 1000;         
					long diffMinutes_before = diff_before / (60 * 1000);
					long diff_after = datestop_after.getTime() - datestop_before.getTime();
					long diffSeconds_after = diff_after / 1000;
					if (diffSeconds_after > 60){
						diffSeconds_after = diffSeconds_after - 60;
					} else if (diffSeconds_before > 60) {
						diffSeconds_before = diffSeconds_before - 60;
					};
					long diffMinutes_after = diff_after / (60 * 1000);
					System.out.println(datestart + "\t"+ datestop_after + "\t" + diff_after + "\t"+diffSeconds_after);
					fw.write("Total time to load login page: "+ diffMinutes_before+ " minutes "+diffSeconds_before+" seconds\n");
					fw.write("Total time to load after login page: "+ diffMinutes_after+ " minutes "+diffSeconds_after+" seconds\n");
					fw.close();
				}
				Thread.sleep(3000);
				 driver.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// assertTrue(driver.getTitle().contains("404"));
		// driver.close();
	}

}
