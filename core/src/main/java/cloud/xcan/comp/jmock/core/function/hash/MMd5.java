package cloud.xcan.comp.jmock.core.function.hash;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_HASH;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MMD5_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MMD5_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MMD5_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MMD5_PARAMETER_LENGTH;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_UNACCEPTABLE_T;
import static cloud.xcan.comp.jmock.core.support.utils.EncryptionUtils.DEFAULT_MD5_LENGTH_LIST;
import static cloud.xcan.comp.jmock.core.support.utils.EncryptionUtils.MD5_32_LENGTH;
import static cloud.xcan.comp.jmock.core.support.utils.EncryptionUtils.md5Digest;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.exception.ParamParseException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bao.zhang
 * @author xiaolong.liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MMD5_DESC,
    categoryI18nKey = {DOC_CATEGORY_HASH}, order = 601)
public class MMd5 extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MMD5_PARAMETER_LENGTH)
  private int length;

  @JMockConstructor(descI18nKey = DOC_MMD5_C1,
      example = "@Md5()",
      exampleValues = {"E2FC714C4727EE9395F324CD2E7F331F"})
  public MMd5() {
    this(MD5_32_LENGTH);
  }

  @JMockConstructor(descI18nKey = DOC_MMD5_C2,
      example = "@Md5(16)",
      exampleValues = {"4727EE9395F324CD"})
  public MMd5(int length) {
    if (!DEFAULT_MD5_LENGTH_LIST.contains(length)) {
      ParamParseException.throw0(FPARAM_UNACCEPTABLE_T, new Object[]{"length"});
    }
    this.length = length;
  }

  @Override
  public String mock() {
    return md5Digest(this.length);
  }
}
