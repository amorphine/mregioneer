package ru.wpstuio.amorphine.utils;

/**
 * Created by amorphine on 20.11.16.
 */
public class Formulae {

    public static int mod(int a, int b) {
        return ((a % b) + b) % b;
    }
    // Block Set

    public static int regionFromBlock(int block) {
        return ((block >> 4) >> 5);
    }

    public static int chunkFromBlock (int block) {
        return (block >> 4);
    }

    public static int localChunkFromBlock (int block) {
        return mod(block >> 4, 32);
    }

    public static int localBlockFromBlock(int block) {
        return mod(block, 16);
    }


    // Chunk Set
    public static int regionFromChunk(int chunk) {
        return (chunk >> 5);
    }

    public static int[] blockFromChunk(int chunk) {
        int start = chunk * 16;
        int end = (chunk + 1) * 16 - 1;

        int[] result = {start, end};

        return result;
    }

    public static int blockFromChunk(int chunk, int localBlock) {
        return (chunk * 16 + localBlock);
    }

    public int localChunkFromChunk (int chunk)
    {
        return mod(chunk, 32);
    }

    private String localBlockFromChunk (int chunk)
    {
        return "(0 to 15)";
    }

    // Region Set
    private int[] chunkFromRegion (int region)
    {
        int start = region * 32;
        int end = (region + 1) * 32 - 1;

        int[] result = {start, end};

        return result;
    }

    private int[] blockFromRegion (int region)
    {
        int start = region * 32 * 16;
        int end = (region + 1) * 32 * 16 - 1;

        int[] result = {start, end};

        return result;
    }

    private int chunkFromLocalChunk (int region, int localChunk)
    {
        return (region * 32 + localChunk);
    }

    private int chunkFromLocalBlock (int region, int localChunk, int localBlock)
    {
        return (region * 32 + localChunk);
    }

    private int blockFromLocalBlock (int region, int localChunk, int localBlock)
    {
        return (region * 32 * 16 + localChunk * 16 + localBlock);
    }

    /*
    private string LocalChunkFromRegion (int region)
    {
        return "(0 to 31)";
    }

    private string LocalBlockFromRegion (int region)
    {
        return "(0 to 15)";
    }

    // Local Chunk Set

    private string RegionFromLocalChunk (int localChunk)
    {
        return "(ANY)";
    }

    private string ChunkFromLocalChunk (int localChunk)
    {
        return "(ANY)";
    }



    private string BlockFromLocalChunk (int localChunk)
    {
        return "(ANY)";
    }

    private string BlockFromLocalChunk (int region, int localChunk)
    {
        return BlockFromChunk(region * 32 + localChunk);
    }

    private string LocalBlockFromLocalChunk (int localChunk)
    {
        return "(0 to 15)";
    }

    private string LocalBlockFromLocalChunk (int region, int localChunk)
    {
        return "(0 to 15)";
    }

    // Local Block Set

    private string RegionFromLocalBlock (int localBlock)
    {
        return "(ANY)";
    }

    private string ChunkFromLocalBlock (int localBlock)
    {
        return "(ANY)";
    }

    private string ChunkFromLocalBlock (int region, int localChunk, int localBlock)
    {
        return (region * 32 + localChunk).ToString();
    }

    private string BlockFromLocalBlock (int localBlock)
    {
        return "(ANY)";
    }

    private string BlockFromLocalBlock (int region, int localChunk, int localBlock)
    {
        return (region * 32 * 16 + localChunk * 16 + localBlock).ToString();
    }
*/

}
