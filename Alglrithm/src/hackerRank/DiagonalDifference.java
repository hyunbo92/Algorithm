package hackerRank;

import java.util.Scanner;

public class DiagonalDifference {

    public static void main(String[] args) {
           Scanner sc = new Scanner(System.in);
        int  size = sc.nextInt();
        int[][] matrix = new int[size][size];
        for (int  x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                matrix[x][y] = sc.nextInt();
            }
        }
        int leftcross=0,rightcross=0;
        for (int  x = 0; x < size; x++) {
            leftcross += matrix[x][x];
            rightcross += matrix[x][size-x-1];
        }
        System.out.println(Math.abs(leftcross-rightcross));
    }
}