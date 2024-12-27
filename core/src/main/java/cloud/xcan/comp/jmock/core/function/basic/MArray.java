package cloud.xcan.comp.jmock.core.function.basic;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MARRAY_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MARRAY_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MARRAY_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_GENEXP_T;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_NOT_NULL_T;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_SIZE_T;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.api.MockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.exception.ParamParseException;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.comp.jmock.core.parser.extractor.DefaultMockExpressionExtractor;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Deprecated // TODO 重新设计、不支持函数嵌套 -> 不以嵌套函数方式实现，使用命名参数方式实现：批量表达式、数据格式、函数名、函数参数列表
@Getter
@Setter
//@JMockFunctionRegister(descI18nKey = DOC_MARRAY_DESC,
//    tags = {"classification=BASIC", "scope=API_PARAMETER_VALUE"}, order = 1)
public class MArray extends AbstractMockFunction {

  @JMockParameter(descI18nKey = "")
  private int size;

  @JMockParameter(descI18nKey = "")
  private String genExp;

  private MockFunction mockFunc;

  public static MockFunction DEFAULT_MOCK = new MString();

  /**
   * Max size of Array allowed
   */
  public final static int DEFAULT_MAX_SIZE = 100000;

  public final static int DEFAULT_SIZE = 10;

  @JMockConstructor(descI18nKey = DOC_MARRAY_C1,
      example = "@Array()",
      exampleValues = {"ceja7d", "ceji7d", "koiuyt", "y7gfde", "0okjhg", "lkgfre", "kihgf6",
          "lpoiu8", "jknht6", "lkj876"})
  public MArray() {
    this.size = DEFAULT_SIZE;
    this.mockFunc = DEFAULT_MOCK;
  }

  @JMockConstructor(descI18nKey = DOC_MARRAY_C2,
      example = "@Array(@Double())",
      exampleValues = {"79.90", "788.90", "799.90", "99.90", "888.90", "666.90", "908.90",
          "790.90", "7889.90", "8856.90"})
  public MArray(String genExp) {
    this(DEFAULT_SIZE, genExp);
  }

  /**
   * Used to generate random values from regular expressions
   *
   * @param size   The number of generated data is not specified, and the default is 10; Maximum one
   *               time allowed 2147483647
   * @param genExp Data generation expression, supported annotation types: mstring, mitteger, mlong,
   *               mfload, mdouble, mbool, menum, mregexp
   */
  @JMockConstructor(descI18nKey = DOC_MARRAY_C3,
      example = "@Array(2，@Double())",
      exampleValues = {"12.99"})
  public MArray(Integer size, String genExp) {
    if (size == null || size < 0 || size > DEFAULT_MAX_SIZE) {
      ParamParseException.throw0(FPARAM_SIZE_T, new Object[]{"size", 0, DEFAULT_MAX_SIZE});
    }

    if (genExp == null) {
      ParamParseException.throw0(FPARAM_NOT_NULL_T, new Object[]{"genExp"});
    }

    List<FunctionToken> tokens = new DefaultMockExpressionExtractor(genExp).extract();
    if (tokens.isEmpty()) {
      ParamParseException.throw0(FPARAM_GENEXP_T, new Object[]{"genExp"});
    }

    try {
      mockFunc = new SimpleMockFunctionTokenParser().parse(tokens.get(0));
    } catch (Exception e) {
      ParamParseException.throw0(FPARAM_GENEXP_T, new Object[]{"genExp"});
    }
    if (mockFunc == null) {
      ParamParseException.throw0(FPARAM_GENEXP_T, new Object[]{"genExp"});
    }

    this.size = size;
    this.genExp = genExp;
  }

  @Override
  public Object[] mock() {
    Object[] arr = new Object[size];
    for (int i = 0; i < size; i++) {
      arr[i] = mockFunc.mock();
    }
    return arr;
  }

}
