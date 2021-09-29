## Introduction

This is a basic Cosmos DB RBAC Java Demo showing how to connect to Cosmos DB using [Java SDK v4](https://docs.microsoft.com/en-us/azure/cosmos-db/sql/sql-api-sdk-java-v4).

To know more about Cosmos DB RBAC, see: [Configure role-based access control with Azure Active Directory for your Azure Cosmos DB account](https://docs.microsoft.com/en-us/azure/cosmos-db/how-to-setup-rbac).

## Requisites

To be able to run this example you will need:
- Java 11. [Microsoft OpenJdk](https://www.microsoft.com/openjdk) is recommended. See: [how to install it](https://docs.microsoft.com/en-us/java/openjdk/install).
- Any IDE or Code Editor. [Visual Studio Code](https://code.visualstudio.com/) is recommended. See [how to install it](https://code.visualstudio.com/docs/setup/setup-overview).
- This project is [Apache Maven](https://maven.apache.org/) based, so even when it is not absolutely necessary since several IDEs/editor has built-in support is recommended to install it. See [how to install it](https://maven.apache.org/install.html).
- Having a Cosmos DB Account. See [how to create it](https://docs.microsoft.com/en-us/azure/cosmos-db/sql/create-sql-api-dotnet#create-account).
- Having an [Azure AD Enterprise Application](https://docs.microsoft.com/en-us/azure/active-directory/manage-apps/) and an Application Secret.
    - See [how to create an Azure Add Enterprise Application](https://docs.microsoft.com/en-us/azure/active-directory/manage-apps/add-application-portal).
    - See [how to create an Application Secret](https://docs.microsoft.com/en-us/azure/active-directory/develop/howto-create-service-principal-portal#option-2-create-a-new-application-secret).

**Important:**
- You will need to take note of some parameters of the *Azure AD Enterprise application*, specifically:
    - *Tenant Id* to authenticate from Java Code.
    - *Object Id* to assign roles to the application.
    - *Application Id* to authenticate from Java Code.
    - *Application Secret* to authenticate from Java Code.
- You will need to take note of some parameters of the *Cosmos DB Account*, specifically:
    - *Cosmos DB URI* to authenticate from Java Code.

## How to run it?
- Within IDE:
    1. Open *application.properties* file.
    1. Replace all parameters.
    1. Run the application inside the IDE or Code Editor.
- From command line:
    1. Open command line prompt.
    1. Go to the folder you have the code.
    1. Execute command mvn package.
    1. Execute command:
        1. For Linux/Mac: java -jar ./target/cosmosdbrbac-1.0-SNAPSHOT.jar
        1. For windows: java -jar .\target\cosmosdbrbac-1.0-SNAPSHOT.jar
    1. If you want to execute reading from a different properties file, just add the full path to the properties files after calling the jar file. Like this:    
    java -jar ./target/cosmosdbrbac-1.0-SNAPSHOT.jar /home/user/myfile.properties