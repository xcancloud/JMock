package cloud.xcan.comp.jmock.core.function.basic.marray;

import static cloud.xcan.comp.jmock.core.function.basic.MArray.DEFAULT_SIZE;
import static cloud.xcan.comp.jmock.core.function.basic.MDouble.DEFAULT_MAX_VALUE;
import static cloud.xcan.comp.jmock.core.function.basic.MString.DEFAULT_LENGTH;

import cloud.xcan.comp.jmock.core.function.basic.MArray;
import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class MArrayMockTest {

  @Test
  public void case1_defaultSizeTest() throws Exception {
    FunctionToken token = new FunctionToken("MArray", new String[]{"@String()"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MArray mock = (MArray) parser.parse(token);
    Object[] arr = mock.mock();
    System.out.println("value: " + Arrays.toString(arr));
    Assert.assertEquals(DEFAULT_SIZE, arr.length);
    Assert.assertEquals(String.class, arr[0].getClass());
    Assert.assertEquals(DEFAULT_LENGTH, ((String) arr[0]).length());
  }

  @Test
  public void case2_defaultSizeAndMockTest() throws Exception {
    FunctionToken token = new FunctionToken("MArray", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MArray mock = (MArray) parser.parse(token);
    Object[] arr = mock.mock();
    System.out.println("value: " + Arrays.toString(arr));
    Assert.assertEquals(DEFAULT_SIZE, arr.length);
    Assert.assertEquals(String.class, arr[0].getClass());
    Assert.assertEquals(DEFAULT_LENGTH, ((String) arr[0]).length());
  }

  @Test
  public void case3_sizeAndMockTest() throws Exception {
    FunctionToken token = new FunctionToken("MArray", new String[]{"20", "@Integer()"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MArray mock = (MArray) parser.parse(token);
    Object[] arr = mock.mock();
    System.out.println("value: " + Arrays.toString(arr));
    Assert.assertEquals(20, arr.length);
    Assert.assertEquals(Integer.class, arr[0].getClass());
    Assert.assertTrue((Integer) arr[0] > 0 || (Integer) arr[0] < DEFAULT_MAX_VALUE);
  }
}
