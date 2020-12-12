package com.allen.algorithm.half;

import java.util.Comparator;

/**
 * 二分查找  有序数组中查找某一特定元素的搜索算法。
 * @author allen小哥 2020/12/12 9:44
 */
public class HalfUtil {
    public static <T extends Comparable<T>> int binarySearch(T[] x,T key){
        return binarySearch(x,0,x.length-1,key);
    }

    /**
     * 循环遍历
     * @param x
     * @param key
     * @param comp
     * @param <T>
     * @return
     */
    public static <T> int binarySearch(T[] x, T key, Comparator<T> comp){
        int low = 0;
        int high = x.length - 1;
        while (low < high){
            int mid = (low+high) >>> 1; // 既是 (low+high)/2
            System.out.println("mid:"+mid);
            int cmp = comp.compare(x[mid],key);
            if (cmp < 0){
                low = mid + 1;
            }else if (cmp > 0){
                high = mid - 1;
            }else {
                return mid;
            }
         }
        return -1;
    }

    /**
     * 递归遍历
     * @param x
     * @param low
     * @param high
     * @param key
     * @param <T>
     * @return
     */
    private static <T extends Comparable<T>> int binarySearch(T[] x,int low,int high,T key){
        if (low <= high){
            int mid = low +((high - low )>> 1); //(high - low)/2
            System.out.println("mid:"+mid);
            if (key.compareTo(x[mid]) == 0){
                return mid;
            }
            else if (key.compareTo(x[mid])<0){
                return binarySearch(x,low,mid-1,key);
            }
            else {
                return binarySearch(x,mid+1,high,key);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer[] aa = {2,5,6,8,9};
        Integer value =8;
        int i = binarySearch(aa, value,Integer::compareTo);
        System.out.println("i==="+i);
    }
}
