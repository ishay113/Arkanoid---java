package files;

import levels.LevelInformation;
import levels.LevelsFromText;
import sprite.BackGround;
import sprite.Block;
import sprite.Velocity;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.Integer.parseInt;

/**
 * LevelSpecificationReader - is the level reader.
 */
public class LevelSpecificationReader {

    /**
     * constructor.
     */
    public LevelSpecificationReader() {
    }

    /**
     * read the level definition file and parse the text and return list of level information.
     *
     * @param reader is the reader file.
     * @return List of level information.
     * @throws IOException throws IOException if can't read the file.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) throws IOException {

        List<LevelInformation> levelsList = new ArrayList<>();
        List<List<String>> listList = splitLevels(reader);
        for (List<String> l : listList) {
            levelsList.add(createLevelsFromList(l));
        }

        reader.close();

        return levelsList;
    }

    /**
     * create levels from the text - list of strings.
     *
     * @param list is the list of strings, each string is a line in the file (one list is a level).
     * @return LevelInformation.
     * @throws IOException throws IOException if can't read the file.
     */
    private LevelInformation createLevelsFromList(List<String> list) throws IOException {
        Map<String, String> map = new TreeMap<>();

        for (String s : list) {
            if (s.contains(":")) {
                map.put(s.split(":")[0], s.split(":")[1]);
            }
        }
        String levelName = map.get("level_name");
        int paddleSpeed = parseInt(map.get("paddle_speed"));
        int paddleWidth = parseInt(map.get("paddle_width"));
        int numOfBlocks = parseInt(map.get("num_blocks"));
        List<Velocity> velocities = velocitiesFromString(map.get("ball_velocities"));
        File file = new File("src\\resources\\" + map.get("block_definitions"));
        boolean t = file.exists();
        BlocksFromSymbolsFactory bfs = BlocksDefinitionReader.fromReader(
                new InputStreamReader(new FileInputStream(file)));
        List<Block> blockList = createBlocksList(map, bfs, list);
        BackGround backGround = backgroundFromString(map);
        return new LevelsFromText(paddleSpeed, paddleWidth, levelName,
                backGround, numOfBlocks, velocities, blockList);
    }

    /**
     * create background from the text.
     *
     * @param map is the map of the level definitions values.
     * @return Background.
     * @throws IOException throws IOException if can't read the file.
     */
    private BackGround backgroundFromString(Map<String, String> map) throws IOException {
        String background = map.get("background");
        ColorsParser colorsParser = new ColorsParser();
        if (background.startsWith("color")) {
            Color color = colorsParser.colorFromString(background);
            return new BackGround(color);
        }
        Image img = null;
        String file = background.substring(background.lastIndexOf("(") + 1, background.lastIndexOf(")"));

        img = ImageIO.read(new File("src\\resources\\" + file));
        return new BackGround(img);
    }

    /**
     * split the levels from the text in the file.
     *
     * @param reader is the reader file.
     * @return list of StringList, every list is a level.
     */
    private List<List<String>> splitLevels(java.io.Reader reader) {
        BufferedReader is = null;
        List<List<String>> levelsList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        levelsList.add(list);
        try {
            is = new BufferedReader(reader);

            String string;
            int i = 0;
            while ((string = is.readLine()) != null) {
                if (!string.equals("")) {
                    levelsList.get(i).add(string);
                }
                if (string.trim().equals("END_LEVEL")) {
                    levelsList.add(new ArrayList<>());
                    i++;
                }
            }
            levelsList.remove(levelsList.size() - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levelsList;
    }

    /**
     * velocities from strings.
     *
     * @param string is the string
     * @return list of velocities.
     */
    private List<Velocity> velocitiesFromString(String string) {
        List<Velocity> velocities = new ArrayList<>();
        String[] pair = string.split(" ");
        for (String s : pair) {
            double angle = parseInt(s.substring(0, s.indexOf(",")));
            int speed = parseInt(s.substring(s.indexOf(",") + 1));
            velocities.add(Velocity.fromAngleAndSpeed(angle, speed));
        }
        return velocities;
    }

    /**
     * create blocks list from strings.
     *
     * @param map  is the map of the definitions values.
     * @param bfs  is the BlocksFromSymbolsFactory.
     * @param list is the list of string.
     * @return List of blocks.
     */
    private List<Block> createBlocksList(Map<String, String> map, BlocksFromSymbolsFactory bfs, List<String> list) {
        List<Block> blockList = new ArrayList<>();
        int rowHeight = parseInt(map.get("row_height"));
        int startX = parseInt(map.get("blocks_start_x"));
        int startY = parseInt(map.get("blocks_start_y"));
        int i = 0;
        int start = list.indexOf("START_BLOCKS");
        int end = list.indexOf("END_BLOCKS");
        // take only the part of the blocks layout.
        List<String> blocksListString = list.subList(start + 1, end);
        // create the block using for in for(i = is the rows, j = the columns)
        for (String s : blocksListString) {
            int j = 0;
            if (!bfs.isSpaceSymbol(s)) {
                // parse the line for every char and if the this is a block symbol create a block
                for (int t = 0; t < s.length(); t++) {
                    String char1 = Character.toString(s.charAt(t));
                    if (bfs.isSpaceSymbol(char1)) {
                        j += bfs.getSpaceWidth(char1);
                    } else if (bfs.isBlockSymbol(char1)) {
                        Block block = bfs.getBlock(char1, startX + j, startY + i);
                        blockList.add(block);
                        j += (int) block.getBlockWidth();
                    }
                }
            }
            i += rowHeight;

        }
        return blockList;
    }
}

