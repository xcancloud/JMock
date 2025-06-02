package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MBrowser.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;

public class MDatabase extends AbstractMockFunction {

  public static final List<String> SQL_DATABASES = Arrays.asList(
      "MySQL", "PostgreSQL", "Microsoft SQL Server", "Oracle Database",
      "SQLite", "MariaDB", "Amazon Aurora", "CockroachDB"
  );

  public static final List<String> NOSQL_DATABASES = Arrays.asList(
      "MongoDB", "Cassandra", "Redis", "Couchbase", "Amazon DynamoDB",
      "Firebase Realtime Database", "Elasticsearch", "Neo4j"
  );

  public static final List<String> NEWSQL_DATABASES = Arrays.asList(
      "Google Spanner", "TiDB", "YugabyteDB", "CockroachDB"
  );

  public MDatabase() {
  }

  @Override
  public String mock() {
    int category = random.nextInt(100);
    if (category < 60) {
      // SQL databases (60% probability)
      return SQL_DATABASES.get(random.nextInt(SQL_DATABASES.size()));
    } else if (category < 90) {
      // NoSQL databases (30% probability)
      return NOSQL_DATABASES.get(random.nextInt(NOSQL_DATABASES.size()));
    } else {
      // NewSQL databases (10% probability)
      return NEWSQL_DATABASES.get(random.nextInt(NEWSQL_DATABASES.size()));
    }
  }
}
