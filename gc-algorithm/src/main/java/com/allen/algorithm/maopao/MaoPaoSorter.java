package com.allen.algorithm.maopao;

import java.util.Comparator;

/**
 * @author allen小哥 2020/12/12 9:16
 */
public interface MaoPaoSorter {

    /**
     * 排序
     * @param list 数组
     * @param <T>
     */
    public <T extends Comparable<T>> void sort(T[] list);

    /**
     * 排序
     * @param list 数组
     * @param comp 比较器
     * @param <T>
     */
    public <T> void sort(T[] list, Comparator<T> comp);

}
