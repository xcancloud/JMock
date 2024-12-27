package cloud.xcan.comp.jmock.core.function.basic.marray;


import static cloud.xcan.comp.jmock.core.function.basic.MArray.DEFAULT_MOCK;
import static cloud.xcan.comp.jmock.core.function.basic.MArray.DEFAULT_SIZE;

import cloud.xcan.comp.jmock.core.function.basic.MArray;
import cloud.xcan.comp.jmock.core.function.basic.MDouble;
import cloud.xcan.comp.jmock.core.function.basic.MString;
import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MArrayConstrouctorTest {

  @Test
  public void MArray1() throws Exception {
    FunctionToken token = new FunctionToken("MArray", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MArray mock = (MArray) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(DEFAULT_SIZE, mock.getSize());
    Assert.assertEquals(DEFAULT_MOCK, mock.getMockFunc());
  }

  @Test
  public void MArray2() throws Exception {
    FunctionToken token = new FunctionToken("MArray", new String[]{"@String()"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MArray mock = (MArray) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(DEFAULT_SIZE, mock.getSize());
    Assert.assertEquals("@String()", mock.getGenExp());
    Assert.assertEquals(new MString().getClass(), mock.getMockFunc().getClass());
  }

  @Test
  public void MArray3() throws Exception {
    FunctionToken token = new FunctionToken("MArray", new String[]{"12", "@Double()"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MArray mock = (MArray) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(12, mock.getSize());
    Assert.assertEquals("@Double()", mock.getGenExp());
    Assert.assertEquals(new MDouble().getClass(), mock.getMockFunc().getClass());
  }

}
