# Trello_quora
Course 5 - Project - Social Q&A web-app.

Teammates

Siddharth Tiwari : siddhartth.1987@gmail.com, 
Sanjit Singh : sanjitsingh1991@gmail.com, 
Swanand Mehta : swanand01.mehta@gmail.com, 
Puja Singh : pujasingh59@gmail.com, 

After cloning the repo, please do a clean install  (mvn clean install -DskipTests)

Points to note: 

1) Duplicate entries for the dependency 'jackson-annotations' have been removed from POM.xml
2) SQL scripts that populate tables require modification, otherwise test cases for QuestionController and AnswerController fail.
   
   Dates in insert script "quora_test.sql" have been set as '2018-09-17 19:41:19.593'. This results in entries with back-dated token expiration dates (expires_at). 
   The value of expires_at should ideally be, Current Date + 8 to 24 hours. 
