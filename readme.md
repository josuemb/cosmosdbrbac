# cosmosdbrbac

A Java demo showing how to connect to **Azure Cosmos DB** using
**Role-Based Access Control (RBAC)** with Azure AD (Entra ID) credentials via the
[Azure Cosmos Java SDK v4](https://docs.microsoft.com/en-us/azure/cosmos-db/sql/sql-api-sdk-java-v4)
and [Azure Identity](https://learn.microsoft.com/azure/developer/java/sdk/authentication/overview) —
**no account keys or connection strings**.

To learn more, see
[Configure RBAC with Azure AD for Azure Cosmos DB](https://docs.microsoft.com/en-us/azure/cosmos-db/how-to-setup-rbac).

## Requirements

- **Java 25** — [Microsoft OpenJDK](https://www.microsoft.com/openjdk) recommended ([install](https://docs.microsoft.com/en-us/java/openjdk/install)).
- [Apache Maven](https://maven.apache.org/) ([install](https://maven.apache.org/install.html)).
- An IDE or editor ([Visual Studio Code](https://code.visualstudio.com/) recommended).
- An [Azure Cosmos DB account](https://docs.microsoft.com/en-us/azure/cosmos-db/sql/create-sql-api-dotnet#create-account).
- An [Azure AD Enterprise Application](https://docs.microsoft.com/en-us/azure/active-directory/manage-apps/add-application-portal) with an [Application Secret](https://docs.microsoft.com/en-us/azure/active-directory/develop/howto-create-service-principal-portal#option-2-create-a-new-application-secret).

You will need to note these values to configure the app:

| From | Value | Used for |
|---|---|---|
| Azure AD Enterprise App | **Tenant Id** | Authentication |
| Azure AD Enterprise App | **Application (Client) Id** | Authentication |
| Azure AD Enterprise App | **Application Secret** | Authentication |
| Azure AD Enterprise App | **Object Id** | Assigning the RBAC role |
| Cosmos DB Account | **Cosmos DB URI** | Connection |

> **Assign an RBAC role** to the application's Object Id on the Cosmos DB account
> (e.g. *Cosmos DB Built-in Data Contributor*) so it can read/write data. See the
> RBAC guide linked above.

## Quick start

```bash
# 1. Fill src/main/resources/application.properties with your values (see Configuration)
mvn package
java -jar ./target/cosmosdbrbac-1.0-SNAPSHOT.jar
```

## Configuration

Edit `src/main/resources/application.properties` (or pass a path to your own file
at runtime — see below). **Use your own values — these are placeholders.**

| Property | Example |
|---|---|
| `azure.cosmos.uri` | `https://<your-account>.documents.azure.com:443/` |
| `azure.cosmos.tenantId` | `<your-tenant-id>` |
| `azure.cosmos.clientId` | `<your-application-client-id>` |
| `azure.cosmos.clientSecret` | `<your-application-secret>` |

## Build

```bash
mvn package
```

## Run

**From an IDE:** fill in `application.properties` and run the app.

**From the command line:**

```bash
# Linux/macOS
java -jar ./target/cosmosdbrbac-1.0-SNAPSHOT.jar
# Windows
java -jar .\target\cosmosdbrbac-1.0-SNAPSHOT.jar
```

To read from a different properties file, pass its full path as an argument:

```bash
java -jar ./target/cosmosdbrbac-1.0-SNAPSHOT.jar /home/user/myfile.properties
```

## License

MIT
