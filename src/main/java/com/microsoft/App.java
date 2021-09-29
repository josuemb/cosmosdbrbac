package com.microsoft;

import java.io.FileInputStream;
import java.util.Properties;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.identity.ClientSecretCredentialBuilder;

/**
 * Cosmos DB RBAC Basic Java Demo
 *
 */
public class App {
    static final String DEFAULT_FILE_NAME = "application.properties";
    static final String DEFAULT_AUTHORITY_HOST = "https://login.microsoftonline.com";
    static final String KEY_ENDPOINT = "azure.cosmos.uri";
    static final String KEY_AUTHORITY_HOST = "azure.cosmos.authorityHost";
    static final String KEY_TENANT_ID = "azure.cosmos.tenantId";
    static final String KEY_CLIENT_ID = "azure.cosmos.clientId";
    static final String KEY_CLIENT_SECRET = "azure.cosmos.clientSecret";

    public static void main(String[] args) {
        String fileName;
        if (args.length > 0) { // If properties file is given in the argument use it
            fileName = args[0];
        } else { //If not properties file is given use the default application.properties
            fileName = DEFAULT_FILE_NAME;
        }
        // Read all properties from application.properties
        var cosmosProperties = readProperties(fileName);
        if (cosmosProperties == null) {
            return;
        }
        // Create Cosmos DB RBAC Credentials
        // See: https://docs.microsoft.com/en-us/azure/cosmos-db/how-to-setup-rbac
        var credential = new ClientSecretCredentialBuilder()
            .authorityHost(cosmosProperties.getAuthorityHost())
            .tenantId(cosmosProperties.getTenantId())
            .clientId(cosmosProperties.getClientId())
            .clientSecret(cosmosProperties.getClientSecret())
        .build();
        System.out.println("Connecting to Cosmos DB...");
        // Try to connect to Cosmos DB with RBAC credentials and auto close connection
        try (var client = new CosmosClientBuilder().credential(credential).endpoint(cosmosProperties.getEndpoint()).buildClient()) {
            System.out.println("Querying Cosmos Databases...");
            // Read databases properties
            var databases = client.readAllDatabases();
            for (var database : databases) {
                System.out.println(String.format("Database found: %s", database.getId()));
            }
        } catch (Exception e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
    }

    /**
     * Read all needed properties from given file
     * @param fileName Path to the properties file
     * @return Object with read properties
     */
    private static RbacCosmosProperties readProperties(String fileName) {
        RbacCosmosProperties cosmosRbacProperties;
        try (var inStream = new FileInputStream(fileName)) {
            Properties props = new Properties();
            props.load(inStream);
            cosmosRbacProperties = new RbacCosmosProperties();
            cosmosRbacProperties.setEndpoint(getProperty(props, KEY_ENDPOINT));
            cosmosRbacProperties.setAuthorityHost(getProperty(props, KEY_AUTHORITY_HOST, DEFAULT_AUTHORITY_HOST));
            cosmosRbacProperties.setTenantId(getProperty(props, KEY_TENANT_ID));
            cosmosRbacProperties.setClientId(getProperty(props, KEY_CLIENT_ID));
            cosmosRbacProperties.setClientSecret(getProperty(props, KEY_CLIENT_SECRET));
        } catch (Exception e) {
            cosmosRbacProperties = null;
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
        return cosmosRbacProperties;
    }

    /**
     * Read a property from the given properties and trows IllegalAccessException when not found
     * @param properties Properties to read
     * @param key Property key to get
     * @return Property value
     * @throws IllegalAccessException
     */
    private static String getProperty(Properties properties, String key) throws IllegalAccessException {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException(String.format("%s parameter not found.", key));
        }
        return value;
    }

    /**
     * Read a property from the given properties and provide default value to be returned if not found
     * @param properties Properties to read
     * @param key Property key to get
     * @param defaultValue Default value to be returned if property is not found
     * @return Property value
     */
    private static String getProperty(Properties properties, String key, String defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }
}
