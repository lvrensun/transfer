package com.tencent.utils;

import java.util.ArrayList;
import java.util.List;

public class GListUtil {
    /**
     * @param list
     * @param listCount 要拼装的list总数
     * @return
     */
    public static List<List> insList(List<Class> list, int listCount) {
        ArrayList<String> resultList = new ArrayList<>();
//        resultList.
        if (null == list && list.isEmpty()) {
            return null;
        }
        int  th = list.size() % listCount;

        int evrListSize = list.size() / listCount;
//        int realList = th > 0 ? countList + 1 : countList;
//        for (int i = 1; i <= realList; i++) {
//            List ver = new ArrayList<>();
//
//        }
        return null;
    }
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remainder = source.size() % n;  //(先计算出余数)
        int number = source.size() / n;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remainder > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remainder--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }



    public static <T> List<T> toSublist(List<T> originalList, int firstIndex, int lastIndex) {
        if (originalList.size() < lastIndex && originalList.size() >= firstIndex) {
            return originalList.subList(firstIndex, originalList.size());
        } else {
            return (List)(originalList.size() < firstIndex ? new ArrayList( 0) : originalList.subList(firstIndex, lastIndex));
        }
    }

    public static <T> List<List<T>> divSublist(List<T> list, int everyListSize) {
        List<List<T>> answer = new ArrayList();
        int size = list.size();
        int count = (size + everyListSize - 1) / everyListSize;

        for(int i = 0; i < count; ++i) {
            answer.add(list.subList(i * everyListSize, (i + 1) * everyListSize > size ? size : everyListSize * (i + 1)));
        }

        return answer;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(6);
        arr.add(7);
        arr.add(8);
        arr.add(9);
        arr.add(10);
        arr.add(11);
        arr.add(12);
        arr.add(13);
        arr.add(14);
        arr.add(15);
        arr.add(16);
        arr.add(17);
        arr.add(18);
        arr.add(19);
        List<List<Integer>> lists = averageAssign(arr, 20);
        System.out.println(lists);
    }
}
