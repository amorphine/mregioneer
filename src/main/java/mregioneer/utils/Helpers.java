package mregioneer.utils;


public class Helpers {
    /**
     * Helps to calculate offset for Add and Data sections which are known to keep 4 bits for a block
     * @param arr nibble array
     * @param index index of correlated 'Blocks' id
     * @return
     */
    public static byte Nibble4(byte[] arr, int index){
        return (byte) (index%2 == 0 ? arr[index/2]&0x0F : (arr[index/2]>>4)&0x0F);
    }


    /**
     * Writes a nibble into an nibble array. Method is used when we write id to Data or Add parts of sections.
     * @param arr nibble array
     * @param value value to be inserted
     * @param offset index of correlated 'Blocks' id
     */
    public static byte[] writeNibble4(byte[] arr, byte value, int offset) {
        int shift = (offset % 2) << 2;
        arr[offset/2] = (byte) ((arr[offset/2] & (0xF0 >> shift)) | ((value &0xF) << shift));

        return arr;
    }
}
