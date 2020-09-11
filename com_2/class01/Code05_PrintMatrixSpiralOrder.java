package com_2.class01;

public class Code05_PrintMatrixSpiralOrder {

	public static void spiralOrderPrint(int[][] matrix) {
		int upR = 0;
		int upC = 0;
		int lowR = matrix.length - 1;
		int lowC = matrix[0].length - 1;
		while (upR <= lowR && upC <= lowC) {
			printEdge(matrix, upR++, upC++, lowR--, lowC--);
		}
	}

	public static void printEdge(int[][] m, int upR, int upC, int lowR, int lowC) {
		if (upR == lowR) {
			for (int i = upC; i <= lowC; i++) {
				System.out.print(m[upR][i] + " ");
			}
		} else if (upC == lowC) {
			for (int i = upR; i <= lowR; i++) {
				System.out.print(m[i][upC] + " ");
			}
		} else {
			int curC = upC;
			int curR = upR;
			while (curC != lowC) {
				System.out.print(m[upR][curC] + " ");
				curC++;
			}
			while (curR != lowR) {
				System.out.print(m[curR][lowC] + " ");
				curR++;
			}
			while (curC != upC) {
				System.out.print(m[lowR][curC] + " ");
				curC--;
			}
			while (curR != upR) {
				System.out.print(m[curR][upC] + " ");
				curR--;
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		spiralOrderPrint(matrix);

	}

}
