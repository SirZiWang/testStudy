package com.wangzi.test.dataStructure;

/**
 * 顺序存储
 * 二叉树的顺序存储结构就是用一维数组来存储二叉树中的结点。不使用数组的第一个位置。
 * 结点的存储位置反映了它们之间的逻辑关系：位置k的结点的双亲结点的位置为「k/2」，它的两个孩子结点的位置分别为2k和2k+1。
 * @author Administrator
 *
 */
public class ArrayBinaryTree<E> {
	private static final int DEFAULT_DEPTH = 5;
	private int size = 0;
	private  E[] datas;
	
	ArrayBinaryTree(){
		this(DEFAULT_DEPTH);
	}

	@SuppressWarnings("unchecked")
	public ArrayBinaryTree(int depth) {
		datas = (E[]) new Object[(int) Math.pow(2, depth)];
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public int size(){
		return size;
	}
	public E getRoot(){
		return datas[1];
	}
	//返回指定节点的父节点   
	public E getParent(int index){
		checkIndex(index);
		if(index == 1){
			throw new RuntimeException("根节点不存在父节点！");
		}
		return datas[index/2];
	}
	//获取右子节点  
	public E getRight(int index){
		checkIndex(2*index + 1);
		return datas[2*index + 1];
	}
	//获取左子节点
	public E getLeft(int index){
		checkIndex(2*index);
		return datas[2*index];
	}
	//返回指定数据的位置
	public int indexOf(E date){
		if(date == null){
			throw new NullPointerException();
		} else{
			for(int i=0; i<datas.length; i++){
				if(date.equals(datas[i])){
					return i;
				}
			}
		}
		return -1;
	}
	//顺序添加元素
	public void add(E element){
		checkIndex(size +1);
		datas[size + 1] = element;
		size++;
	}
	//在指定位置添加元素
	public void add(E element, int parent, boolean isLeft){
		if(datas[parent] == null){
			throw new RuntimeException("index["+parent*2+"] is not exits!");
		}
		if(element == null){
			throw new NullPointerException();
		}
		if(isLeft){
			checkIndex(2*parent);
			if(datas[2*parent] != null){
				 throw new RuntimeException("index["+parent*2+"] is  Exist!"); 
			}
			datas[2*parent] = element;
		}else {
			checkIndex(2*parent + 1);
            if(datas[(parent+1)*2]!=null) {  
                throw new RuntimeException("index["+ parent*2+1 +"] is  Exist!");  
            } 
            datas[2*parent + 1] = element;
		}
		size++;
	}
	//检查下标是否越界
	private void checkIndex(int index) {
		if(index <= 0 || index >= datas.length){
			 throw new IndexOutOfBoundsException();  
		}
	}
	public static void main(String[] args) {
		char[] data = {'A','B','C','D','E','F','G','H','I','J'};
		ArrayBinaryTree<Character> abt = new ArrayBinaryTree<>();
		for(int i=0; i<data.length; i++) {
			abt.add(data[i]);
		}
		System.out.print(abt.getParent(abt.indexOf('B')));
	}
}
