package ru.wpstuio.amorphine.utils;

/**
 * Created by amorphine on 23.11.16.
 */
public class Helpers {
    /**
     * Helps to calculate offset for Add and Data sections which are known to keep 4 bits for a block
     * @param arr
     * @param index
     * @return
     */
    public static byte Nibble4(byte[] arr, int index){
        return (byte) (index%2 == 0 ? arr[index/2]&0x0F : (arr[index/2]>>4)&0x0F);
    }
}
