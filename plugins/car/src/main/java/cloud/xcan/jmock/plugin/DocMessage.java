package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;

public class DocMessage {

  static {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-car-plugin-messages");
  }

  /**
   * Coding order by 20xx.
   */
  public final static String DOC_CATEGORY_CAR = "jmock.func.category.car";

  public final static String DOC_MBRAND_DESC = "jmock.func.MBrand.description";
  public final static String DOC_MBRAND_C1 = "jmock.func.MBrand.C1";

  public final static String DOC_MDRIVETRAIN_DESC = "jmock.func.MDrivetrain.description";
  public final static String DOC_MDRIVETRAIN_C1 = "jmock.func.MDrivetrain.C1";

  public final static String DOC_MENGINE_DESC = "jmock.func.MEngine.description";
  public final static String DOC_MENGINE_C1 = "jmock.func.MEngine.C1";

  public final static String DOC_MHORSEPOWER_DESC = "jmock.func.MHorsepower.description";
  public final static String DOC_MHORSEPOWER_C1 = "jmock.func.MHorsepower.C1";

  public final static String DOC_MPLATE_DESC = "jmock.func.MPlate.description";
  public final static String DOC_MPLATE_C1 = "jmock.func.MPlate.C1";

  public final static String DOC_MVEHICLE_DESC = "jmock.func.MVehicle.description";
  public final static String DOC_MVEHICLE_C1 = "jmock.func.MVehicle.C1";

}
