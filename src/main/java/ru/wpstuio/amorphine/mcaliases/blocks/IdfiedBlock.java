package ru.wpstuio.amorphine.mcaliases.blocks;

/**
 * Created by amorphine on 19.11.16.
 */
public class IdfiedBlock extends Block {

    byte id;
    byte add;
    short full_id;

    public IdfiedBlock(int x, int z, int y, byte id, byte add) {
        super(x, z, y);
        this.add = add;
        this.id = id;

        this.full_id = (short) (id + (add << 8));
    }
}
