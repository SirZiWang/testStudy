package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolveNQueens {

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> result = new ArrayList<>();
		int[] queens = new int[n];
		Set<Integer> columns = new HashSet<Integer>();
		Set<Integer> diagonals1 = new HashSet<Integer>();
		Set<Integer> diagonals2 = new HashSet<Integer>();
		backtrack(result, queens, n, 0, columns, diagonals1, diagonals2);
		return result;
	}

	private void backtrack(List<List<String>> result, int[] queens, int n, int row, Set<Integer> columns,
			Set<Integer> diagonals1, Set<Integer> diagonals2) {
		if(n == row) {
			List<String> board = generateBoard(queens, n);
			result.add(board);
		}else {
			for (int i = 0; i < n; i++) {
				if (columns.contains(i)) {
					continue;
				}
				int diagonal1 = row - i;
				if (diagonals1.contains(diagonal1)) {
					continue;
				}
				int diagonal2 = row + i;
				if (diagonals2.contains(diagonal2)) {
					continue;
				}
				queens[row] = i;
				columns.add(i);
				diagonals1.add(diagonal1);
				diagonals2.add(diagonal2);
				backtrack(result, queens, n, row + 1, columns, diagonals1, diagonals2);
				queens[row] = -1;
				columns.remove(i);
				diagonals1.remove(diagonal1);
				diagonals2.remove(diagonal2);
			}
		}

	}

	private List<String> generateBoard(int[] queens, int n) {
		List<String> board = new ArrayList<>();
		for(int i=0; i<n; i++){
			char[] row = new char[n];
			Arrays.fill(row, '.');
			row[queens[i]] = 'Q';
			board.add(new String(row));
		}
		return board;
	}
	
	public List<List<String>> solveNQueens1(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> solutions = new ArrayList<List<String>>();
        solve(solutions, queens, n, 0, 0, 0, 0);
        return solutions;
    }

    public void solve(List<List<String>> solutions, int[] queens, int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
            while (availablePositions != 0) {
                int position = availablePositions & (-availablePositions);
                availablePositions = availablePositions & (availablePositions - 1);
                int column = Integer.bitCount(position - 1);
                queens[row] = column;
                solve(solutions, queens, n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
                queens[row] = -1;
            }
        }
    }
    
	public static void main(String[] args) {
		SolveNQueens s = new SolveNQueens();
		List<List<String>> solveNQueens = s.solveNQueens(4);
		System.out.println(solveNQueens.toString());
	}
}
