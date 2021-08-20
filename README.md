# EmployeeProject

This project reads employee records from a CSV file and removes records with duplicate ids and removes records with greater or fewer attributes than 10. The project
uses multithreading to write the records into the database concurrently, using JDBC. The fastest time I could achieve was 7.35 seconds using 90 threads.

## How to Use

Within java/resources, a .properties file should be created. Within this file you should include the following keys (case-sensitive) :
 - userName
 - password
 - url
 
 Where the values are the username, password and connection URL for your database respectively.
 
 A maven dependency will need to be added for the database of your choice. I used MySQL Workbench during development.
 
 ## Using the Classes
 
 To access the reader - use the CsvManager class. This class will read the CSV file provided and filter based on the aforementioned requirements; returning a List of 
 EmployeeDTO objects.
 
 To access the database - use the DatabaseManager class. Supported operations are:
  - Adding a list of records
  - Retrieving all the records from the database
  - Retrieving the number of records in the database 
  - Truncating the table
  
  ThreadPool creates the number of threads specified as passed into the constructor. Creating anymore than 149 threads on my machine returns a "Too many connections" message 
  from the server.
