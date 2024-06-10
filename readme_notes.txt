 
Server
	JDK 17
	Run the batch file in a cmd window to see the log
	It works on port 8080
		Changing the server port: -Dserver.port=8083
		
Java
	IntelliJ + JDK 17
	Maven
	JUnit + AssertJ
	Run tests inside IDE
	Contains the solution examples
	
JS
	Jest, Selenium, Playwright
	NPM ci (clean install)
	NPM run test (src folder)
	OR
		NPX jest FILE
	Playwright	
		npx playwright install
		npx playwright test

CSharp
	VS 2022, .net 6.0 Console app
	.Net 
	Using NUnit
	May need to install Selenium.WebDriver.ChromeDriver

Python
	Pycharm, using PyTest
	3.11
	May require importing package
		pip install chromedriver-py
	
	If does not identify packages:
		pip install -r requirements.txt

HTML App
	Needs to press the IDs
	Press Update before results is updated
	Then check the display

All
	Change the path to the HTML to the absolute path for the UI tests	
		the driver cannot work with relative paths (out of the box).
	Tests may (or may not) pass
	May need to install the right ChromeDriver based on your version of Chrome
	Broken UI tests may leave open windows.