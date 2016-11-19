package ru.wpstuio.amorphine.mcaliases.worldessenses;

import com.flowpowered.nbt.CompoundMap;

/**
 * Created by amorphine on 19.11.16.
 */
public class Selection {

    private CompoundMap map;
    private byte[] blocks;

    public Selection(CompoundMap map) {
        this.map = map;
    }
}
