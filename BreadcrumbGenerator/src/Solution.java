/**
 * Created by Ionut on Dec, 2019
 */
public class Solution {

    public static String generate_bc(String url, String separator) {
        String out = "<a href=\"/\">HOME</a>" + separator;

        url = url.substring(url.indexOf('/') + 1);
        String last = url.substring(url.lastIndexOf("/"));
        if (last.contains("index.htm")) url = url.substring(0, url.indexOf(last));

        while (!url.isEmpty()) {
            if (url.contains("/")) {
                String path = url.substring(0, url.indexOf('/'));
                out += "<a href=\"" + "/" + path + "/\">" + path.toUpperCase() + "</a>" + separator;
                url = url.substring(url.indexOf('/') + 1);
            } else {
                String path;
                if (url.contains(".")) path = url.substring(0, url.indexOf("."));
                else if (url.contains("?")) path = url.substring(0, url.indexOf("?"));
                else if (url.contains("#")) path = url.substring(0, url.indexOf("#"));
                else path = url;
                out += "<span class=\"active\">" + path.toUpperCase() + "</span>";
                url = "";
            }

        }

        return out;
    }

    static String getAcronym(String in) {
        String out = "";

        return out;
    }

}
