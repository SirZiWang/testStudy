package com.wangzi.test.algorithm;

public class ShellSort {
	//希尔增量下的希尔排序
	//为了使代码更加的通用，我们用泛型来书写，同时和前几篇一样实现comparable接口
	public static<T extends Comparable<? super T>> void shellsort(T[] target){//用T表示泛型
		int j;//标记当前数据应该要插入的位置

		//第一个for循环，循环增量
		//希尔增量增量大于0，且第一个增量为目标数据总长度的一半取整，后面的增量都为前面增量的一半取整
		for(int increment = target.length / 2; increment > 0; increment /=2){

			//简单的插入排序
			//遍历目标数据，从第一个增量开始，第0个增量默认排序好的
			for(int i = increment; i < target.length; i++){
				//把带插入的数据用temp暂存起来
				T temp = target[i];
				//寻找准确的插入位置
				//其实核心是在增量的分组下寻找这个值在某一个分组下应该属于它的位置
				for(j = i; j >= increment&&temp.compareTo(target[j - increment]) < 0; j-=increment){//注意，因为是处理分组的，所以不能和简单插入排序一样j--
					//如果当前位置的数据比前一个数据小，那么就需要把前面数据往后移动一位
					target[j] = target[j - increment];//这里并不是冒泡的交换位置！！
				}
				target[j] = temp;//把数据插入到准确的地方，这个才是插入排序
				//打印每次排序后的结果
				String result = "";
				for (T t : target) {
					result += t+" ";
				}
				System.out.println(increment+"增量的排序结果：" + result);

			}
		}
	}

	public static void main(String[] args) {

		Integer[] target = {8,2,3,7,5};
		shellsort(target);
	}
}
