package com.allen.algorithm.maopao;

import java.util.Comparator;

/**
 * 冒泡排序法
 * @author allen小哥 2020/12/12 9:20
 */
public class MaoPaoSorterImpl implements MaoPaoSorter {
    @Override
    public <T extends Comparable<T>> void sort(T[] list) {
        boolean flag = true;
        for (int i = 1, len = list.length;i<len && flag;++i){
            flag = false;
            for (int j = 0;j < len-i;++j){
                if (list[j].compareTo(list[j+1]) > 0){
                    // 定义一个临时变量存值
                    T temp = list[j];
                    // 数组位置交换，将值小的放在前面，值大的放后面
                    list[j] = list[j+1];
                    // 临时值赋予后面的数组下标
                    list[j+1] = temp;
                    flag = true;
                }
            }
        }

    }

    @Override
    public <T> void sort(T[] list, Comparator<T> comp) {
        boolean flag = true;
        for (int i = 1, len = list.length;i<len && flag;++i){
            flag = false;
            for (int j = 0;j < len-i;++j){
                if (comp.compare(list[j],list[j+1]) > 0){
                    // 定义一个临时变量存值
                    T temp = list[j];
                    // 数组位置交换，将值小的放在前面，值大的放后面
                    list[j] = list[j+1];
                    // 临时值赋予后面的数组下标
                    list[j+1] = temp;
                    flag = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        MaoPaoSorterImpl maoPaoSorter = new MaoPaoSorterImpl();
        Integer[] aa = {1,9,5,3,7,4};
//        maoPaoSorter.sort(aa,Integer::compareTo);
        maoPaoSorter.sort(aa);
        for (Integer temp:aa){
            System.out.println(temp);
        }
    }
}
