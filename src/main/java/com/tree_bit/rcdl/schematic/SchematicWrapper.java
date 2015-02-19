package com.tree_bit.rcdl.schematic;

import static com.google.common.base.Preconditions.checkNotNull;

import org.jnbt.ByteArrayTag;
import org.jnbt.CompoundTag;
import org.jnbt.NBTInputStream;
import org.jnbt.NBTOutputStream;
import org.jnbt.ShortTag;
import org.jnbt.Tag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper for Minecraft NBT files. Used for MCEdit schematics.
 *
 * @see<a 
 *        href="http://minecraft.gamepedia.com/Schematic">http://minecraft.gamepedia
 *        .com/Schematic</a>
 */
public class SchematicWrapper {

    /**
     * Root Tag
     */
    private CompoundTag root;

    /**
     * Creates a new wrapper for a schematic file.
     *
     * @param path <b>String</b> path to file
     * @throws FileNotFoundException
     * @throws IOException
     */
    // Can't be null (checked by Guava)
    @SuppressWarnings("null")
    public SchematicWrapper(final String path) throws FileNotFoundException, IOException {
        if (path.equals("")) {
            throw new IllegalArgumentException("Path is empty");
        }

        try (final NBTInputStream inputStream = new NBTInputStream(new FileInputStream(path))) {
            this.root = checkNotNull((CompoundTag) inputStream.readTag());
        }

    }

    /**
     * Creates a new wrapper for an empty schematic file.
     */
    public SchematicWrapper() {
        this.root = new CompoundTag("Schematic", new HashMap<String, Tag>());
    }

    /**
     * Returns the height of the schematic.
     *
     * @return <b>short</b> height
     */
    public short readHeight() {
        return ((ShortTag) this.read(ESchematicFields.HEIGHT)).getValue();
    }

    /**
     * Returns the width of the schematic.
     *
     * @return <b>short</b> width
     */
    public short readWidth() {
        return ((ShortTag) this.read(ESchematicFields.WIDTH)).getValue();
    }

    /**
     * Returns the length of the schematic.
     *
     * @return <b>short</b> length
     */
    public short readLength() {
        return ((ShortTag) this.read(ESchematicFields.LENGTH)).getValue();
    }

    /**
     * Returns a byte array containing all block ids of the schematic.
     * <p>
     * Each block id uses 8 bits.
     * </p>
     *
     * @return <b>byte[]</b> blocks
     */
    // Can't be null (checked by Guava)
    @SuppressWarnings("null")
    public byte[] readBlocks() {
        return checkNotNull(((ByteArrayTag) this.read(ESchematicFields.BLOCKS)).getValue());
    }

    /**
     * Returns a byte array containing all data values for the blocks of the
     * schematic.
     * <p>
     * Each data value uses the lower 4 bits of a byte.
     * </p>
     *
     * @return <b>byte[]</b> blocks
     */
    // Can't be null (checked by Guava)
    @SuppressWarnings("null")
    public byte[] readData() {
        return checkNotNull(((ByteArrayTag) this.read(ESchematicFields.DATA)).getValue());
    }

    /**
     * Returns a Tag object matching a field of the schematic.
     *
     * @param field <b>ESchematicFields</b> schematic field
     * @return <b>Tag</b> tag
     */
    public Tag read(final ESchematicFields field) {
        return getTag(field.getKey(), this.root);
    }

    /**
     * Returns the child of a CompoundTag matching a key.
     *
     * @param key <b>String</b> key of the child
     * @param parentTag <b>CompoundTag</b> parent compound tag
     * @return <b>Tag</b> child tag
     */
    // Can't be null (checked by Guava)
    @SuppressWarnings("null")
    private static Tag getTag(final String key, final CompoundTag parentTag) {
        return checkNotNull(parentTag.getValue().get(key));
    }

    /**
     * Copy the values of a compound tag to a new map and add a new tag. An
     * existing tag with the same name is replaced.
     *
     * @param t <b>Tag</b> new tag
     * @param parentTag <b>CompoundTag</b> parent tag
     * @return <b>Map&lt;String, Tag&gt;</b> map with the old values and the new
     *         one
     */
    // Can't be null (checked by Guava)
    @SuppressWarnings("null")
    private static Map<String, Tag> addTag(final Tag t, final CompoundTag parentTag) {
        final Map<String, Tag> map = new HashMap<>();

        // Copy old Map
        for (final Tag old : parentTag.getValue().values()) {
            map.put(checkNotNull(old.getName()), old);
        }

        // Add t to map (replace possibly existing old value)
        map.put(checkNotNull(t.getName()), t);

        return map;
    }

    /**
     * Adds a tag to the root compound tag values. Replaces possibly existing
     * value with same name/key.
     *
     * @param t <b>Tag</b> tag
     */
    private void addTagToRoot(final Tag t) {
        this.root = new CompoundTag(this.root.getName(), addTag(t, this.root));
    }

    /**
     * Adds a tag for the height of the schematic to the root compound tag.
     *
     * @param value <b>short</b> height
     */
    public void writeHeight(final short value) {
        final ShortTag t = new ShortTag(ESchematicFields.HEIGHT.getKey(), value);
        this.addTagToRoot(t);
    }

    /**
     * Adds a tag for the width of the schematic to the root compound tag.
     *
     * @param value <b>short</b> width
     */
    public void writeWidth(final short value) {
        final ShortTag t = new ShortTag(ESchematicFields.WIDTH.getKey(), value);
        this.addTagToRoot(t);
    }

    /**
     * Adds a tag for the length of the schematic to the root compound tag.
     *
     * @param value <b>short</b> length
     */
    public void writeLength(final short value) {
        final ShortTag t = new ShortTag(ESchematicFields.LENGTH.getKey(), value);
        this.addTagToRoot(t);
    }

    /**
     * Adds a tag for the blocks of the schematic to the root compound tag.
     *
     * @param value <b>byte[]</b> blocks
     */
    public void writeBlocks(final byte[] value) {
        final ByteArrayTag t = new ByteArrayTag(ESchematicFields.BLOCKS.getKey(), value);
        this.addTagToRoot(t);
    }

    /**
     * Adds a tag for the data values (for blocks) of the schematic to the root
     * compound tag.
     *
     * @param value <b>byte[]</b> data values
     */
    public void writeData(final byte[] value) {
        final ByteArrayTag t = new ByteArrayTag(ESchematicFields.DATA.getKey(), value);
        this.addTagToRoot(t);
    }

    /**
     * Writes the current root compound tag (and value tags) to the given file.
     *
     * <p>
     * If the file doesn't exist it will be created.
     * </p>
     *
     * @param path <b>String</b> path to file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void saveChangesToFile(final String path) throws FileNotFoundException, IOException {
        if (path.equals("")) {
            throw new IllegalArgumentException("Path is empty");
        }

        final File f = new File(path);

        if (!f.exists()) {
            if (f.getParentFile() != null) {
                f.getParentFile().mkdirs();
            }

            f.createNewFile();
        }

        try (final NBTOutputStream out = new NBTOutputStream(new FileOutputStream(f))) {
            out.writeTag(this.root);
        }
    }

    @Override
    // Can't be null (checked by Guava)
    @SuppressWarnings("null")
    public String toString() {
        return checkNotNull(this.root.toString());
    }
}
