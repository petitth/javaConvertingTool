package edu.spring.utils;

public class Constants {

    private Constants() { }

    public static final String APP_TITLE                            = "Java Convert Tool";

    public static final String BUTTON_CONVERT                       = "convert";
    public static final String BUTTON_OPEN                          = "open";
    public static final String BUTTON_SAVE                          = "save";
    public static final String LABEL_ACTION                         = "action";

    public static final String LEFT_PANEL_TITLE                     = "Input";
    public static final String NORTH_PANEL_TITLE                    = "Actions";
    public static final String RIGHT_PANEL_TITLE                    = "Result";
    public static final String OPEN_DIALOG_MSG                      = "Open";
    public static final String OPEN_DIALOG_TITLE                    = "Specify a file to open";
    public static final String SAVE_DIALOG_TITLE                    = "Specify a path to save";
    public static final String STATUS_CURSOR_POS                    = "Line %d, Column %d";

    public static final String ACTION_DISPLAY_JSON                  = "display-json";
    public static final String ACTION_DISPLAY_XML                   = "display-xml";
    public static final String ACTION_JSON_TO_XML                   = "json-to-xml";
    public static final String ACTION_LOWER                         = "lower";
    public static final String ACTION_UPPER                         = "upper";
    public static final String ACTION_TRIM_FILE                     = "trim-file";
    public static final String ACTION_TRIM_JSON                     = "trim-json";
    public static final String ACTION_TRIM_XML                      = "trim-xml";
    public static final String ACTION_XML_TO_JSON                   = "xml-to-json";
    public static final String ACTION_CSV_TO_JSON                   = "csv-to-json";
    public static final String ACTION_UNDEFINED                     = "";

    public static final String ERROR_CONVERT_FILE                   = "cannot process file";
    public static final String ERROR_CONVERT_JSON                   = "cannot process json";
    public static final String ERROR_CONVERT_XML                    = "cannot process xml";
    public static final String ERROR_FILE                           = "an error occurred while processing file : \n\t%s";
    public static final String ERROR_JSON                           = "an error occurred while processing json : \n\t%s";
    public static final String ERROR_XML                            = "an error occurred while processing xml : \n\t%s";
    public static final String ERROR_CSV                            = "an error occurred while processing csv : \n\t%s";
}
