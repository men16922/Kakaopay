package com.bm.kakaopay.algorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 과제 2
 */
public class p2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine()); // 열
        int m = Integer.parseInt(sc.nextLine()); // 행

        String numbers = sc.nextLine(); // 배열
        String rotation = sc.nextLine(); // 회전
        String location = sc.nextLine(); // 위치

        int[] locs = Arrays.stream(location.split(",")).mapToInt(Integer::parseInt).toArray();

        String[] nums = numbers.split(",");


        int[][] arr = new int[m][n];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(nums[cnt]);
                cnt++;
            }
        }

        for(int i = 0; i < rotation.length(); i++) {
            arr = rotate(arr, rotation.charAt(i));
        }

        System.out.println(arr[locs[1] - 1][locs[0] - 1]);

    }

    static int[][] rotate(int[][] arr, char direction) {
        int col = arr.length;
        int row = arr[0].length;

        int[][] rotate;

        if(direction == 'T') {
            rotate = flipOver(arr);
        } else {
            rotate = new int[row][col];
            for (int i = 0; i < rotate.length; i++) {
                for (int j = 0; j < rotate[i].length; j++) {
                    switch (direction) {
                        case 'L':
                            rotate[i][j] = arr[j][row-1-i];
                            break;
                        case 'R':
                            rotate[i][j] = arr[col-1-j][i];
                            break;
                    }
                }
            }
        }

        return rotate;
    }

    public static int[][] flipOver(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col / 2; j++){
                int tmp = arr[i][j];
                arr[i][j] = arr[i][col - 1 - j];
                arr[i][col - 1 - j] = tmp;
            }
        }

        return arr;
    }

}

