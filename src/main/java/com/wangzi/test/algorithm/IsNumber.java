package com.wangzi.test.algorithm;

import java.util.HashMap;
import java.util.Map;

public class IsNumber {

	@SuppressWarnings("serial")
	public boolean isNumber(String s) {

		Map<State, Map<CharType, State>> transfer = new HashMap<>();
		Map<CharType, State> initialMap = new HashMap<CharType, State>() {{
			put(CharType.CHAR_SPACE, State.STATE_INITIAL);
			put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
			put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
			put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
		}};
		transfer.put(State.STATE_INITIAL, initialMap);
		Map<CharType, State> intSignMap = new HashMap<CharType, State> () {{
			put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
			put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
		}};
		transfer.put(State.STATE_INT_SIGN, intSignMap);
		Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
			put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
			put(CharType.CHAR_EXP, State.STATE_EXP);
			put(CharType.CHAR_POINT, State.STATE_POINT);
			put(CharType.CHAR_SPACE, State.STATE_END);
		}};
		transfer.put(State.STATE_INTEGER, integerMap);
		Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
			put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
			put(CharType.CHAR_EXP, State.STATE_EXP);
			put(CharType.CHAR_SPACE, State.STATE_END);
		}};
		transfer.put(State.STATE_POINT, pointMap);
		Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
			put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
		}};
		transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);
		Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
			put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
			put(CharType.CHAR_EXP, State.STATE_EXP);
			put(CharType.CHAR_SPACE, State.STATE_END);
		}};
		transfer.put(State.STATE_FRACTION, fractionMap);
		Map<CharType, State> expMap = new HashMap<CharType, State>() {{
			put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
			put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
		}};
		transfer.put(State.STATE_EXP, expMap);
		Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
			put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
		}};
		transfer.put(State.STATE_EXP_SIGN, expSignMap);
		Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
			put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
			put(CharType.CHAR_SPACE, State.STATE_END);
		}};
		transfer.put(State.STATE_EXP_NUMBER, expNumberMap);
		Map<CharType, State> endMap = new HashMap<CharType, State>() {{
			put(CharType.CHAR_SPACE, State.STATE_END);
		}};
		transfer.put(State.STATE_END, endMap);
		int length = s.length();
		State state = State.STATE_INITIAL;

		for (int i = 0; i < length; i++) {
			CharType type = toCharType(s.charAt(i));
			if (!transfer.get(state).containsKey(type)) {
				return false;
			} else {
				state = transfer.get(state).get(type);
			}
		}
		return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXP_NUMBER || state == State.STATE_END;

	}

	public CharType toCharType(char ch) {
		if (ch >= '0' && ch <= '9') {
			return CharType.CHAR_NUMBER;
		} else if (ch == 'e' || ch == 'E') {
			return CharType.CHAR_EXP;
		} else if (ch == '.') {
			return CharType.CHAR_POINT;
		} else if (ch == '+' || ch == '-') {
			return CharType.CHAR_SIGN;
		} else if (ch == ' ') {
			return CharType.CHAR_SPACE;
		} else {
			return CharType.CHAR_ILLEGAL;
		}
	}

	public static void main(String[] args) {
		IsNumber i = new IsNumber();
		String s = " 56 7 ";
		boolean number = i.isNumber2(s);
		System.out.println(number);
	}

	/**
	 * 1、起始的空格
	 * 2、符号位
	 * 3、整数部分
	 * 4、左侧有整数的小数点
	 * 5、左侧无整数的小数点（根据前面的第二条额外规则，需要对左侧有无整数的两种小数点做区分）
	 * 6、小数部分
	 * 7、字符 \text{e}e
	 * 8、指数部分的符号位
	 * 9、指数部分的整数部分
	 * 10、末尾的空格
	 * 「初始状态」应当为状态 1，而「接受状态」的集合则为状态 3、状态 4、状态 6、状态 9 以及状态 10
	 * 
	 * @author Administrator
	 *
	 */
	enum State {
		STATE_INITIAL,
		STATE_INT_SIGN,
		STATE_INTEGER,
		STATE_POINT,
		STATE_POINT_WITHOUT_INT,
		STATE_FRACTION,
		STATE_EXP,
		STATE_EXP_SIGN,
		STATE_EXP_NUMBER,
		STATE_END,
	}

	enum CharType {
		CHAR_NUMBER,
		CHAR_EXP,
		CHAR_POINT,
		CHAR_SIGN,
		CHAR_SPACE,
		CHAR_ILLEGAL,
	}

	/**
	 * 0、开始的空格1、幂符号前的正负号
	 * 2、小数点前的数字
	 * 3、小数点、小数点后的数字
	 * 4、当小数点前为空格时，小数点、小数点后的数字
	 * 5、幂符号
	 * 6、幂符号后的正负号
	 * 7、幂符号后的数字
	 * 8、结尾的空格
	 * 结束状态：合法的结束状态有 2, 3, 7, 8 
	 * @param s
	 * @return
	 */
	public boolean isNumber1(String s){
		Map[] states = {
				new HashMap<Character, Integer>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
				new HashMap<Character, Integer>() {{ put('d', 2); put('.', 4); }},                           // 1.
				new HashMap<Character, Integer>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
				new HashMap<Character, Integer>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
				new HashMap<Character, Integer>() {{ put('d', 3); }},                                        // 4.
				new HashMap<Character, Integer>() {{ put('s', 6); put('d', 7); }},                           // 5.
				new HashMap<Character, Integer>() {{ put('d', 7); }},                                        // 6.
				new HashMap<Character, Integer>() {{ put('d', 7); put(' ', 8); }},                           // 7.
				new HashMap<Character, Integer>() {{ put(' ', 8); }}                                         // 8.
		};
		int p = 0;
		char t;
		for(char c : s.toCharArray()) {
			if(c >= '0' && c <= '9') t = 'd';
			else if(c == '+' || c == '-') t = 's';
			else if(c == 'e' || c == 'E') t = 'e';
			else if(c == '.' || c == ' ') t = c;
			else t = '?';
			if(!states[p].containsKey(t)) return false;
			p = (int)states[p].get(t);
		}
		return p == 2 || p == 3 || p == 7 || p == 8;
	}

	/**
	 * ‘.’出现正确情况：只出现一次，且在e的前面
	 * ‘e’出现正确情况：只出现一次，且出现前有数字
	 * ‘+’‘-’出现正确情况：只能在开头和e后一位
	 * 
	 * @param s
	 * @return
	 */

	public boolean isNumber2(String s) {
		if(s == null || s.length() == 0) return false; // s为空对象或 s长度为0(空字符串)时, 不能表示数值
		boolean isNum = false, isDot = false, ise_or_E = false; // 标记是否遇到数位、小数点、‘e’或'E'
		// 转为字符数组，遍历判断每个字符
		char[] str = s.trim().toCharArray(); 
		for(int i=0; i<str.length; i++) {
			if(str[i] >= '0' && str[i] <= '9') 
				isNum = true; // 判断当前字符是否为 0~9 的数位
			else if(str[i] == '.') { // 遇到小数点
				if(isDot || ise_or_E)  // 小数点之前可以没有整数，但是不能重复出现小数点、或出现‘e’、'E'
					return false; 
				isDot = true; // 标记已经遇到小数点
			}
			else if(str[i] == 'e' || str[i] == 'E') { // 遇到‘e’或'E'
				if(!isNum || ise_or_E) // ‘e’或'E'前面必须有整数，且前面不能重复出现‘e’或'E'
					return false; 
				ise_or_E = true; // 标记已经遇到‘e’或'E'
				isNum = false; // 重置isNum，因为‘e’或'E'之后也必须接上整数，防止出现 123e或者123e+的非法情况
			}
			else if(str[i] == '-' ||str[i] == '+') { 
				if(i!=0 && str[i-1] != 'e' && str[i-1] != 'E')  // 正负号只可能出现在第一个位置，或者出现在‘e’或'E'的后面一个位置
					return false; 
			}
			else 
				return false; // 其它情况均为不合法字符
		}
		return isNum;
	}
}
