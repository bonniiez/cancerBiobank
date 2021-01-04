# Cancer Biobank

A desktop application that models a cancer biobank. At a high level, the cancer biobank functions to assist in the improvement of cancer prediction and prevention, and facilitate the development of new drugs and personalized therapy approaches.

Java Swing is used for front end GUI and Java + MySQL for backend.

<b>Working features:</b>
* Displays table of existing cancer patients recorded regarding each patients cancer diagnosis
* Insert new patients into db
* Delete existing patient record
* Display a list of treatments/therapy (drugs, dosages, costs, progression types etc)


<b>Instructions: </b>
1. Configure MySQL Environment on your machine.
2. Create a database named “cancerBiobank” 
3. “CancerBiobank.sql” in the project will automatically import tables/data into database. 
4. Download the MySQL Java connector from https://www.mysql.com/products/connector/. (It is already included in the project, but included in case of any problems)
    * Add the library manually to the project in the dependencies 
5. Run the Main from CancerBiobank.java 


<b>Plans for future development:</b>
*  Blood/ tissue samples for each patient
* Display list of mutagens patients were exposed to
* Edit existing patient information
