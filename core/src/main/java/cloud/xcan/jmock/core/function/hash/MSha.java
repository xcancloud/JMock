package cloud.xcan.jmock.core.function.hash;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_HASH;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_SHA_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_SHA_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_SHA_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_SHA_PARAMETER_VERSION;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_UNACCEPTABLE_T;
import static cloud.xcan.jmock.core.support.utils.EncryptionUtils.DEFAULT_SHA_VERSION_LIST;
import static cloud.xcan.jmock.core.support.utils.EncryptionUtils.SHA512_VERSION;
import static cloud.xcan.jmock.core.support.utils.EncryptionUtils.shaDigest;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.exception.ParamParseException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_SHA_DESC,
    categoryI18nKey = {DOC_CATEGORY_HASH}, order = 602)
public class MSha extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_SHA_PARAMETER_VERSION)
  private String version;

  @JMockConstructor(descI18nKey = DOC_SHA_C1,
      example = "@Sha()",
      exampleValues = {
          "85433ab2b5841aaced05816705ff434f4df037829ed425de03ba80e636a826d020a4e954e4f69c2cfe57abc7751bdcc9d27dc89c286b8567934f01a467221169",
          "91701de028f41ddc5319e31c21b3453ca0653cdaf1dfe410b43da0cdf5a0218053f0f0034ff696367b741a51dcc028c6676403f52955e78bc66bd49abe99ab95"})
  public MSha() {
    this(SHA512_VERSION);
  }

  @JMockConstructor(descI18nKey = DOC_SHA_C2,
      example = "@Word(SHA-224)",
      exampleValues = {"21bca972707548ae0ffac10d2fed7495e51cb62f48b31e5edd4eb257",
          "ad7cdf3b66e8d68c22ce4750557e4f7c3af1a199fd11193dfbfa1e41"})
  public MSha(String version) {
    if (!DEFAULT_SHA_VERSION_LIST.contains(version)) {
      ParamParseException.throw0(FPARAM_UNACCEPTABLE_T, new Object[]{"version"});
    }
    this.version = version;
  }

  @Override
  public String mock() {
    return shaDigest(this.version);
  }
}
