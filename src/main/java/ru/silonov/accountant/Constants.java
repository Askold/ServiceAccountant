package ru.silonov.accountant;

/**
 * Class that conserves all HardCode variables
 *
 * @author Silonov Kirill
 */
public class Constants {
    //---------------------------------Hibernate Util
    public static final String PROPERTIES_PATH = "properties";
    public static final String DEFAULT_CONFIG_PATH ="./src/main/resources/environment.properties";
    public static final String DEFAULT_HBN_CFG = "def.hbn_cnf";
    public static final String DEFAULT_HBN_CONFIG_PATH = "src/main/resources/hibernate.cfg.xml";

    //---------------------------------CRUD
    public static final String RECORDS_ADDED = "All records were added";
    public static final String RECORDS_SELECTED = "Records were selected";
    public static final String FOUND = " found";
    public static final String NOT_FOUND = " not found";
    public static final String NOT_UPDATED =" wasn't updated";
    public static final String UPDATED =" updated";
    public static final String DELETED =" deleted";
    public static final String NOT_DELETED =" not deleted";
    public static final String ADDED =" was added";
    public static final String NOT_ADDED =" not added";


}
