# SOTIslandFinder
Got lazy playing sea of thieves so I built an Island finder application to locate islands based on pixel comparison in their image. 

Getting Started: 
- The application is very simple, containing only a few classes.
- The classes are as follows: 
- First the starting point of the application is in the main class, here the user is prompted with the requirements of the system 
and the file path of the image they want to compare is taken as input. 
- From the main class a call will then be made to the CheckImage class where the format and the dimensions of the image will be checked to make sure they are within the specifications. 
- If the image is found to be within specification then the main class will call the Compare class and the method imageCompare with an argument of the file path of the image the user wants to compare. 
- in the compare class the Database class is called with the method getIslandFile and an Id as the argument. 
- As the name would suggest the database is in charge of connecting with the mySQL database to retrieve stock island photos. 
- Once the matching island has been found within the compare class the id of the located island is sent to the main class. 
- Once the ID has been obtained in the main class it then calls the getIslandLocation method of the Database class to get the name and location of the island to present to the user. 

To Use: 
- In order to use the application all that is needed is IntelliJ paired with java JDK 15.0.2 and a copy of the mySQL single table database. 
- The system can also be placed into a jar file and run in the command line paired with the mySQL database. 

PLEASE NOTE ALL CODE IS LOCATED IN THE MASTER BRANCH
