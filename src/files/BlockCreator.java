package files;

import sprite.Block;

/**
 * BlockCreator - creates blocks.
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     *
     * @param xpos is the x position of the block
     * @param ypos is the y position of the block
     * @return block.
     */
    Block create(int xpos, int ypos);
}
