# state-runner-code
Simple Azure SQL &amp; Cosmos DB app used as demo during "Do Developers Dream of Stateless Apps" presentation.

## Prerequisites
1. You need to have access to Azure account and subscription.
2. Make sure you have az cli installed & you're logged in to your subscription.

## Azure SQL Example
1. Run deployment/deploy-azure-sql.sh to create Azure SQL DB and Server in two regions (with failover group).
2. Put user, password and your database url (get it from Azure portal) in factory-db/pom.xml:
   ```
   <flyway.user></flyway.user>
   <flyway.password></flyway.password>
   <flyway.url></flyway.url>
   ```
3. Ad your ip address to Azure SQL firewall.
4. Run mvn flyway:migrate from factory-db.   
5. Fill the application.properties file in sql-androids-factory
   ```
   spring.datasource.url=
   spring.datasource.username=
   spring.datasource.password=
   ```
6. Run the app, main method can be found in AppAzureSQL.java   

## Cosmos DB Example
1. Run deployment/deploy-cosmos-db.sh to create Azure Cosmos DB with one write region and two read regions.
2. Get connection string and service endpoint for your Cosmos DB (you get it from Azure portal). Set cosomos-androids-factory application.properties:
   ```
   cosmos.master-key=
   cosmos.service-endpoint=
   ```
3. Run the app, main method can be found in AppCosmosDb.java   
