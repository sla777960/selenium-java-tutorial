```markdown
# selenium-java-tutorial

A small Selenium + JUnit example project using Maven and Java 24. It contains a single test (`com.example.test.GettingStarted`) that demonstrates running Chrome in headless and non-headless modes.

## Prerequisites

- Java 24 (JDK)
- Maven
- Google Chrome (recommended latest stable)

Selenium Manager (bundled with Selenium 4.15+) will automatically download and manage the correct browser driver, so you normally don't need to install chromedriver manually.

## Project layout

- `pom.xml` — Maven configuration and dependencies (Selenium, JUnit)
- `src/test/java/com/example/test/GettingStarted.java` — example test
- `.gitignore` — ignores build artifacts, IDE files, and drivers

## Run the example test

Open PowerShell in the project root (where `pom.xml` lives) and run one of these commands.

Run with headless Chrome (default):

```powershell
mvn clean test -Dtest=com.example.test.GettingStarted -Dheadless=true
```

Run with a visible browser window:

```powershell
mvn clean test -Dtest=com.example.test.GettingStarted -Dheadless=false
```

Notes:
- Use `-Dtest=...` to run a single test class. Remove it to run the full test suite.
- The `GettingStarted` test uses simple `Thread.sleep` pauses for brevity. Replace with explicit waits (WebDriverWait) for production tests.

## Common troubleshooting

- If Chrome won't start in headless mode on CI, ensure the following options are present in `ChromeOptions` (they are already set in the example): `--no-sandbox`, `--disable-dev-shm-usage`, and `--headless=new`.
- If Selenium can't find a driver, make sure your machine has network access so Selenium Manager can download the driver, or install a matching `chromedriver` and put it on PATH.

## Git & GitHub

To add, commit and push changes to GitHub from the project root:

```powershell
git add .
git commit -m "Describe your changes"
git push origin main
```

If you need to remove a file's historical presence from the Git history (advanced), prefer `git-filter-repo` over `git filter-branch`. This rewrites history and requires a force-push; coordinate with collaborators before doing it.

## License & notes

This repository contains example code for demonstration and learning. Check `lib/LICENSE` for bundled library licenses.

Enjoy testing!
```## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## To run code in non-headless mode

cd "YourProjectDirectory"; 
mvn "-Dtest=com.example.test.GettingStarted" "-Dheadless=false" test

## To run code in headless mode

cd "YourProjectDirectory";
mvn "-Dtest=com.example.test.GettingStarted" "-Dheadless=true" test
