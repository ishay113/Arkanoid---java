package files;

import java.awt.Color;

/**
 * color parser - parse color definition and return the specified color.
 */
public class ColorsParser {

    /**
     * parse color definition and return the specified color.
     *
     * @param s is the string - the color definition.
     * @return color.
     */
    public java.awt.Color colorFromString(String s) {
        String data = s.substring(s.lastIndexOf("(") + 1, s.indexOf(")"));
        // if the color is one of the regular colors
        if (!data.contains(",")) {
            return getColorFromString(data);
        }
        // else
        String[] strings = data.split(",");
        return new Color(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));


    }

    /**
     * get color by the name of the color.
     *
     * @param s is the string name of the color.
     * @return color.
     */
    private Color getColorFromString(String s) {
        switch (s) {

            case ("black"):
                return Color.black;
            case ("yellow"):
                return Color.yellow;
            case ("red"):
                return Color.red;
            case ("blue"):
                return Color.blue;
            case ("pink"):
                return Color.pink;
            case ("orange"):
                return Color.ORANGE;
            case ("white"):
                return Color.white;
            case ("magenta"):
                return Color.magenta;
            case ("gray"):
                return Color.gray;
            case ("cyan"):
                return Color.cyan;
            case ("lightGray"):
                return Color.lightGray;
            case ("green"):
                return Color.green;
            default:
                return null;
        }
    }
}
