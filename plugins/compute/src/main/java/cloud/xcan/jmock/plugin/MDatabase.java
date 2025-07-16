package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_DATABASE_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_DATABASE_DESC;
import static cloud.xcan.jmock.plugin.MBrowser.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.Arrays;
import java.util.List;

@JMockFunctionRegister(descI18nKey = DOC_DATABASE_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5006)
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

  @JMockConstructor(descI18nKey = DOC_DATABASE_C1,
      example = "@Database()",
      exampleValues = {"MariaDB", "Amazon DynamoDB"})
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
