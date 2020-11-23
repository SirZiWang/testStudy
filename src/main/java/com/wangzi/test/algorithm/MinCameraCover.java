package com.wangzi.test.algorithm;

import com.wangzi.test.algorithm.BinaryTreePaths.TreeNode;

public class MinCameraCover {
	public int minCameraCover(TreeNode root) {
		int[] array = dfs(root);
		return array[1];
	}

	private int[] dfs(TreeNode root) {
		if(root == null)
			return new int[]{Integer.MAX_VALUE/2, 0, 0};
		int[] leftArray = dfs(root.left);
		int[] rightArray = dfs(root.right);
		int[] array = new int[3];
		array[0] = leftArray[2] + rightArray[2] + 1;
		array[1] = Math.min(array[0], Math.min(leftArray[0] + rightArray[1], rightArray[0] + leftArray[1]));
		array[2] = Math.min(array[0], leftArray[1] + rightArray[1]);
		return array;
	}

	int result = 0;
	public int minCameraCover1(TreeNode root) {
		if(dfs1(root)==1){
			result++;
		}
		return result;
	}
	//0:可被观测但无监控，上一层节点为1
	//1：不可被观测到，上一层节点为2
	//2：有摄像机，上一层节点为0
	private int dfs1(TreeNode root){
		if(root==null){
			return 0;
		}
		int leftStatus = dfs1(root.left),rightStatus = dfs1(root.right);
		if(leftStatus==1||rightStatus==1){
			result++;
			return 2;
		}else if(leftStatus==2||rightStatus==2){
			return 0;
		}else{
			return 1;
		}
	}

	
	//NO_CAMERA表示的是子节点没有相机，当前节点也没放相机
    private final int NO_CAMERA = 0;
    //HAS_CAMERA表示当前节点有一个相机
    private final int HAS_CAMERA = 1;
    //NO_NEEDED表示当前节点没有相机，但他的子节点有一个相机，把它给
    //覆盖了，所以它不需要了。或者他是一个空的节点也是不需要相机的
    private final int NO_NEEDED = 2;
    //全局的，统计有多少相机
    int res = 0;
    public int minCameraCover2(TreeNode root) {
        //边界条件判断
        if (root == null)
            return 0;
        //如果最后返回的是NO_CAMERA，表示root节点的子节点也没有相机，
        //所以root节点要添加一个相机
        if (dfs2(root) == NO_CAMERA)
            res++;
        //返回结果
        return res;
    }

    public int dfs2(TreeNode root) {
        //如果是空的，就不需要相机了
        if (root == null)
            return NO_NEEDED;
        int left = dfs2(root.left), right = dfs2(root.right);
        //如果左右子节点有一个是NO_CAMERA，表示的是子节点既没相机，也没相机覆盖它，
        //所以当前节点需要有一个相机
        if (left == NO_CAMERA || right == NO_CAMERA) {
            //在当前节点放一个相机，统计相机的个数
            res++;
            return HAS_CAMERA;
        }
        //如果左右子节点只要有一个有相机，那么当前节点就不需要相机了，否则返回一个没有相机的标记
        return left == HAS_CAMERA || right == HAS_CAMERA ? NO_NEEDED : NO_CAMERA;
    }

}
