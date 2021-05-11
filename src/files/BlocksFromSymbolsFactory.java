package files;

import sprite.Block;

import java.util.Map;

/**
 * BlocksFromSymbolsFactory - Factory of blocks, according their symbols and definitions.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * constructor with spacerWidth map and blockCreators map.
     *
     * @param spacerWidths  is a map of the spacer widths values.
     * @param blockCreators is a map of the block creators values.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.blockCreators = blockCreators;
        this.spacerWidths = spacerWidths;
    }


    /**
     * returns true if 's' is a valid space symbol.
     *
     * @param s is the symbol.
     * @return true if 's' is a valid space symbol, false otherwise.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }


    /**
     * returns true if 's' is a valid block symbol.
     *
     * @param s is the block symbol.
     * @return true if 's' is a valid block symbol, false otherwise.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }


    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     *
     * @param s    is the symbol.
     * @param xpos is the x position of the block.
     * @param ypos is the y position of the block.
     * @return a block according to the definitions associated with symbol s.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);

    }


    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s is the symbol.
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}
