package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class GeographyDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 5xx.
   */
  public static final String DOC_CATEGORY_GEOGRAPHY = "jmock.func.category.geography";

  public static final String DATA_COUNTRY = "jmock.func.data.country";
  public static final String DATA_PROVINCE = "jmock.func.data.province";
  public static final String DATA_CITY = "jmock.func.data.city";
  public static final String DATA_ADDRESS = "jmock.func.data.address";
  public static final String DATA_ZIP = "jmock.func.data.zip";

  public static final String DOC_ADDRESS_DESC = "jmock.func.MAddress.description";
  public static final String DOC_ADDRESS_C1 = "jmock.func.MAddress.C1";
  public static final String DOC_ADDRESS_C2 = "jmock.func.MAddress.C2";
  public static final String DOC_ADDRESS_C3 = "jmock.func.MAddress.C3";

  public static final String DOC_COUNTRY_DESC = "jmock.func.MCountry.description";
  public static final String DOC_COUNTRY_C1 = "jmock.func.MCountry.C1";
  public static final String DOC_COUNTRY_C2 = "jmock.func.MCountry.C2";
  public static final String DOC_COUNTRY_C3 = "jmock.func.MCountry.C3";

  public static final String DOC_PROVINCE_DESC = "jmock.func.MProvince.description";
  public static final String DOC_PROVINCE_C1 = "jmock.func.MProvince.C1";
  public static final String DOC_PROVINCE_C2 = "jmock.func.MProvince.C2";
  public static final String DOC_PROVINCE_C3 = "jmock.func.MProvince.C3";

  public static final String DOC_CITY_DESC = "jmock.func.MCity.description";
  public static final String DOC_CITY_C1 = "jmock.func.MCity.C1";
  public static final String DOC_CITY_C2 = "jmock.func.MCity.C2";
  public static final String DOC_CITY_C3 = "jmock.func.MCity.C3";

  public static final String DOC_ZIP_DESC = "jmock.func.MZip.description";
  public static final String DOC_ZIP_PARAMETER_DICT = "jmock.func.MZip.parameter.dict";
  public static final String DOC_ZIP_C1 = "jmock.func.MZip.C1";
  public static final String DOC_ZIP_C2 = "jmock.func.MZip.C2";
  public static final String DOC_ZIP_C3 = "jmock.func.MZip.C3";

  public static final String DOC_COORDINATES_DESC = "jmock.func.MCoordinates.description";
  public static final String DOC_COORDINATES_PARAMETER_MIN_LNG = "jmock.func.MCoordinates.parameter.minLng";
  public static final String DOC_COORDINATES_PARAMETER_MAX_LNG = "jmock.func.MCoordinates.parameter.maxLng";
  public static final String DOC_COORDINATES_PARAMETER_MIN_LAT = "jmock.func.MCoordinates.parameter.minLat";
  public static final String DOC_COORDINATES_PARAMETER_MAX_LAT = "jmock.func.MCoordinates.parameter.maxLat";
  public static final String DOC_COORDINATES_PARAMETER_SCALE = "jmock.func.MCoordinates.parameter.scale";
  public static final String DOC_COORDINATES_C1 = "jmock.func.MCoordinates.C1";
  public static final String DOC_COORDINATES_C2 = "jmock.func.MCoordinates.C2";

  public static final String DOC_LATITUDE_DESC = "jmock.func.MLatitude.description";
  public static final String DOC_LATITUDE_C1 = "jmock.func.MLatitude.C1";
  public static final String DOC_LATITUDE_C2 = "jmock.func.MLatitude.C2";

  public static final String DOC_LONGITUDE_DESC = "jmock.func.MLongitude.description";
  public static final String DOC_LONGITUDE_C1 = "jmock.func.MLongitude.C1";
  public static final String DOC_LONGITUDE_C2 = "jmock.func.MLongitude.C2";

  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-geography-plugin-messages");
  }
}
