package files;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * BlocksDefinitionReader - the class responds to read and solve the block definition file.
 */
public class BlocksDefinitionReader {
    /**
     * read the block definition file and return BlockFromSymbolsFactory.
     *
     * @param reader is the reader of the file
     * @return BlocksFromSymbolsFactory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        Map<String, Integer> spacerWidths = new TreeMap<>();
        Map<String, BlockCreator> blockCreators = new TreeMap<>();
        Map<String, String> defaultsValues = new TreeMap<>();
        BufferedReader is = null;
        try {
            is = new BufferedReader(reader);
            String data;
            while ((data = is.readLine()) != null) {
                if (data.startsWith("default")) {
                    putDefaultsIntoMap(defaultsValues, data);

                } else if (data.startsWith("bdef")) {
                    data = data.replaceAll("bdef ", "");
                    String[] strings = data.split(" ");
                    blockCreators.put(strings[0].split(":")[1], createBlockCreator(strings, defaultsValues));

                } else if (data.startsWith("sdef")) {
                    data = data.replaceAll("sdef ", "");
                    String[] strings = data.split(" ");
                    spacerWidths.put(strings[0].split(":")[1], Integer.parseInt(strings[1].split(":")[1]));
                }
            }
            return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * create BlockCreator according to the block definition.
     *
     * @param s        is a line from the block definition, start with "bdef".
     * @param defaults are defaults values presented as a map,  according to the block definition file.
     * @return BlockCreator.
     */
    private static BlockCreator createBlockCreator(String[] s, Map<String, String> defaults) {
        Map<String, String> stringMap = new TreeMap<>();
        ColorsParser colorsParser = new ColorsParser();
        Color color = null;
        Color stroke = null;
        Image img = null;
        for (String s1 : s) {
            stringMap.put(s1.split(":")[0], s1.split(":")[1]);
        }
        if (defaults.containsKey("stroke")) {
            stroke = colorsParser.colorFromString(defaults.get("stroke"));
        } else if (stringMap.containsKey("stroke")) {
            stroke = colorsParser.colorFromString(defaults.get("stroke"));
        }
        int height = defaults.containsKey("height")
                ? Integer.parseInt(defaults.get("height")) : Integer.parseInt(stringMap.get("height"));
        int width = defaults.containsKey("width")
                ? Integer.parseInt(defaults.get("width")) : Integer.parseInt(stringMap.get("width"));

        String fill = defaults.containsKey("fill")
                ? defaults.get("fill") : stringMap.get("fill");
        if (!fill.contains(".")) {
            color = colorsParser.colorFromString(fill);
        } else {

            try {
                String data = "src\\resources\\" + fill.substring(fill.lastIndexOf("(") + 1, fill.indexOf(")"));
                img = ImageIO.read(new File(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (color != null && stroke != null) {
            return new BlockCreatorFromText(width, height, color, stroke);
        }
        if (color != null) {
            return new BlockCreatorFromText(width, height, color);
        }
        if (stroke == null) {
            return new BlockCreatorFromText(width, height, img);
        }
        return new BlockCreatorFromText(width, height, img, stroke);
    }

    /**
     * put the defaults values into a map, according to the block defaults file.
     *
     * @param map  is the map
     * @param data is the string of the defaults values.
     */
    private static void putDefaultsIntoMap(Map<String, String> map, String data) {
        String temp = data.replaceAll("default ", "");
        String[] defaults = temp.split(" ");
        for (String s : defaults) {
            map.put(s.split(":")[0], s.split(":")[1]);
        }
    }
}
