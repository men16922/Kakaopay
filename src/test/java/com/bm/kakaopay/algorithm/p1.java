package com.bm.kakaopay.algorithm;

import java.util.Scanner;

/**
 * 과제 1
 */
public class p1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine()); // 열
        int m = Integer.parseInt(sc.nextLine()); // 행

        String numbers = sc.nextLine();

        String[] nums = numbers.split(",");

        int[][] arr = new int[m][n];
        int[][] ans = new int[m][n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(nums[cnt]);
                cnt++;
            }
        }
        boolean goUp = true;
        int col = 0, row = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = arr[col][row];
                if (goUp) {
//                    System.out.println("Up");
                    if (row == n - 1) { // 현재 열이 최대크기와 같으면 행을 더하고 아래로 내려간다.
                        col++;
                        goUp = false;
//                        System.out.println(col + "," + l);
                    } else if (col == 0) { // 현재 행이 0이면 열을 더하고 아래로 내려간다.
                        row++;
                        goUp = false;
//                        System.out.println(col + "," + l);
                    } else { // 열을 더하고 행을 뺀다
                        col--;
                        row++;
//                        System.out.println(col + "," + l);
                    }
                } else {
//                    System.out.println("Down");
                    if (col == m - 1) { // 현재 행이 최대크기와 같으면 열을 더하고 위로 올라간다.
                        row++;
                        goUp = true;
//                        System.out.println(col + "," + l);
                    } else if (row == 0) { // 현재 열이 0이면 행을 더하고 위로 올라간다.
                        col++;
                        goUp = true;
//                        System.out.println(col + "," + l);
                    } else { // 행을 더하고 열을 뺀다
                        row--;
                        col++;
//                        System.out.println(col + "," + l);
                    }
                }

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(ans[i][j] + " ");
            }
        }
    }
}

