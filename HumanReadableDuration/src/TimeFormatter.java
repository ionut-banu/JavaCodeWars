/**
 * Created by Ionut on Dec, 2019
 */
public class TimeFormatter {

    public static String formatDuration(int seconds) {
        System.out.println(seconds);
        if (seconds == 0)
            return "now";

        String out = "";
        int sec = seconds % 60;
        if (sec != 0) out += sec + " second" + (sec > 1 ? "s" : "");
        seconds /= 60;
        int min = seconds % 60;
        if (min != 0) out = addUnit(min, "minute", out);
        seconds /= 60;
        int hours = seconds % 24;
        if (hours != 0) out = addUnit(hours, "hour", out);
        seconds /= 24;
        int days = seconds % 365;
        if (days != 0) out = addUnit(days, "day", out);
        seconds /= 365;
        if (seconds != 0) out = addUnit(seconds, "year", out);
        return out;
    }

    public static String addUnit(int amount, String unit, String out) {
        if (out.length() > 0)
            if (!out.contains("and")) out = " and " + out;
            else out = ", " + out;
        return amount + " " + unit + (amount > 1 ? "s" : "") + out;
    }
}
