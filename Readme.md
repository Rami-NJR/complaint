<h2>Complaint application</h2>

<h2>Environment Setup</h2>

<h4>You will need to install the following to run the application locally:</h4>
Java 8+
Maven 3+
Docker tools

<h4>Running the applications</h4>
We use Docker containers to run the mysql database. 
They are configured within the docker-compose.yml in the root project directory.
View the database versioning page on Confluence for an understanding of how Liquibase is configured.

<h4>To start the database instance from the command line:</h4>

> docker-compose up

The MySql database is available at localhost:9011/complaintDB.

<h4>To stop the container:</h4>

> docker-compose down

<h4>After Running the app application, You can access the api Swagger docs from</h4>
>http://localhost:8080/swagger-ui.html