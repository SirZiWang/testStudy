package com.wangzi.test.algorithm;

/**
 * 二叉堆
 * @author Administrator
 *
 * @param <T>
 */
public class HeapDemo<T extends Comparable<? super T>> {
	
	//默认容量
	private static final int DEFAULT_CAPACITY = 10;
	
	//用数组存储二叉堆
	private T[] table;
	
	//表示当前二叉堆中有多少数据
	int size;
	
	@SuppressWarnings("unchecked")
	public HeapDemo(int capactiy){
		this.size = 0; //初始化二叉堆数据量
		table = (T[]) new Comparable[capactiy + 1]; //+1是因为我们要空出下标为0的元素不存储
	}
	
	public HeapDemo(){
		this(DEFAULT_CAPACITY);
	}
	
	//插入
	public void insert(T t){
		//判断是否需要扩容
		if(size == table.length - 1){
			resize();
		}
		//开始插入
		//定义一个预插入位置下标
		int target = ++size;
		//循环比较父节点进行位置交换
		for(table[0] = t; t.compareTo(table[target/2]) < 0; target /= 2){
			table[target] = table[target/2];//如果满足条件，那么两者交换，知道找到合适位置（上滤）
		}
		//插入数据
		table[target] = t;
		print();
	}
	
	//删除最小
    //删除过程中，需要重新调整二叉堆（下滤）
    public void deleteMin(){
    	if(size == 0){
    		throw new IllegalAccessError("二叉堆为空");
    	}
    	table[1] = table[size--];
    	int target = 1; //从顶部开始重新调整二叉堆
    	int child; //要处理的节点下标
    	T temp = table[target];
    	for(; target * 2 <= size; target = child){
    		child = target * 2;
    		if(child != size && table[target+1].compareTo(table[child])<0){ //如果右孩子比左孩子小
    			child++;
    		}
    		if(table[ child ].compareTo( temp ) < 0 ){
                table[ target ] = table[ child ];
                table[child] = null;
            }
            else{
                 break;
            }
        }
        table[ target ] = temp;

        print();
    }
    
    //扩容
	@SuppressWarnings("unchecked")
	private void resize() {
		T[] old = table;
		table = (T[]) new Comparable[old.length*2 +1];
		for(int i=0; i<old.length; i++){
			table[i] = old[i];
		}
	}
	
	//打印数组
	private void print(){
		System.out.println();
		for (int i = 1; i <= size; i++) {
			System.out.print(table[i] + " ");
		}
		System.out.println("二叉堆大小:"+size);
	}
	
	public void delHeap(int target){
		int child; //要处理的节点下标
		T temp = table[target];
		for(; target * 2 <= size; target = child){
			child = target * 2;
			if(child != size && table[child+1].compareTo(table[child])<0){ //如果右孩子比左孩子小
				child ++;
			}
			if(table[ child ].compareTo( temp ) < 0 ){
                table[ target ] = table[ child ];
                table[child] = null;
            }
            else{
                 break;
            }
		}
		table[ target ] = temp;
	}
	
	public static void main(String[] args) {
		HeapDemo<Integer> heap = new HeapDemo<>();

        //循环插入0~9的数据
        for (int i = 0; i < 10; i++) {
            heap.insert(i);
        }

        //循环删除3次，理论上是删除0,1,2 
        for (int i = 0; i < 3; i++) {
            heap.deleteMin();
        }
	}
}
