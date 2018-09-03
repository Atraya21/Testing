Atraya Mukherjee 1001144456
Raveena Jadhav 1000833967

This readme file contains instructions for creating , executing and 
generating a coverage report for the provided code in Eclipse IDE. 

1) Create an Eclipse workspace  and project; while making a project choose the option
	"use project files as root for sources and class files".

2) Inside your project save file.txt this will 
   serve as the input file for running Printtokens2.java and Printtokens2Test.java
   test cases.  
   
   If compiler gives error on file input despite file.txt being present in the project workspace.
   In your class and right click -> new -> file and save it accordingly
   
3) First make a package by right clicking on your project -> new -> package

4) Make a class by Package(right click)-> new -> class and make Printtokens2.java 

5) Make a test class by Package(right click)-> new ->Junit Test Case and make Printtokens2Test.java

6) Both Printtokens2Test.java and Printtokens2.java have been provided with the submission. Please
	note that to ensure maximum coverage, the bug fixed version of Printtokens2.java has been used

7) During first run/build please select the option to use Junit in class path when prompted to do so
	
8) To execute Printtokens2.java(right click) -> RunAs -> Java application.

9) To execute Printtokens2Test.java(right click) -> RunAs -> Junit test

10) To generate coverage right click on the class -> CoverageAs -> Coverage Configurations -> 
   click coverage 
   
11) In steps 8 & 10 give "file.txt" as input file. 

12) To generate the coverage report to JaCoCo right click on the coverage report and
    export as html. Open the html to see the JacoCo session. 
    