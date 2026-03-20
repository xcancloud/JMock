package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_NEWSQL_DATABASES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_NOSQL_DATABASES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_SQL_DATABASES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_DATABASE_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_DATABASE_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_DATABASE_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5006)
public class MDatabase extends AbstractMockFunction {

  private final String[] sqlDatabases;
  private final String[] nosqlDatabases;
  private final String[] newsqlDatabases;

  @JMockConstructor(descI18nKey = DOC_DATABASE_C1,
      example = "@Database()",
      exampleValues = {"MariaDB", "Amazon DynamoDB"})
  public MDatabase() {
    this.sqlDatabases = getString(DATA_SQL_DATABASES).split("\\|");
    this.nosqlDatabases = getString(DATA_NOSQL_DATABASES).split("\\|");
    this.newsqlDatabases = getString(DATA_NEWSQL_DATABASES).split("\\|");
  }

  @Override
  public String mock() {
    int category = JMockRandom.nextInt(100);
    if (category < 60) {
      return sqlDatabases[JMockRandom.nextInt(sqlDatabases.length)];
    } else if (category < 90) {
      return nosqlDatabases[JMockRandom.nextInt(nosqlDatabases.length)];
    } else {
      return newsqlDatabases[JMockRandom.nextInt(newsqlDatabases.length)];
    }
  }
}
