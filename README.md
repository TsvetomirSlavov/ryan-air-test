
# Automated Testing Framework Sample
A Skeleton of a Selenium testing Frameworking with one passing test for the Ryan-Air One Way flight booking test case
##  Project Structure
 
To do tree of structure


## Prerequisites
### On OSX
For the following project the following is necessary:
-IDE e.g (eclipse, intelliJ, etc)
-Java
-Maven

Simplest way of doing this is to install Homebrew which can be done by following this
link: http://brew.sh/

Then once homebrew is installed open up your cmd or bash. 

```
brew install java

```


```
brew install maven

```

open new tab and


```
 mvn -version 

```
This should return something like so:


```
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T16:41:47+00:00)
Maven home: <YOUR-PATH>
Java version: 1.8.0_112, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk1.8.0_112\jre
Default locale: en_GB, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "dos"

```

### Windows

Links to help you through this process:

Java
https://www.ntu.edu.sg/home/ehchua/programming/howto/JDK_Howto.html#jdk-install

Maven
http://maven.apache.org/guides/getting-started/windows-prerequisites.html


##The project contains 


## Reporting
Using the (Allure Reporting framwork)

Example usage (jUnit Example) https://github.com/allure-examples/allure-junit-example

Wiki https://github.com/allure-framework/allure-core/wiki

There is a Jenkins plugin, which automatically compiles report, an icon will appear next to the build number, simply click and view.

To view in Chrome, type this line into terminal 

```
http://localhost:8080/
```


To run on your local and view the results in allure, run: mvn clean test site , then go to 

## How to use the project
### Run Ryan-Air tests and view reports

Build, package and run the tests:

```
mvn clean verify
```

To run test:

```
mvn clean test
```

Recompile the reporting files to location for jetty 
```
mvn site 
```

to run jetty  to view allures reports

```
mvn jetty:run
```

Go to http://localhost:8080/ on your browser and you view the allure reports 
