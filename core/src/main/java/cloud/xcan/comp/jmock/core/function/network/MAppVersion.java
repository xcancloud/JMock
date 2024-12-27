package cloud.xcan.comp.jmock.core.function.network;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_NETWORK;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MAPP_VERSION_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MAPP_VERSION_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MAPP_VERSION_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MAPP_VERSION_C4;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MAPP_VERSION_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MAPP_VERSION_PARAMETER_BUILD_STATE_DICT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MAPP_VERSION_PARAMETER_PREFIX_DICT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MAPP_VERSION_PARAMETER_RELEASE_STATE_DICT;
import static cloud.xcan.comp.jmock.core.support.utils.AppVersionUtils.appVersion;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author bao.zhang
 * @author xiaolong.liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MAPP_VERSION_DESC,
    categoryI18nKey = {DOC_CATEGORY_NETWORK}, order = 902)
public class MAppVersion extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MAPP_VERSION_PARAMETER_PREFIX_DICT)
  private String prefixDict;

  @JMockParameter(descI18nKey = DOC_MAPP_VERSION_PARAMETER_RELEASE_STATE_DICT)
  private String releaseStateDict;

  @JMockParameter(descI18nKey = DOC_MAPP_VERSION_PARAMETER_BUILD_STATE_DICT)
  private String buildStateDict;

  private String[] prefixDictArray;

  private String[] releaseStateDictArray;

  private String[] buildStateDictArray;

  @JMockConstructor(descI18nKey = DOC_MAPP_VERSION_C1,
      example = "@AppVersion()",
      exampleValues = {"135.50.149", "199.31.103"})
  public MAppVersion() {
    this(null, null, null);
  }

  @JMockConstructor(descI18nKey = DOC_MAPP_VERSION_C2,
      example = "@AppVersion(v|wchat)",
      exampleValues = {"v-74.153.115", "m-223.100.154"})
  public MAppVersion(String prefixDict) {
    this(prefixDict, null, null);
  }

  @JMockConstructor(descI18nKey = DOC_MAPP_VERSION_C3,
      example = "@AppVersion(v|wchat,SNAPSHOT|BETA|RELEASE)",
      exampleValues = {"v-237.22.12-SNAPSHOT", "m-110.126.74-BETA"})
  public MAppVersion(String prefixDict, String releaseStateDict) {
    this(prefixDict, releaseStateDict, null);
  }

  @JMockConstructor(descI18nKey = DOC_MAPP_VERSION_C4,
      example = "@AppVersion(v|wchat,SNAPSHOT|BETA|RELEASE,build.1|build.2)",
      exampleValues = {"v-78.225.93-BETA+build2", "m-250.204.99-RELEASE+build1"})
  public MAppVersion(String prefixDict, String releaseStateDict, String buildStateDict) {
    if (StringUtils.isNotBlank(prefixDict)) {
      this.prefixDictArray = prefixDict.split("\\|");
    }
    if (StringUtils.isNotBlank(releaseStateDict)) {
      this.releaseStateDictArray = releaseStateDict.split("\\|");
    }
    if (StringUtils.isNotBlank(buildStateDict)) {
      this.buildStateDictArray = buildStateDict.split("\\|");
    }
  }

  @Override
  public String mock() {
    return appVersion(prefixDictArray, releaseStateDictArray, buildStateDictArray);
  }
}
