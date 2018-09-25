package com.wangzi.test.demo;

/**
 * 
 * @author Administrator
 * 在单链表中寻找倒数第K个元素
 */
public class LinkedListDemo<T> {
	//定义一个头节点
    Node head = null;

    //定义一个内部类，表示一个链表的节点
    class Node{
        private T t;
        Node next;
        public Node(T t) {
            this.t = t;
        }
    }

    //链表插入数据
    public void insert(T t){
        Node node = new Node(t);
        //如果头结点是空，那就是首次插入
        if(head == null){
            head = node;
        }else {
            //如果头结点不为空，那么寻找最后为空的位置插入
            Node p = head;
            while(p.next!= null){
                p = p.next;
            }
            p.next = node;
        }

    }
	//展示链表
	public void print(){
		Node p =head;
		while(p!=null){
			System.out.print(p.t + "->");
			p = p.next;
		}
		System.out.print("null\n");
	}
	
	/**
	 * 先遍历一遍链表，获取链表的总长度为N，那么我们就知道倒数第K的元素位置就是N-K。然后重新遍历该链表，寻找N-K位置的元素就可以了
	 * @param K
	 */
    public void analysis1(int K){
    if(K < 1){
            throw new IllegalArgumentException("K参数不合法");
        }
        //先遍历一次链表获得总长度
        int count = 0;
        Node p = head;
        while(p !=null ){
            count ++;
            p = p.next;
        }

        //再遍历一遍链表获得对于的值
        count = count - K;//把倒数转成顺序遍历的位置
        p = head;
        while(count > 0){
            p = p.next;
            count --;
        }
        System.out.println("倒数第" + K +"个元素为：" + p.t);

    } 

    public static void main(String[] args) {
        //构建一个链表
    	LinkedListDemo<Integer> example = new LinkedListDemo<Integer>();
        example.insert(1);
        example.insert(2);
        example.insert(3);
        example.insert(4);
        example.insert(5);
        //打印链表结构
        example.print();
        //获取倒数第三个元素
        example.analysis1(2);
        example.analysis2(2);
        example.analysis3(2);
    }
    
    /**
     * 从链表中的某一个节点开始，遍历K个元素到达链表尾部，那么这个开始的节点就是倒数第K个节点。那么实现上来说，
     * 我们就需要对每一个节点尝试进行遍历K个元素，查看是否到达链表尾部就可以了。
     * 比如开始的节点为C，那么只要保证C.(K-1).next !=null && C.K.next ==null的时候C就是我们要寻找的节点
     * @param K
     */
    public void analysis2(int K){
        if(K < 1){
            throw new IllegalArgumentException("K参数不合法");
        }
        //从头开始遍历链表
        Node p = head;
        while(getIndex(p, K) != null){
            p = p.next;
        }
        System.out.println("倒数第" + K +"个元素为：" + p.t);
    }

    //获取当前位置后k个数
    private Node getIndex(Node n, int k){
        while(k > 0){
            n = n.next;
            k --;
        }
        return n;

    }
    
    /**
     * 指针追击。设置两个指针，其中一个指针先行k步，然后两个指针同时向前移动。
     * 当先行的指针为null的时候，那么后面这个指针所指向的元素就是倒数第K个元素。
     * @param K
     */
    public void analysis3(int K){
        if(K < 1){
            throw new IllegalArgumentException("K参数不合法");
        }
        //设立两个指针
        Node fast = head;//快行K步
        Node slow = head;
        while(K > 0){
            //中途出现了null值，一般考虑K值不合法的情况
            if(fast == null){
                throw new IllegalArgumentException("K参数不合法");
            }
            fast = fast.next;
            K --;
        }
        //两个指针同时向前移动
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        System.out.println("倒数第" + K +"个元素为：" + slow.t);
    }
}

