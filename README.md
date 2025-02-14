# Shift-Tech-task

This program reads data about managers and employees from file, processes it and prints to comsole/file.  

Input data is stored in the following format:  
Manager,1,Jane Smith,5000,HR  
Employee,101,John Doe,3000,1  
Employee,102,Emily Johnson,abc,1  
Manager,2,Michael Brown,6000,Sales  
Employee,103,Chris White,-2900,2  
Employee,104,Anna Taylor,3100,2  
Employee,105,Robert Black,,2  

After reading, data is processed, finding erroneous lines. Depeneding on cli arguments, employees can be sorted by name or salary in ascending/descending order.  

Shift-TZ-1.0-SNAPSHOT-jar-with-dependencies.jar file is located in folder 'target'.  

For execution run command java -jar <directory to jar file> <parameters>  

-i= / --input='inputFilePath'  
-o= / --output=outputType (can be 'console' or 'file')  
if output type is 'file', then --path='outputFilePath' must be set as well  
-s / --sort='sorting criteria' ('name' or 'salary')  
--order='sorting order' ('asc' or 'desc')  

Sorting criteria is not obligatory, but if set, then order must be set too.  

Example: java -jar Shift-TZ-1.0-SNAPSHOT-jar-with-dependencies.jar -i=file -o=output.txt --path=input.txt -s=name --order=desc  

If console parameters are erroneous and program can't output any data (file not found, wrong parameters) then appropriate information is logged in app.log file. Other logs can be found there too.  

Java version: 17  
Build tool: Maven, 3.9.6  
Dependencies: junit 5.11.4, apache commons cli 1.9.0, lombok 1.18.30, log4j 2.20.0  
