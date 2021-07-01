package org.nicolas.test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhaosi
 * @version 1.0
 * @description: TODO
 * @date 2021/6/22 上午10:25
 */
public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        Integer[] a = new Integer[random.nextInt(35)];

        for (int i = 0; i < 29; i++) {
            a[i] = i ;
        }

        System.out.print(Arrays.toString(a));

        System.out.println(a.length);

    }
}
