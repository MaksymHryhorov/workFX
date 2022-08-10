# JavaFX app

The program was developed taking into account all the requirements and needs of the user. In particular, the program meets the functional requirements for:

• creating a database file and writing to it in a certain format;
• reading all data from the file and displaying them;
• adding a new data element to the database file;
• update of any element in the database file;
• deletion of any data element from the file;
• checking the admissibility of basic data entered by the user;
• issuance of warning and information messages to the user;
• sorting with any database field;
• search for any user in the database.

UML Diagram

![image](https://user-images.githubusercontent.com/84277122/183878374-8f17bda5-0ddb-4a41-a9ad-8fc10812de21.png)
![image](https://user-images.githubusercontent.com/84277122/183878446-4c1babe4-f84c-482e-b944-449f50ffba0f.png)

The module consists of 6 classes, namely the Main, TableController, UserAccount, TableSearch, OpenNewScene, Alerts classes. Below is a description of the purpose of all the classes in my program.

The Main class is the main Main class to indicate the entry point to the program.
The UserAccount class is a class for creating any user in the system.
The TableSearch class is a class for searching for a specific user in the system and displaying it in a specific table.
The OpenNewScene class is a class for opening a new scene.
The Alerts class is a class for describing errors
The TableController class is a class for adding, editing, and deleting a user in the system. Displaying the user table and storing data in a file.

Launch the program in one of the standard ways - by double-clicking on the program icon. When the program is enabled, an authorization form appears.

![image](https://user-images.githubusercontent.com/84277122/183878616-ce7d9395-12d0-4bfb-a7c6-a757e7f58900.png)

After successful authorization, a new window opens in which you can work with the file database

![image](https://user-images.githubusercontent.com/84277122/183878769-b3bf25b8-27d3-4a38-8cf4-ef916658add6.png)

The main window of the application contains buttons: add, remove, save, edit, search in the database and exit, which provide access to perform all functions of the program.

If we want to find a user in the database, click on the "Search in the database" button and enter what we want to find in the search

![image](https://user-images.githubusercontent.com/84277122/183879914-866c111e-3961-4d17-89ca-dbbc1484ff98.png)

Users can be sorted by:
1. Username
2. Mail address
3. Surname
4. TIN
