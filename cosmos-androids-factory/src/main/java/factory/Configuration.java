package factory;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

  @Bean
  public CosmosDbProperties cosmosDbProperties() {
    return new CosmosDbProperties();
  }

  @Bean
  public AndroidCosmosDao androidCosmosDao(CosmosDbProperties cosmosDbProperties) {
    return new AndroidCosmosDao(cosmosDbProperties);
  }
}
