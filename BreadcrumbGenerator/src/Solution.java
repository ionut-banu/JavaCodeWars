/**
 * Created by Ionut on Dec, 2019
 */
public class Solution {

    public static String generate_bc(String url, String separator) {
        System.out.println(url);
        String out = "<a href=\"/\">HOME</a>" + separator;

        if (url.contains("https://")) {
            url = url.substring(url.indexOf("https://") + 8);
            url = url.substring(url.indexOf('/') + 1);
        } else if (url.contains("http://")) {
            url = url.substring(url.indexOf("http://") + 7);
            url = url.substring(url.indexOf('/') + 1);
        } else url = url.substring(url.indexOf('/') + 1);

        if (url.lastIndexOf("/") > -1) {
            String last = url.substring(url.lastIndexOf("/"));
            if (last.contains("index.htm")) url = url.substring(0, url.indexOf(last));
            if (last.contains("/#")) url = url.substring(0, url.indexOf("/#"));
            if (last.contains("/?")) url = url.substring(0, url.indexOf("/?"));
        }
        if (url.length() > 0) {
            if (url.charAt(url.length() - 1) == '/')
                url = url.substring(0, url.length() - 1);
            if (!url.contains("/") && url.contains(".")) return "<span class=\"active\">HOME</span>";
        } else {
            return "<span class=\"active\">HOME</span>";
        }

        /*String urlRegex = "(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?";
        Pattern urlPattern = Pattern.compile(urlRegex);
        Matcher m = urlPattern.matcher(url);
        while (m.find()) {
            String s = m.group(1);
            System.out.println(s);
        }*/

        while (!url.isEmpty()) {
            if (url.contains("/")) {
                String path = url.substring(0, url.indexOf('/'));
                String s = "";
                if (path.length() < 31) {
                    s = path.replace("-" , " ").toUpperCase();
                } else {
                    s = getAcronym(path);
                }
                out += "<a href=\"" + "/" + path + "/\">" + s + "</a>" + separator;
                url = url.substring(url.indexOf('/') + 1);
            } else {
                String path;
                if (url.contains(".")) path = url.substring(0, url.indexOf("."));
                else if (url.contains("?")) path = url.substring(0, url.indexOf("?"));
                else if (url.contains("#")) path = url.substring(0, url.indexOf("#"));
                else path = url;
                String s = "";
                if (path.length() < 31) {
                    s = path.replace("-" , " ").toUpperCase();
                } else {
                    s = getAcronym(path);
                }
                out += "<span class=\"active\">" + s + "</span>";
                url = "";
            }

        }

        return out;
    }

    static String getAcronym(String in) {
        String out = "";
        while (!in.isEmpty()) {
            if (in.contains("-")) {
                String t = in.substring(0, in.indexOf('-'));
                if (t.length() > 2 && !t.equals("and") && !t.equals("for") && !t.equals("the")
                        && !t.equals("from") && !t.equals("with")) {
                    out += in.substring(0, 1).toUpperCase();
                }
                in = in.substring(in.indexOf('-') + 1);
            } else {
                String t = in;
                if (t.length() > 2 && !t.equals("and") && !t.equals("for") && !t.equals("the")
                        && !t.equals("from") && !t.equals("with")) {
                    out += in.substring(0, 1).toUpperCase();
                }
                in = "";
            }
        }

        return out;
    }

}
