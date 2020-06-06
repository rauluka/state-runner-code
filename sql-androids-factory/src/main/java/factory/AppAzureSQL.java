package factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Simple AzureSQL based app that creates state.runner.Android and lists all available Androids.
 */
@SpringBootApplication
public class AppAzureSQL implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(AppAzureSQL.class);

  private final ScheduledExecutorService executorService;
  private AndroidSQLDao androidSQLDao;

  @Autowired
  public AppAzureSQL(AndroidSQLDao androidSQLDao) {
    this.androidSQLDao = androidSQLDao;
    this.executorService = Executors.newSingleThreadScheduledExecutor();
  }

  public static void main(String[] args) {
    SpringApplication.run(AppAzureSQL.class, args);
  }

  @Override
  public void run(String... strings) {
    executorService.scheduleAtFixedRate(() -> {
      try {
        androidSQLDao.createNewAndroid();
        log.info("Number of available androids: {}", androidSQLDao.findAllAndroids().size());
      } catch (Exception ex) {
        ex.printStackTrace();
      }

    }, 0, 200, TimeUnit.MILLISECONDS);
  }
}
