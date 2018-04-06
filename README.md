# codingtest
my coding test



Steps for setting up the project:

1.Install the java 1.8 and eclipse luna. 
2.Install MongoDB and make mongodb as windows service.
3.Download the project from github and extract it.
4.Import the project in eclipse as a maven project.
5.Setup the wildfly or jboss server in the eclipse and deploy the project in the server.
6.Import CSV file into the mongodb server by going into the bin folder in mongodb and the command is "mongoimport --db sample --collection user --type csv --file filepath/file_name.csv.csv --headerline".
7.Start the server and check it in browser using the URL "localhost:8080/uninsuredrest/users"
8.Finally the data is retrieved from mongodb.
