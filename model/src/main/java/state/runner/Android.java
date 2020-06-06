package state.runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UncheckedIOException;
import java.util.Random;
import java.util.UUID;

/**
 * state.runner.Android model.
 */
public class Android {
  private static final String[] FIRST_NAMES = {"ROY", "PRIS", "JOHN", "RACHAEL", "LEON", "ZHORA", "HODGE", "MARY"};
  private static final String[] LAST_NAMES = {"BATTY", "STRATTON", "SALOME", "KOWALSKI", "BRADY", "SMITH"};
  private static final String[] VERSIONS = {"NEXUS-6", "NEXUS-7", "NEXUS-9"};

  private String identifier;
  private String firstName;
  private String lastName;
  private String version;

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  public Android(String identifier, String firstName, String lastName, String version) {
    this.identifier = identifier;
    this.firstName = firstName;
    this.lastName = lastName;
    this.version = version;
  }

  /**
   * Build random state.runner.Android model.
   */
  public Android() {
    Random rand = new Random(System.currentTimeMillis());
    this.firstName = FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)];
    this.lastName = LAST_NAMES[rand.nextInt(LAST_NAMES.length)];
    this.version = VERSIONS[rand.nextInt(VERSIONS.length)];
    this.identifier = UUID.randomUUID().toString();
  }

  public String toJson() {
    try {
      return OBJECT_MAPPER.writeValueAsString(this);
    } catch (JsonProcessingException ex) {
      throw new UncheckedIOException("What can I do?", ex);
    }
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return "state.runner.Android{" +
        "identifier='" + identifier + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", version='" + version + '\'' +
        '}';
  }
}
