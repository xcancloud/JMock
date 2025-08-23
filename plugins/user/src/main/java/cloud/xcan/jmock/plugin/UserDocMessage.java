package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class UserDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 8xx.
   */
  public static final String DOC_CATEGORY_USER = "jmock.func.category.user";

  public static final String DATA_FIRSTNAME = "jmock.func.data.firstname";
  public static final String DATA_LASTNAME = "jmock.func.data.lastname";
  public static final String DATA_GENDER = "jmock.func.data.gender";
  public static final String DATA_EDUCATION = "jmock.func.data.education";
  public static final String DATA_EMAIL = "jmock.func.data.email";
  public static final String DATA_LANDLINE = "jmock.func.data.landLine";

  public static final String DOC_FIRSTNAME_DESC = "jmock.func.MFirstname.description";
  public static final String DOC_FIRSTNAME_PARAMETER_DICT = "jmock.func.MFirstname.parameter.dict";
  public static final String DOC_FIRSTNAME_C1 = "jmock.func.MFirstname.C1";
  public static final String DOC_FIRSTNAME_C2 = "jmock.func.MFirstname.C2";
  public static final String DOC_FIRSTNAME_C3 = "jmock.func.MFirstname.C3";

  public static final String DOC_LASTNAME_DESC = "jmock.func.MLastname.description";
  public static final String DOC_LASTNAME_PARAMETER_DICT = "jmock.func.MLastname.parameter.dict";
  public static final String DOC_LASTNAME_C1 = "jmock.func.MLastname.C1";
  public static final String DOC_LASTNAME_C2 = "jmock.func.MLastname.C2";
  public static final String DOC_LASTNAME_C3 = "jmock.func.MLastname.C3";

  public static final String DOC_NAME_DESC = "jmock.func.MName.description";
  public static final String DOC_NAME_PARAMETER_DICT = "jmock.func.MName.parameter.dict";
  public static final String DOC_NAME_C1 = "jmock.func.MName.C1";
  public static final String DOC_NAME_C2 = "jmock.func.MName.C2";
  public static final String DOC_NAME_C3 = "jmock.func.MName.C3";

  public static final String DOC_GENDER_DESC = "jmock.func.MGender.description";
  public static final String DOC_GENDER_PARAMETER_DICT = "jmock.func.MGender.parameter.dict";
  public static final String DOC_GENDER_C1 = "jmock.func.MGender.C1";
  public static final String DOC_GENDER_C2 = "jmock.func.MGender.C2";
  public static final String DOC_GENDER_C3 = "jmock.func.MGender.C3";

  public static final String DOC_AGE_DESC = "jmock.func.MAge.description";
  public static final String DOC_AGE_PARAMETER_MIN = "jmock.func.MAge.parameter.min";
  public static final String DOC_AGE_PARAMETER_MAX = "jmock.func.MAge.parameter.max";
  public static final String DOC_AGE_C1 = "jmock.func.MAge.C1";
  public static final String DOC_AGE_C2 = "jmock.func.MAge.C2";

  public static final String DOC_MOBILE_DESC = "jmock.func.MMobile.description";
  public static final String DOC_MOBILE_C1 = "jmock.func.MMobile.C1";
  public static final String DOC_MOBILE_C2 = "jmock.func.MMobile.C2";

  public static final String DOC_LANDINE_DESC = "jmock.func.MLandline.description";
  public static final String DOC_LANDINE_C1 = "jmock.func.MLandline.C1";
  public static final String DOC_LANDINE_C2 = "jmock.func.MLandline.C2";

  public static final String DOC_EMAIL_DESC = "jmock.func.MEmail.description";
  public static final String DOC_EMAIL_PARAMETER_MIN = "jmock.func.MEmail.parameter.min";
  public static final String DOC_EMAIL_PARAMETER_MAX = "jmock.func.MEmail.parameter.max";
  public static final String DOC_EMAIL_PARAMETER_SUFFIX = "jmock.func.MEmail.parameter.suffix";
  public static final String DOC_EMAIL_C1 = "jmock.func.MEmail.C1";
  public static final String DOC_EMAIL_C2 = "jmock.func.MEmail.C2";
  public static final String DOC_EMAIL_C3 = "jmock.func.MEmail.C3";
  public static final String DOC_EMAIL_C4 = "jmock.func.MEmail.C4";

  public static final String DOC_PASSWORD_DESC = "jmock.func.MPassword.description";
  public static final String DOC_PASSWORD_PARAMETER_MIN = "jmock.func.MPassword.parameter.min";
  public static final String DOC_PASSWORD_PARAMETER_MAX = "jmock.func.MPassword.parameter.max";
  public static final String DOC_PASSWORD_PARAMETER_ALLOW_UPPERCASE = "jmock.func.MPassword.parameter.allowUpperCase";
  public static final String DOC_PASSWORD_PARAMETER_ALLOW_LOWERCASE = "jmock.func.MPassword.parameter.allowLowerCase";
  public static final String DOC_PASSWORD_PARAMETER_ALLOW_DIGITS = "jmock.func.MPassword.parameter.allowDigits";
  public static final String DOC_PASSWORD_PARAMETER_ALLOW_SPECIALCHAR = "jmock.func.MPassword.parameter.allowSpecialChar";
  public static final String DOC_PASSWORD_C1 = "jmock.func.MPassword.C1";
  public static final String DOC_PASSWORD_C2 = "jmock.func.MPassword.C2";
  public static final String DOC_PASSWORD_C3 = "jmock.func.MPassword.C3";
  public static final String DOC_PASSWORD_C4 = "jmock.func.MPassword.C4";

  public static final String DOC_EDUCATION_DESC = "jmock.func.MEducation.description";
  public static final String DOC_EDUCATION_PARAMETER_DICT = "jmock.func.MEducation.parameter.dict";
  public static final String DOC_EDUCATION_C1 = "jmock.func.MEducation.C1";
  public static final String DOC_EDUCATION_C2 = "jmock.func.MEducation.C2";
  public static final String DOC_EDUCATION_C3 = "jmock.func.MEducation.C3";

  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-user-plugin-messages");
  }
}
