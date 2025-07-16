package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class CarDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 20xx.
   */
  public final static String DOC_CATEGORY_CAR = "jmock.func.category.car";

  public final static String DOC_BRAND_DESC = "jmock.func.MBrand.description";
  public final static String DOC_BRAND_C1 = "jmock.func.MBrand.C1";

  public final static String DOC_DRIVETRAIN_DESC = "jmock.func.MDrivetrain.description";
  public final static String DOC_DRIVETRAIN_C1 = "jmock.func.MDrivetrain.C1";

  public final static String DOC_ENGINE_DESC = "jmock.func.MEngine.description";
  public final static String DOC_ENGINE_C1 = "jmock.func.MEngine.C1";

  public final static String DOC_HORSEPOWER_DESC = "jmock.func.MHorsepower.description";
  public final static String DOC_HORSEPOWER_C1 = "jmock.func.MHorsepower.C1";

  public final static String DOC_PLATE_DESC = "jmock.func.MPlate.description";
  public final static String DOC_PLATE_C1 = "jmock.func.MPlate.C1";

  public final static String DOC_VEHICLE_DESC = "jmock.func.MVehicle.description";
  public final static String DOC_VEHICLE_C1 = "jmock.func.MVehicle.C1";

  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-car-plugin-messages");
  }
}
