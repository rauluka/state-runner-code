package factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Simple Azure CosmosDB based app that creates state.runner.Android and lists all available Androids.
 */
@SpringBootApplication
@EnableConfigurationProperties(CosmosDbProperties.class)
public class AppCosmosDb implements CommandLineRunner {
  private static final Logger log = LoggerFactory.getLogger(AppCosmosDb.class);

  private final ScheduledExecutorService executorService;
  private AndroidCosmosDao androidCosmosDao;

  @Autowired
  public AppCosmosDb(AndroidCosmosDao androidCosmosDao) {
    this.androidCosmosDao = androidCosmosDao;
    this.executorService = Executors.newSingleThreadScheduledExecutor();
  }

  public static void main(String[] args) {
    SpringApplication.run(AppCosmosDb.class, args);
  }

  @Override
  public void run(String... strings) {
    executorService.scheduleAtFixedRate(() -> {
      try {
        androidCosmosDao.createNewAndroid();
        log.info("Number of available androids: {}", androidCosmosDao.findAllAndroids().size());
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }, 0, 200, TimeUnit.MILLISECONDS);
  }
}
