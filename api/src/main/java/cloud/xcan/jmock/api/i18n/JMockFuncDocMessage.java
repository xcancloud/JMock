package cloud.xcan.jmock.api.i18n;

public interface JMockFuncDocMessage {

  /**
   * Coding order by 1xx.
   */
  String DOC_CATEGORY_BASIC = "jmock.func.category.basic";
  /**
   * Coding order by 2xx.
   */
  String DOC_CATEGORY_DATE = "jmock.func.category.date";
  /**
   * Coding order by 3xx.
   */
  String DOC_CATEGORY_ID = "jmock.func.category.id";
  /**
   * Coding order by 4xx.
   */
  String DOC_CATEGORY_ARTICLE = "jmock.func.category.text";
  /**
   * Coding order by 5xx.
   */
  String DOC_CATEGORY_GEOGRAPHY = "jmock.func.category.geography";
  /**
   * Coding order by 6xx.
   */
  String DOC_CATEGORY_HASH = "jmock.func.category.hash";
  /**
   * Coding order by 7xx.
   */
  String DOC_CATEGORY_NETWORK = "jmock.func.category.network";
  /**
   * Coding order by 8xx.
   */
  String DOC_CATEGORY_USER = "jmock.func.category.user";
  /**
   * Coding order by 9xx.
   */
  String DOC_CATEGORY_LOCALE = "jmock.func.category.locale";
  /**
   * Coding order by 10xx.
   */
  String DOC_CATEGORY_WEB = "jmock.func.category.web";

  String DOC_PARAMETER_LOCALE = "jmock.func.parameter.locale";
  String DOC_PARAMETER_DICT = "jmock.func.parameter.dict";
  String DOC_PARAMETER_NULL_WEIGHT = "jmock.func.parameter.nullWeight";

  String DOC_STRING_DESC = "jmock.func.MString.description";
  String DOC_STRING_PARAMETER_LENGTH = "jmock.func.MString.parameter.length";
  String DOC_STRING_PARAMETER_MIN = "jmock.func.MString.parameter.min";
  String DOC_STRING_PARAMETER_MAX = "jmock.func.MString.parameter.max";
  String DOC_STRING_PARAMETER_CHARS = "jmock.func.MString.parameter.chars";
  String DOC_STRING_C1 = "jmock.func.MString.C1";
  String DOC_STRING_C2 = "jmock.func.MString.C2";
  String DOC_STRING_C3 = "jmock.func.MString.C3";
  String DOC_STRING_C4 = "jmock.func.MString.C4";
  String DOC_STRING_C5 = "jmock.func.MString.C5";
  String DOC_STRING_C6 = "jmock.func.MString.C6";

  String DOC_INTEGER_DESC = "jmock.func.MInteger.description";
  String DOC_INTEGER_PARAMETER_MIN = "jmock.func.MInteger.parameter.min";
  String DOC_INTEGER_PARAMETER_MAX = "jmock.func.MInteger.parameter.max";
  String DOC_INTEGER_C1 = "jmock.func.MInteger.C1";
  String DOC_INTEGER_C2 = "jmock.func.MInteger.C2";
  String DOC_INTEGER_C3 = "jmock.func.MInteger.C3";
  String DOC_INTEGER_C4 = "jmock.func.MInteger.C4";

  String DOC_LONG_DESC = "jmock.func.MLong.description";
  String DOC_LONG_PARAMETER_MIN = "jmock.func.MLong.parameter.min";
  String DOC_LONG_PARAMETER_MAX = "jmock.func.MLong.parameter.max";
  String DOC_LONG_C1 = "jmock.func.MLong.C1";
  String DOC_LONG_C2 = "jmock.func.MLong.C2";
  String DOC_LONG_C3 = "jmock.func.MLong.C3";
  String DOC_LONG_C4 = "jmock.func.MLong.C4";

  String DOC_FLOAT_DESC = "jmock.func.MFloat.description";
  String DOC_FLOAT_PARAMETER_MIN = "jmock.func.MFloat.parameter.min";
  String DOC_FLOAT_PARAMETER_MAX = "jmock.func.MFloat.parameter.max";
  String DOC_FLOAT_PARAMETER_SCALE = "jmock.func.MFloat.parameter.scale";
  String DOC_FLOAT_C1 = "jmock.func.MFloat.C1";
  String DOC_FLOAT_C2 = "jmock.func.MFloat.C2";
  String DOC_FLOAT_C3 = "jmock.func.MFloat.C3";
  String DOC_FLOAT_C4 = "jmock.func.MFloat.C4";
  String DOC_FLOAT_C5 = "jmock.func.MFloat.C5";
  String DOC_FLOAT_C6 = "jmock.func.MFloat.C6";

  String DOC_DOUBLE_DESC = "jmock.func.MDouble.description";
  String DOC_DOUBLE_PARAMETER_MIN = "jmock.func.MDouble.parameter.min";
  String DOC_DOUBLE_PARAMETER_MAX = "jmock.func.MDouble.parameter.max";
  String DOC_DOUBLE_PARAMETER_SCALE = "jmock.func.MDouble.parameter.scale";
  String DOC_DOUBLE_C1 = "jmock.func.MDouble.C1";
  String DOC_DOUBLE_C2 = "jmock.func.MDouble.C2";
  String DOC_DOUBLE_C3 = "jmock.func.MDouble.C3";
  String DOC_DOUBLE_C4 = "jmock.func.MDouble.C4";
  String DOC_DOUBLE_C5 = "jmock.func.MDouble.C5";
  String DOC_DOUBLE_C6 = "jmock.func.MDouble.C6";

  String DOC_BOOL_DESC = "jmock.func.MBool.description";
  String DOC_BOOL_PARAMETER_TRUE_WEIGHT = "jmock.func.MBool.parameter.trueWeight";
  String DOC_BOOL_PARAMETER_DICT = "jmock.func.MBool.parameter.dict";
  String DOC_BOOL_C1 = "jmock.func.MBool.C1";
  String DOC_BOOL_C2 = "jmock.func.MBool.C2";
  String DOC_BOOL_C3 = "jmock.func.MBool.C3";
  String DOC_BOOL_C4 = "jmock.func.MBool.C4";

  String DOC_ENUM_DESC = "jmock.func.MEnum.description";
  String DOC_ENUM_PARAMETER_DICT = "jmock.func.MEnum.parameter.dict";
  String DOC_ENUM_PARAMETER_VALUE_WEIGHT = "jmock.func.MEnum.parameter.valueWeight";
  String DOC_ENUM_C1 = "jmock.func.MEnum.C1";
  String DOC_ENUM_C2 = "jmock.func.MEnum.C2";
  String DOC_ENUM_C3 = "jmock.func.MEnum.C3";

  String DOC_REGEXP_DESC = "jmock.func.MRegexp.description";
  String DOC_REGEXP_PARAMETER_REGEXP = "jmock.func.MRegexp.parameter.regexp";
  String DOC_REGEXP_C1 = "jmock.func.MRegexp.C1";
  String DOC_REGEXP_C2 = "jmock.func.MRegexp.C2";

  String DOC_ARRAY_DESC = "jmock.func.MArray.description";
  String DOC_ARRAY_C1 = "jmock.func.MArray.C1";
  String DOC_ARRAY_C2 = "jmock.func.MArray.C2";
  String DOC_ARRAY_C3 = "jmock.func.MArray.C3";

  String DOC_LOCALE_DATE_DESC = "jmock.func.MLocaleDate.description";
  String DOC_LOCALE_DATE_PARAMETER_FORMAT = "jmock.func.MLocaleDate.parameter.format";
  String DOC_LOCALE_DATE_PARAMETER_ZONEID = "jmock.func.MLocaleDate.parameter.zoneId";
  String DOC_LOCALE_DATE_PARAMETER_RANDOM = "jmock.func.MLocaleDate.parameter.random";
  String DOC_LOCALE_DATE_C1 = "jmock.func.MLocaleDate.C1";
  String DOC_LOCALE_DATE_C2 = "jmock.func.MLocaleDate.C2";
  String DOC_LOCALE_DATE_C3 = "jmock.func.MLocaleDate.C3";
  String DOC_LOCALE_DATE_C4 = "jmock.func.MLocaleDate.C4";

  String DOC_LOCALE_TIME_DESC = "jmock.func.MLocaleTime.description";
  String DOC_LOCALE_TIME_PARAMETER_FORMAT = "jmock.func.MLocaleTime.parameter.format";
  String DOC_LOCALE_TIME_PARAMETER_RANDOM = "jmock.func.MLocaleTime.parameter.random";
  String DOC_LOCALE_TIME_C1 = "jmock.func.MLocaleTime.C1";
  String DOC_LOCALE_TIME_C2 = "jmock.func.MLocaleTime.C2";
  String DOC_LOCALE_TIME_C3 = "jmock.func.MLocaleTime.C3";
  String DOC_LOCALE_TIME_C4 = "jmock.func.MLocaleTime.C4";

  String DOC_LOCALE_DATE_TIME_DESC = "jmock.func.MLocaleDateTime.description";
  String DOC_LOCALE_DATE_TIME_PARAMETER_FORMAT = "jmock.func.MLocaleDateTime.parameter.format";
  String DOC_LOCALE_DATE_TIME_PARAMETER_RANDOM = "jmock.func.MLocaleDateTime.parameter.random";
  String DOC_LOCALE_DATE_TIME_C1 = "jmock.func.MLocaleDateTime.C1";
  String DOC_LOCALE_DATE_TIME_C2 = "jmock.func.MLocaleDateTime.C2";
  String DOC_LOCALE_DATE_TIME_C3 = "jmock.func.MLocaleDateTime.C3";
  String DOC_LOCALE_DATE_TIME_C4 = "jmock.func.MLocaleDateTime.C4";

  String DOC_TIMESTAMP_DESC = "jmock.func.MTimestamp.description";
  String DOC_TIMESTAMP_PARAMETER_UNIX = "jmock.func.MTimestamp.parameter.unix";
  String DOC_TIMESTAMP_C1 = "jmock.func.MTimestamp.C1";
  String DOC_TIMESTAMP_C2 = "jmock.func.MTimestamp.C2";

  String DOC_WEEK_DESC = "jmock.func.MWeek.description";
  String DOC_WEEK_C1 = "jmock.func.MWeek.C1";
  String DOC_WEEK_C2 = "jmock.func.MWeek.C2";

  String DOC_MONTH_DESC = "jmock.func.MMonth.description";
  String DOC_MONTH_C1 = "jmock.func.MMonth.C1";
  String DOC_MONTH_C2 = "jmock.func.MMonth.C2";

  String DOC_GUID_DESC = "jmock.func.MGuid.description";
  String DOC_GUID_PARAMETER_FORMAT = "jmock.func.MGuid.parameter.withoutSeparator";
  String DOC_GUID_C1 = "jmock.func.MGuid.C1";
  String DOC_GUID_C2 = "jmock.func.MGuid.C2";

  String DOC_UUID_DESC = "jmock.func.MUuid.description";
  String DOC_UUID_PARAMETER_FORMAT = "jmock.func.MUuid.parameter.withoutSeparator";
  String DOC_UUID_C1 = "jmock.func.MUuid.C1";
  String DOC_UUID_C2 = "jmock.func.MUuid.C2";

  String DOC_SNOWID_DESC = "jmock.func.MSnowId.description";
  String DOC_SNOWID_PARAMETER_DCID = "jmock.func.MSnowId.parameter.dcId";
  String DOC_SNOWID_PARAMETER_MID = "jmock.func.MSnowId.parameter.mId";
  String DOC_SNOWID_C1 = "jmock.func.MSnowId.C1";
  String DOC_SNOWID_C2 = "jmock.func.MSnowId.C2";

  String DOC_INCID_DESC = "jmock.func.MIncId.description";
  String DOC_INCID_PARAMETER_INIT = "jmock.func.MIncId.parameter.init";
  String DOC_INCID_PARAMETER_STEP = "jmock.func.MIncId.parameter.step";
  String DOC_INCID_C1 = "jmock.func.MIncId.C1";
  String DOC_INCID_C2 = "jmock.func.MIncId.C2";

  String DOC_LOCALE_DESC = "jmock.func.MLocale.description";
  String DOC_LOCALE_PARAMETER_JOINER = "jmock.func.MLocale.parameter.joiner";
  String DOC_LOCALE_C1 = "jmock.func.MLocale.C1";
  String DOC_LOCALE_C2 = "jmock.func.MLocale.C2";

  String DOC_TIMEZONE_DESC = "jmock.func.MTimeZone.description";
  String DOC_TIMEZONE_PARAMETER_DICT = "jmock.func.MTimeZone.parameter.dict";
  String DOC_TIMEZONE_C1 = "jmock.func.MTimeZone.C1";
  String DOC_TIMEZONE_C2 = "jmock.func.MTimeZone.C2";

  String DOC_FIRSTNAME_DESC = "jmock.func.MFirstname.description";
  String DOC_FIRSTNAME_PARAMETER_DICT = "jmock.func.MFirstname.parameter.dict";
  String DOC_FIRSTNAME_C1 = "jmock.func.MFirstname.C1";
  String DOC_FIRSTNAME_C2 = "jmock.func.MFirstname.C2";
  String DOC_FIRSTNAME_C3 = "jmock.func.MFirstname.C3";

  String DOC_LASTNAME_DESC = "jmock.func.MLastname.description";
  String DOC_LASTNAME_PARAMETER_DICT = "jmock.func.MLastname.parameter.dict";
  String DOC_LASTNAME_C1 = "jmock.func.MLastname.C1";
  String DOC_LASTNAME_C2 = "jmock.func.MLastname.C2";
  String DOC_LASTNAME_C3 = "jmock.func.MLastname.C3";

  String DOC_NAME_DESC = "jmock.func.MName.description";
  String DOC_NAME_PARAMETER_DICT = "jmock.func.MName.parameter.dict";
  String DOC_NAME_C1 = "jmock.func.MName.C1";
  String DOC_NAME_C2 = "jmock.func.MName.C2";
  String DOC_NAME_C3 = "jmock.func.MName.C3";

  String DOC_GENDER_DESC = "jmock.func.MGender.description";
  String DOC_GENDER_PARAMETER_DICT = "jmock.func.MGender.parameter.dict";
  String DOC_GENDER_C1 = "jmock.func.MGender.C1";
  String DOC_GENDER_C2 = "jmock.func.MGender.C2";
  String DOC_GENDER_C3 = "jmock.func.MGender.C3";

  String DOC_AGE_DESC = "jmock.func.MAge.description";
  String DOC_AGE_PARAMETER_MIN = "jmock.func.MAge.parameter.min";
  String DOC_AGE_PARAMETER_MAX = "jmock.func.MAge.parameter.max";
  String DOC_AGE_C1 = "jmock.func.MAge.C1";
  String DOC_AGE_C2 = "jmock.func.MAge.C2";

  String DOC_MOBILE_DESC = "jmock.func.MMobile.description";
  String DOC_MOBILE_C1 = "jmock.func.MMobile.C1";
  String DOC_MOBILE_C2 = "jmock.func.MMobile.C2";

  String DOC_LANDINE_DESC = "jmock.func.MLandline.description";
  String DOC_LANDINE_C1 = "jmock.func.MLandline.C1";
  String DOC_LANDINE_C2 = "jmock.func.MLandline.C2";

  String DOC_EMAIL_DESC = "jmock.func.MEmail.description";
  String DOC_EMAIL_PARAMETER_MIN = "jmock.func.MEmail.parameter.min";
  String DOC_EMAIL_PARAMETER_MAX = "jmock.func.MEmail.parameter.max";
  String DOC_EMAIL_PARAMETER_SUFFIX = "jmock.func.MEmail.parameter.suffix";
  String DOC_EMAIL_C1 = "jmock.func.MEmail.C1";
  String DOC_EMAIL_C2 = "jmock.func.MEmail.C2";
  String DOC_EMAIL_C3 = "jmock.func.MEmail.C3";
  String DOC_EMAIL_C4 = "jmock.func.MEmail.C4";

  String DOC_PASSWORD_DESC = "jmock.func.MPassword.description";
  String DOC_PASSWORD_PARAMETER_MIN = "jmock.func.MPassword.parameter.min";
  String DOC_PASSWORD_PARAMETER_MAX = "jmock.func.MPassword.parameter.max";
  String DOC_PASSWORD_PARAMETER_ALLOW_UPPERCASE = "jmock.func.MPassword.parameter.allowUpperCase";
  String DOC_PASSWORD_PARAMETER_ALLOW_LOWERCASE = "jmock.func.MPassword.parameter.allowLowerCase";
  String DOC_PASSWORD_PARAMETER_ALLOW_DIGITS = "jmock.func.MPassword.parameter.allowDigits";
  String DOC_PASSWORD_PARAMETER_ALLOW_SPECIALCHAR = "jmock.func.MPassword.parameter.allowSpecialChar";
  String DOC_PASSWORD_C1 = "jmock.func.MPassword.C1";
  String DOC_PASSWORD_C2 = "jmock.func.MPassword.C2";
  String DOC_PASSWORD_C3 = "jmock.func.MPassword.C3";
  String DOC_PASSWORD_C4 = "jmock.func.MPassword.C4";

  String DOC_EDUCATION_DESC = "jmock.func.MEducation.description";
  String DOC_EDUCATION_PARAMETER_DICT = "jmock.func.MEducation.parameter.dict";
  String DOC_EDUCATION_C1 = "jmock.func.MEducation.C1";
  String DOC_EDUCATION_C2 = "jmock.func.MEducation.C2";
  String DOC_EDUCATION_C3 = "jmock.func.MEducation.C3";

  String DOC_URL_DESC = "jmock.func.MUrl.description";
  String DOC_URL_PARAMETER_MAX = "jmock.func.MUrl.parameter.max";
  String DOC_URL_PARAMETER_PROTOCOL = "jmock.func.MUrl.parameter.protocol";
  String DOC_URL_PARAMETER_DOMAIN= "jmock.func.MUrl.parameter.domain";
  String DOC_URL_PARAMETER_ALLOW_QUERY = "jmock.func.MUrl.parameter.allowQueryParams";
  String DOC_URL_C1 = "jmock.func.MUrl.C1";
  String DOC_URL_C2 = "jmock.func.MUrl.C2";
  String DOC_URL_C3 = "jmock.func.MUrl.C3";

  String DOC_PROTOCOL_DESC = "jmock.func.MProtocol.description";
  String DOC_PROTOCOL_PARAMETER_DICT = "jmock.func.MProtocol.parameter.dict";
  String DOC_PROTOCOL_C1 = "jmock.func.MProtocol.C1";
  String DOC_PROTOCOL_C2 = "jmock.func.MProtocol.C2";

  String DOC_IPV4_DESC = "jmock.func.MIPv4.description";
  String DOC_IPV4_C1 = "jmock.func.MIPv4.C1";

  String DOC_IPV6_DESC = "jmock.func.MIPv6.description";
  String DOC_IPV6_C1 = "jmock.func.MIPv6.C1";

  String DOC_PORT_DESC = "jmock.func.MPort.description";
  String DOC_PORT_PARAMETER_MIN = "jmock.func.MPort.parameter.min";
  String DOC_PORT_PARAMETER_MAX = "jmock.func.MPort.parameter.max";
  String DOC_PORT_C1 = "jmock.func.MPort.C1";
  String DOC_PORT_C2 = "jmock.func.MPort.C2";

  String DOC_MAC_DESC = "jmock.func.MMac.description";
  String DOC_MAC_C1 = "jmock.func.MMac.C1";

  String DOC_APP_NAME_DESC = "jmock.func.MAppName.description";
  String DOC_APP_NAME_C1 = "jmock.func.MAppName.C1";
  String DOC_APP_NAME_C2 = "jmock.func.MAppName.C2";

  String DOC_APP_VERSION_DESC = "jmock.func.MAppVersion.description";
  String DOC_APP_VERSION_PARAMETER_PREFIX_DICT = "jmock.func.MAppVersion.parameter.prefixDict";
  String DOC_APP_VERSION_PARAMETER_RELEASE_STATE_DICT = "jmock.func.MAppVersion.parameter.releaseStateDict";
  String DOC_APP_VERSION_PARAMETER_BUILD_STATE_DICT = "jmock.func.MAppVersion.parameter.buildStateDict";
  String DOC_APP_VERSION_C1 = "jmock.func.MAppVersion.C1";
  String DOC_APP_VERSION_C2 = "jmock.func.MAppVersion.C2";
  String DOC_APP_VERSION_C3 = "jmock.func.MAppVersion.C3";
  String DOC_APP_VERSION_C4 = "jmock.func.MAppVersion.C4";

  String DOC_ARTICLE_DESC = "jmock.func.MArticle.description";
  String DOC_ARTICLE_PARAMETER_PARAGRAPH_COUNT = "jmock.func.MArticle.parameter.paragraphCount";
  String DOC_ARTICLE_PARAMETER_WORD_COUNT = "jmock.func.MArticle.parameter.wordCount";
  String DOC_ARTICLE_C1 = "jmock.func.MArticle.C1";
  String DOC_ARTICLE_C2 = "jmock.func.MArticle.C2";
  String DOC_ARTICLE_C3 = "jmock.func.MArticle.C3";
  String DOC_ARTICLE_C4 = "jmock.func.MArticle.C4";

  String DOC_PARAGRAPH_DESC = "jmock.func.MParagraph.description";
  String DOC_PARAGRAPH_C1 = "jmock.func.MParagraph.C1";
  String DOC_PARAGRAPH_C2 = "jmock.func.MParagraph.C2";
  String DOC_PARAGRAPH_C3 = "jmock.func.MParagraph.C3";

  String DOC_SENTENCE_DESC = "jmock.func.MSentence.description";
  String DOC_SENTENCE_C1 = "jmock.func.MSentence.C1";
  String DOC_SENTENCE_C2 = "jmock.func.MSentence.C2";
  String DOC_SENTENCE_C3 = "jmock.func.MSentence.C3";

  String DOC_WORD_DESC = "jmock.func.MWord.description";
  String DOC_WORD_C1 = "jmock.func.MWord.C1";
  String DOC_WORD_C2 = "jmock.func.MWord.C2";
  String DOC_WORD_C3 = "jmock.func.MWord.C3";

  String DOC_TITLE_DESC = "jmock.func.MTitle.description";
  String DOC_TITLE_C1 = "jmock.func.MTitle.C1";
  String DOC_TITLE_C2 = "jmock.func.MTitle.C2";
  String DOC_TITLE_C3 = "jmock.func.MTitle.C3";

  String DOC_TANGPOETRY_DESC = "jmock.func.MTangPoetry.description";
  String DOC_TANGPOETRY_C1 = "jmock.func.MTangPoetry.C1";
  String DOC_TANGPOETRY_C2 = "jmock.func.MTangPoetry.C2";
  String DOC_TANGPOETRY_C3 = "jmock.func.MTangPoetry.C3";

  String DOC_ADDRESS_DESC = "jmock.func.MAddress.description";
  String DOC_ADDRESS_C1 = "jmock.func.MAddress.C1";
  String DOC_ADDRESS_C2 = "jmock.func.MAddress.C2";
  String DOC_ADDRESS_C3 = "jmock.func.MAddress.C3";

  String DOC_COUNTRY_DESC = "jmock.func.MCountry.description";
  String DOC_COUNTRY_C1 = "jmock.func.MCountry.C1";
  String DOC_COUNTRY_C2 = "jmock.func.MCountry.C2";
  String DOC_COUNTRY_C3 = "jmock.func.MCountry.C3";

  String DOC_PROVINCE_DESC = "jmock.func.MProvince.description";
  String DOC_PROVINCE_C1 = "jmock.func.MProvince.C1";
  String DOC_PROVINCE_C2 = "jmock.func.MProvince.C2";
  String DOC_PROVINCE_C3 = "jmock.func.MProvince.C3";

  String DOC_CITY_DESC = "jmock.func.MCity.description";
  String DOC_CITY_C1 = "jmock.func.MCity.C1";
  String DOC_CITY_C2 = "jmock.func.MCity.C2";
  String DOC_CITY_C3 = "jmock.func.MCity.C3";

  String DOC_ZIP_DESC = "jmock.func.MZip.description";
  String DOC_ZIP_PARAMETER_DICT = "jmock.func.MZip.parameter.dict";
  String DOC_ZIP_C1 = "jmock.func.MZip.C1";
  String DOC_ZIP_C2 = "jmock.func.MZip.C2";
  String DOC_ZIP_C3 = "jmock.func.MZip.C3";

  String DOC_COORDINATES_DESC = "jmock.func.MCoordinates.description";
  String DOC_COORDINATES_PARAMETER_MIN_LNG = "jmock.func.MCoordinates.parameter.minLng";
  String DOC_COORDINATES_PARAMETER_MAX_LNG = "jmock.func.MCoordinates.parameter.maxLng";
  String DOC_COORDINATES_PARAMETER_MIN_LAT = "jmock.func.MCoordinates.parameter.minLat";
  String DOC_COORDINATES_PARAMETER_MAX_LAT = "jmock.func.MCoordinates.parameter.maxLat";
  String DOC_COORDINATES_PARAMETER_SCALE = "jmock.func.MCoordinates.parameter.scale";
  String DOC_COORDINATES_C1 = "jmock.func.MCoordinates.C1";
  String DOC_COORDINATES_C2 = "jmock.func.MCoordinates.C2";

  String DOC_LATITUDE_DESC = "jmock.func.MLatitude.description";
  String DOC_LATITUDE_C1 = "jmock.func.MLatitude.C1";
  String DOC_LATITUDE_C2 = "jmock.func.MLatitude.C2";

  String DOC_LONGITUDE_DESC = "jmock.func.MLongitude.description";
  String DOC_LONGITUDE_C1 = "jmock.func.MLongitude.C1";
  String DOC_LONGITUDE_C2 = "jmock.func.MLongitude.C2";

  String DOC_SHA_DESC = "jmock.func.MSha.description";
  String DOC_SHA_PARAMETER_VERSION = "jmock.func.MSha.parameter.version";
  String DOC_SHA_C1 = "jmock.func.MSha.C1";
  String DOC_SHA_C2 = "jmock.func.MSha.C2";

  String DOC_MD5_DESC = "jmock.func.MMd5.description";
  String DOC_MD5_PARAMETER_LENGTH = "jmock.func.MMd5.parameter.length";
  String DOC_MD5_C1 = "jmock.func.MMd5.C1";
  String DOC_MD5_C2 = "jmock.func.MMd5.C2";

  String DOC_COLOR_DESC = "jmock.func.MColor.description";
  String DOC_COLOR_PARAMETER_FORMAT  = "jmock.func.MColor.parameter.format";
  String DOC_COLOR_C1 = "jmock.func.MColor.C1";
  String DOC_COLOR_C2 = "jmock.func.MColor.C2";

  String DOC_EMOJI_DESC = "jmock.func.MEmoji.description";
  String DOC_EMOJI_C1 = "jmock.func.MEmoji.C1";
}
