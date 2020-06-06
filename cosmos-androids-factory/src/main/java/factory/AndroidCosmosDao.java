package factory;

import com.microsoft.azure.documentdb.*;
import org.springframework.beans.factory.annotation.Autowired;
import state.runner.Android;

import java.util.List;

/**
 * CosmosDB Dao.
 */
public class AndroidCosmosDao {

  private final DocumentClient documentClient;
  private final FeedOptions feedOptions;

  private static final String DB_NAME = "androids-factory";
  private static final String COLLECTION_NAME = "androids";
  private static final String COLLECTION_LINK = "dbs/" + DB_NAME + "/colls/" + COLLECTION_NAME;
  private static final String FIND_ALL = "SELECT * FROM " + COLLECTION_NAME;

  @Autowired
  public AndroidCosmosDao(CosmosDbProperties cosmosDbProperties) {
    ConnectionPolicy connectionPolicy = new ConnectionPolicy();
    connectionPolicy.setEnableEndpointDiscovery(true);
    this.documentClient = new DocumentClient(cosmosDbProperties.getServiceEndpoint(), cosmosDbProperties.getMasterKey(),
        connectionPolicy, ConsistencyLevel.Session);
    this.feedOptions = new FeedOptions();
    feedOptions.setEnableCrossPartitionQuery(true);
  }

  void createNewAndroid() throws DocumentClientException {
    Android android = new Android();
    Document document = new Document(android.toJson());
    documentClient.createDocument(COLLECTION_LINK, document, null, false);
  }

  List<Document> findAllAndroids() {
    return documentClient
        .queryDocuments(COLLECTION_LINK, FIND_ALL, feedOptions).getQueryIterable().toList();
  }
}
