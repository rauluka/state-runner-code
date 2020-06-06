package factory;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cosmos")
public class CosmosDbProperties {
  private String serviceEndpoint;
  private String masterKey;

  public CosmosDbProperties() {

  }

  public String getServiceEndpoint() {
    return serviceEndpoint;
  }

  public void setServiceEndpoint(String serviceEndpoint) {
    this.serviceEndpoint = serviceEndpoint;
  }

  public String getMasterKey() {
    return masterKey;
  }

  public void setMasterKey(String masterKey) {
    this.masterKey = masterKey;
  }
}
