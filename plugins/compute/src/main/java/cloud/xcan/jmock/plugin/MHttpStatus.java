package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_GPU_MODEL_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_HTTP_STATUS_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_HTTP_STATUS_DESC;
import static cloud.xcan.jmock.plugin.MBrowser.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JMockFunctionRegister(descI18nKey = DOC_HTTP_STATUS_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5012)
public class MHttpStatus extends AbstractMockFunction {

  public static final Map<Integer, String> HTTP_STATUS_CODES = new HashMap<>();

  static {
    HTTP_STATUS_CODES.put(200, "OK");
    HTTP_STATUS_CODES.put(201, "Created");
    HTTP_STATUS_CODES.put(202, "Accepted");
    HTTP_STATUS_CODES.put(203, "Non-Authoritative Information");
    HTTP_STATUS_CODES.put(204, "No Content");
    HTTP_STATUS_CODES.put(205, "Reset Content");
    HTTP_STATUS_CODES.put(206, "Partial Content");
    HTTP_STATUS_CODES.put(207, "Multi-Status");
    HTTP_STATUS_CODES.put(301, "Moved Permanently");
    HTTP_STATUS_CODES.put(302, "Found");
    HTTP_STATUS_CODES.put(304, "Not Modified");
    HTTP_STATUS_CODES.put(400, "Bad Request");
    HTTP_STATUS_CODES.put(401, "Unauthorized");
    HTTP_STATUS_CODES.put(403, "Forbidden");
    HTTP_STATUS_CODES.put(404, "Not Found");
    HTTP_STATUS_CODES.put(405, "Method Not Allowed");
    HTTP_STATUS_CODES.put(500, "Internal Server Error");
    HTTP_STATUS_CODES.put(501, "Not Implemented");
    HTTP_STATUS_CODES.put(502, "Bad Gateway");
    HTTP_STATUS_CODES.put(503, "Service Unavailable");
    HTTP_STATUS_CODES.put(504, "Gateway Timeout");
  }

  @JMockConstructor(descI18nKey = DOC_HTTP_STATUS_C1,
      example = "@HttpStatus()",
      exampleValues = {"302 Found", "500 Internal Server Error"})
  public MHttpStatus() {
  }

  @Override
  public String mock() {
    List<Integer> codes = new ArrayList<>(HTTP_STATUS_CODES.keySet());
    int code = codes.get(random.nextInt(codes.size()));
    return code + " " + HTTP_STATUS_CODES.get(code);
  }
}
