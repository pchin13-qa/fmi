package Selenium.maven.Selenium.maven.demo;

import org.testng.TestNG;
import Selenium.maven.Selenium.maven.demo.loginTest;


public class Executor {

    public static void main(String[] args) {
		TestNG testSuite = new TestNG();
		testSuite.setTestClasses(new Class[] { loginTest.class });
		testSuite.run();
    }

}
