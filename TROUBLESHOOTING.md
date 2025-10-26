# Selenium Test Troubleshooting Guide

## Common Issues and Solutions

### 1. Chrome/ChromeDriver Issues

#### Chrome Won't Start in Headless Mode
```
org.openqa.selenium.WebDriverException: unknown error: Chrome failed to start: crashed [...] (chrome not reachable)
```
**Solutions:**
- Add these Chrome options (already included in example):
  ```java
  options.addArguments("--no-sandbox");
  options.addArguments("--disable-dev-shm-usage");
  options.addArguments("--headless=new");  // Modern Chrome headless mode
  ```
- Ensure you have enough system resources (memory/CPU)
- Try updating Chrome to the latest version
- Clear Chrome's user data directory

#### Driver Version Mismatch
```
SessionNotCreatedException: Message: session not created: This version of ChromeDriver only supports Chrome version XX
```
**Solutions:**
- Let Selenium Manager handle driver downloads (default in Selenium 4.15+)
- If using manual driver:
  1. Check Chrome version: `chrome://version`
  2. Download matching ChromeDriver from https://chromedriver.chromium.org/
  3. Add driver to system PATH or specify path in code:
     ```java
     System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
     ```

### 2. Network/Proxy Issues

#### Cannot Download Driver
```
IOException: Connection refused [...] while downloading driver
```
**Solutions:**
- Check internet connectivity
- Configure proxy if needed:
  ```java
  System.setProperty("https.proxyHost", "proxy.company.com");
  System.setProperty("https.proxyPort", "8080");
  ```
- Manual driver download as fallback

#### Page Load Timeouts
```
TimeoutException: Timeout loading page
```
**Solutions:**
- Increase timeouts:
  ```java
  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  ```
- Check network connectivity
- Verify URL is accessible
- Consider using explicit waits

### 3. Element Interaction Issues

#### Element Not Found
```
NoSuchElementException: no such element: Unable to locate element
```
**Solutions:**
- Use explicit waits instead of Thread.sleep:
  ```java
  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  WebElement element = wait.until(
      ExpectedConditions.presenceOfElementLocated(By.name("q"))
  );
  ```
- Check if element is in an iframe
- Verify element selectors (ID, name, CSS, XPath)
- Check if page is fully loaded

#### Element Not Clickable
```
ElementClickInterceptedException: element click intercepted
```
**Solutions:**
- Wait for element to be clickable:
  ```java
  wait.until(ExpectedConditions.elementToBeClickable(element));
  ```
- Scroll element into view:
  ```java
  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
  ```
- Check for overlaying elements
- Try JavaScript click as fallback:
  ```java
  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  ```

### 4. Test Environment Issues

#### Tests Work Locally But Fail in CI
**Solutions:**
- Ensure CI environment has:
  - Chrome installed
  - Sufficient memory/CPU
  - Network access
- Add CI-specific Chrome options:
  ```java
  if (System.getenv("CI") != null) {
      options.addArguments("--headless=new");
      options.addArguments("--no-sandbox");
      options.addArguments("--disable-dev-shm-usage");
      options.addArguments("--disable-gpu");
  }
  ```
- Configure explicit screen resolution:
  ```java
  options.addArguments("--window-size=1920,1080");
  ```

#### Memory Issues
```
OutOfMemoryError or Chrome crashes
```
**Solutions:**
- Close driver properly in finally block
- Limit parallel test execution
- Increase JVM memory:
  ```powershell
  mvn test -DargLine="-Xmx2g"
  ```
- Clear browser data between tests:
  ```java
  driver.manage().deleteAllCookies();
  ```

### 5. Maven/Project Issues

#### Test Compilation Fails
```
[ERROR] COMPILATION ERROR
```
**Solutions:**
- Verify Java version matches pom.xml:
  ```xml
  <properties>
      <maven.compiler.release>24</maven.compiler.release>
  </properties>
  ```
- Clean Maven cache:
  ```powershell
  mvn clean
  rm -r ~/.m2/repository/org/seleniumhq/selenium/
  mvn dependency:purge-local-repository
  ```
- Update dependencies to latest versions

#### Tests Not Running
**Solutions:**
- Check test naming conventions
- Verify test classes have @Test annotations
- Run specific test:
  ```powershell
  mvn test -Dtest=com.example.test.GettingStarted
  ```
- Enable test debugging:
  ```powershell
  mvn test -Dmaven.surefire.debug
  ```

## Getting More Help

1. Check Selenium logs (enable with `-Dwebdriver.chrome.verboseLogging=true`)
2. Review Chrome logs (enable with `options.setLogLevel(ChromeDriverLogLevel.ALL)`)
3. Check test reports in `target/surefire-reports/`
4. Review [Selenium documentation](https://www.selenium.dev/documentation/)
5. Search [Selenium issues on GitHub](https://github.com/SeleniumHQ/selenium/issues)

## Contributing Solutions

If you find new issues and solutions, please:
1. Document the problem and solution clearly
2. Add example code if relevant
3. Submit a PR to update this guide