package com.wangzi.test.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class Connect {

	public Node connect(Node root) {
		if(root == null) return null;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			int n = queue.size();
			Node last = null;
			for(int i=1; i<=n; i++) {
				Node f = queue.poll();
				if(f.left != null)
					queue.offer(f.left);
				if(f.right != null)
					queue.offer(f.right);
				if(i != 1)
					last.next = f;
				last = f;
			}
		}
		return root;
	}
	
	
	Node last = null, nextStart = null;
    public Node connect1(Node root) {
        if (root == null)
            return null;
        Node start = root;
        while (start != null) {
            last = null;
            nextStart = null;
            for (Node p = start; p != null; p = p.next) {
                if (p.left != null) 
                    handle(p.left);
                if (p.right != null)
                    handle(p.right);
            }
            start = nextStart;
        }
        return root;
    }

    public void handle(Node p) {
        if (last != null) 
            last.next = p; 
        if (nextStart == null) 
            nextStart = p;
        last = p;
    }

}
