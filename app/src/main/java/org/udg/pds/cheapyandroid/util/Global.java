package org.udg.pds.cheapyandroid.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Global {

    public static final String tz = TimeZone.getDefault().getDisplayName();
    public static final SimpleDateFormat DATE_ONLY_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat TIME_ONLY_FORMAT = new SimpleDateFormat("HH::mm");
    public static final SimpleDateFormat TIME_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH::mm");
    public static final SimpleDateFormat FULL_TIME_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    // Resquest codes for startActivityForResult
    public static final String BASE_URL = "http://private-amnesiac-5389b3-pdsrest.apiary-proxy.com/";
    public static final String PREFS_NAME = "MisPreferencias";

}
