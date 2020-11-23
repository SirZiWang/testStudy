package com.wangzi.test.algorithm;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LadderLength {

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> set = new HashSet<>(wordList);
		if(set.size() == 0 || !set.contains(endWord)) return 0;
		set.remove(beginWord);
		Deque<String> queue  = new LinkedList<>();
		queue.offer(beginWord);
		Set<String> visited = new HashSet<>();
		visited.add(beginWord);
		int step = 1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i=0; i<size; i++) {
				String currentWord = queue.poll();
				if(changeWordEveryOneLetter(currentWord, endWord, queue, visited, set)) {
					return step + 1;
				}
			}
			step++;
		}
		return 0;
	}

	private boolean changeWordEveryOneLetter(String currentWord, String endWord, Deque<String> queue,
			Set<String> visited, Set<String> set) {
		char[] charArray = currentWord.toCharArray();
		for(int i=0; i<endWord.length(); i++) {
			char originChar = charArray[i];
			for(char j='a'; j<='z'; j++) {
				if(j == originChar) 
					continue;
				charArray[i] = j;
				String nextWord = String.valueOf(charArray);
				if(set.contains(nextWord)) {
					if(nextWord.equals(endWord)) return true;
					if(!visited.contains(nextWord)) {
						queue.offer(nextWord);
						visited.add(nextWord);
					}
				}
			}
			charArray[i] = originChar;
		}
		return false;
	}


	public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
		// 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
		Set<String> wordSet = new HashSet<>(wordList);
		if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
			return 0;
		}

		// 第 2 步：已经访问过的 word 添加到 visited 哈希表里
		Set<String> visited = new HashSet<>();
		// 分别用左边和右边扩散的哈希表代替单向 BFS 里的队列，它们在双向 BFS 的过程中交替使用
		Set<String> beginVisited = new HashSet<>();
		beginVisited.add(beginWord);
		Set<String> endVisited = new HashSet<>();
		endVisited.add(endWord);

		// 第 3 步：执行双向 BFS，左右交替扩散的步数之和为所求
		int step = 1;
		while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
			// 优先选择小的哈希表进行扩散，考虑到的情况更少
			if (beginVisited.size() > endVisited.size()) {
				Set<String> temp = beginVisited;
				beginVisited = endVisited;
				endVisited = temp;
			}

			// 逻辑到这里，保证 beginVisited 是相对较小的集合，nextLevelVisited 在扩散完成以后，会成为新的 beginVisited
			Set<String> nextLevelVisited = new HashSet<>();
			for (String word : beginVisited) {
				if (changeWordEveryOneLetter(word, endVisited, visited, wordSet, nextLevelVisited)) {
					return step + 1;
				}
			}

			// 原来的 beginVisited 废弃，从 nextLevelVisited 开始新的双向 BFS
			beginVisited = nextLevelVisited;
			step++;
		}
		return 0;
	}

	private boolean changeWordEveryOneLetter(String word, Set<String> endVisited,
			Set<String> visited,
			Set<String> wordSet,
			Set<String> nextLevelVisited) {
		char[] charArray = word.toCharArray();
		for (int i = 0; i < word.length(); i++) {
			char originChar = charArray[i];
			for (char c = 'a'; c <= 'z'; c++) {
				if (originChar == c) {
					continue;
				}
				charArray[i] = c;
				String nextWord = String.valueOf(charArray);
				if (wordSet.contains(nextWord)) {
					if (endVisited.contains(nextWord)) {
						return true;
					}
					if (!visited.contains(nextWord)) {
						nextLevelVisited.add(nextWord);
						visited.add(nextWord);
					}
				}
			}
			// 恢复，下次再用
			charArray[i] = originChar;
		}
		return false;
	}

}
