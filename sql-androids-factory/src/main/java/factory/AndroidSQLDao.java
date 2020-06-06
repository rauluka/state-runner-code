package factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import state.runner.Android;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles interactions with DB.
 */
@Component
public class AndroidSQLDao {
  private static final String FIND_ALL_ANDROIDS = "SELECT * FROM androids";
  private static final String CREATE_NEW_ANDROID = "INSERT INTO androids (identifier, first_name, last_name, version)" +
      " VALUES (:identifier, :firstName, :lastName, :version)";

  private JdbcTemplate jdbcTemplate;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public AndroidSQLDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  /**
   * Fetches all Androids from DB.
   */
  List<Android> findAllAndroids() {
    return jdbcTemplate.query(FIND_ALL_ANDROIDS, (resultSet, i) ->
        new Android(resultSet.getString("identifier"),
            resultSet.getString("first_name"),
            resultSet.getString("last_name"),
            resultSet.getString("version")));
  }

  /**
   * Inserts new state.runner.Android to DB.
   */
  void createNewAndroid() {
    Android android = new Android();
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("identifier", android.getIdentifier());
    parameters.put("firstName", android.getFirstName());
    parameters.put("lastName", android.getLastName());
    parameters.put("version", android.getVersion());
    namedParameterJdbcTemplate.update(CREATE_NEW_ANDROID, parameters);
  }
}
