# TDL-Manny
Coverage: 94%

##About the Project

This is a fullstack webbased application with which a user can use to create Projects and Tasks. You will no doubt have heard of similar projects that have been made with pure JS but the benefit of going fullstack with Springboot and using a H2 database is that we can allow for persistence that doesn't rely on clogging up localStorage.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

In order to use and test this application, you will need several services:

-A Github Account to clone/fork this repository.
-Java and SpringBoot for the Backend 
-Maven
-VS Code or any other IDE suitable for writing frontend code.

### Installing

Step 1: Fork this repository.
Step 2: Connect your local session to your forked repository.
Step 3: Git bash or command line into the directory on your pc.
Step 4: Enter "mvn clean install"
Step 5: The jar file should now be in your target folder, cd into there.
Step 6: Lastly, Enter "java -jar [name of file].war 

## Testing Used

-JUnit and Mockito. Spring's testing varies from the traditional testing especially Mockito testing in several ways, keep an eye out for 'Autowired' and other various annotations for which spring is famous for.
-Integration Testing. Integration testing means checking if different modules are working fine when combined together as a group. It's fine to have working components, but putting them together and testing they work is also important.

## Deployment

Deployment was focused with the idea of CI, Continous Integration, this meant commiting to (pardon the pun) multiple commits a day on Github as well as regular testing with detailed commit messages. 

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring-Tools] (https://spring.io/tools) - IDE Used
* [JUnit] (https://junit.org) - Testing
* [Mockito] (https://site.mockito.org/) - Alternate Testing
* [Jira] (https://emmanuelqa.atlassian.net/secure/RapidBoard.jspa?projectKey=TDL&rapidView=6&view=planning.nodetail) - Link to my Jira
* [Sonarqube] (https://www.sonarqube.org/) - Static Analysis
* [Postman] (https://www.postman.com/) - API testing and development

## Versioning

I used [Github](https://github.com/) for versioning.

## Author

Emmanuel Lomax

## Acknowledgements
-Savannah Vaithilingam
-Alan Davis
