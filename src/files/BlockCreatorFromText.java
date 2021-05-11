package files;

import sprite.Block;

import java.awt.Color;
import java.awt.Image;

/**
 * create Blocks from files and texts.
 */
public class BlockCreatorFromText implements BlockCreator {

    private int width;
    private int height;
    private Color color;
    private Color stroke;
    private boolean isImage;
    private boolean hasStroke;
    private Image image;

    /**
     * constructor with width and height and color and stroke.
     *
     * @param width  is the width of the block
     * @param height is the height of the block.
     * @param color  is the color.
     * @param stroke is the stroke.
     */
    public BlockCreatorFromText(int width, int height, Color color, Color stroke) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.stroke = stroke;
        this.isImage = false; // the block fill by color and not by image.
        this.hasStroke = true;
    }

    /**
     * constructor with width and height and image and stroke.
     *
     * @param width  is the width of the block
     * @param height is the height of the block.
     * @param image  is an image.
     * @param stroke is the stroke.
     */
    public BlockCreatorFromText(int width, int height, Image image, Color stroke) {
        this.width = width;
        this.height = height;
        this.stroke = stroke;
        this.image = image;
        this.isImage = true;
        this.hasStroke = true;
    }

    /**
     * constructor with width and height and color.
     *
     * @param width  is the width of the block
     * @param height is the height of the block.
     * @param color  is the color.
     */
    public BlockCreatorFromText(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.isImage = false;
        this.hasStroke = false;
    }

    /**
     * constructor with width and height and image.
     *
     * @param width  is the width of the block
     * @param height is the height of the block.
     * @param image  is an image.
     */
    public BlockCreatorFromText(int width, int height, Image image) {
        this.width = width;
        this.height = height;
        this.isImage = true;
        this.hasStroke = false;
        this.image = image;
    }

    @Override
    public Block create(int xpos, int ypos) {
        // create blocks according the possibility combinations.
        if (!isImage && !hasStroke) {
            return new Block(xpos, ypos, width, height, color, stroke);
        }
        if (!isImage) {
            return new Block(xpos, ypos, width, height, color, stroke);
        }
        if (!hasStroke) {
            return new Block(xpos, ypos, width, height, image);
        }
        return new Block(xpos, ypos, width, height, stroke, image);
    }

    /**
     * get the block width.
     *
     * @return the block width.
     */
    public int getBlockWidth() {
        return this.width;
    }
}
