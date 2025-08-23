package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockMessage.PARAM_UNACCEPTABLE_T;
import static cloud.xcan.jmock.api.support.utils.EncryptionUtils.DEFAULT_MD5_LENGTH_LIST;
import static cloud.xcan.jmock.api.support.utils.EncryptionUtils.MD5_32_LENGTH;
import static cloud.xcan.jmock.api.support.utils.EncryptionUtils.md5Digest;
import static cloud.xcan.jmock.plugin.HashDocMessage.DOC_CATEGORY_HASH;
import static cloud.xcan.jmock.plugin.HashDocMessage.DOC_MD5_C1;
import static cloud.xcan.jmock.plugin.HashDocMessage.DOC_MD5_C2;
import static cloud.xcan.jmock.plugin.HashDocMessage.DOC_MD5_DESC;
import static cloud.xcan.jmock.plugin.HashDocMessage.DOC_MD5_PARAMETER_LENGTH;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.exception.ParamParseException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Bao Zhang
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MD5_DESC,
    categoryI18nKey = {DOC_CATEGORY_HASH}, order = 601)
public class MMd5 extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MD5_PARAMETER_LENGTH)
  private int length;

  @JMockConstructor(descI18nKey = DOC_MD5_C1,
      example = "@Md5()",
      exampleValues = {"E2FC714C4727EE9395F324CD2E7F331F"})
  public MMd5() {
    this(MD5_32_LENGTH);
  }

  @JMockConstructor(descI18nKey = DOC_MD5_C2,
      example = "@Md5(16)",
      exampleValues = {"4727EE9395F324CD"})
  public MMd5(int length) {
    if (!DEFAULT_MD5_LENGTH_LIST.contains(length)) {
      ParamParseException.throw0(PARAM_UNACCEPTABLE_T, new Object[]{"length"});
    }
    this.length = length;
  }

  @Override
  public String mock() {
    return md5Digest(this.length);
  }
}
