package id.my.avmmartin.myspending;

import android.app.Application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

final public class MySpending extends Application {
    public final static String PACKAGE_NAME = "id.my.avmmartin.myspending";

    public final static String DB_NAME = "MySpending";
    public final static String FORMAT_DATE = "yyyy-MM-dd";

    public final static String INTENT_SPENDING_ID = PACKAGE_NAME + ".SPENDING_ID";

    public final static int NEW_SPENDING_ID = -1;

    public static String get_today_date() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE, Locale.US);
        return simpleDateFormat.format(new Date());
    }

    public static boolean is_valid_number(String s) {
        for (int idx = 0; idx < s.length(); idx++) {
            if (!Character.isDigit(s.charAt(idx))) {
                return false;
            }
        }
        return true;
    }
}
