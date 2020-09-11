package com_2.class01;

public class Code06_ZigZagPrintMatrix {

	public static void printMatrixZigZag(int[][] matrix) {
		int AR = 0;
		int AC = 0;
		int BR = 0;
		int BC = 0;
		int endR = matrix.length - 1;
		int endC = matrix[0].length - 1;
		boolean fromUp = false;
		while (AR != endR + 1) {
			printLevel(matrix, AR, AC, BR, BC, fromUp);
			AR = AC == endC ? AR + 1 : AR;
			AC = AC == endC ? AC : AC + 1;
			BC = BR == endR ? BC + 1 : BC;
			BR = BR == endR ? BR : BR + 1;
			fromUp = !fromUp;
		}
		System.out.println();
	}

	public static void printLevel(int[][] m, int AR, int AC, int BR, int BC,
			boolean f) {
		if (f) {
			while (AR != BR + 1) {
				System.out.print(m[AR++][AC--] + " ");
			}
		} else {
			while (BR != AR - 1) {
				System.out.print(m[BR--][BC++] + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		printMatrixZigZag(matrix);

	}

}
