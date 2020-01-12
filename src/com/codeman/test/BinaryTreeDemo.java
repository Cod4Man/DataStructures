package com.codeman.test;

public class BinaryTreeDemo {

	public static void main(String[] args) {
		//?????????????????
		BinaryTree binaryTree = new BinaryTree();
		//???????????
		HeroNode root = new HeroNode(1, "???");
		HeroNode node2 = new HeroNode(2, "????");
		HeroNode node3 = new HeroNode(3, "?????");
		HeroNode node4 = new HeroNode(4, "???");
		HeroNode node5 = new HeroNode(5, "???");
		
		//?????????????????????????????????????????????????????
		root.setLeft(node2);
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);
		binaryTree.setRoot(root);
		
		//????
//		System.out.println("??????"); // 1,2,3,5,4
//		binaryTree.preOrder();
		
		//???? 
//		System.out.println("???????");
//		binaryTree.infixOrder(); // 2,1,5,3,4
//		
//		System.out.println("???????");
//		binaryTree.postOrder(); // 2,5,4,3,1
		
		//??????
		//??????????? ??4 
//		System.out.println("?????????~~~");
//		HeroNode resNode = binaryTree.preOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("?????????? no=%d name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("?????? no = %d ?????", 5);
//		}
		
		//???????????
		//???????3??
//		System.out.println("??????????~~~");
//		HeroNode resNode = binaryTree.infixOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("?????????? no=%d name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("?????? no = %d ?????", 5);
//		}
		
		//???????????
		//???????????????  2??
//		System.out.println("??????????~~~");
//		HeroNode resNode = binaryTree.postOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("?????????? no=%d name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("?????? no = %d ?????", 5);
//		}
		
		//?????????????
		
		System.out.println("????,??????");
		binaryTree.preOrder(); //  1,2,3,5,4
		binaryTree.delNode(5);
		//binaryTree.delNode(3);
		System.out.println("???????????");
		binaryTree.preOrder(); // 1,2,3,4
		
		
		
	}

}

//????BinaryTree ??????
class BinaryTree {
	private HeroNode root;

	public void setRoot(HeroNode root) {
		this.root = root;
	}
	
	//??????
	public void delNode(int no) {
		if(root != null) {
			//?????????root???, ???????????root???????????????
			if(root.getNo() == no) {
				root = null;
			} else {
				//??????
				root.delNode(no);
			}
		}else{
			System.out.println("?????????????~");
		}
	}
	//??????
	public void preOrder() {
		if(this.root != null) {
			this.root.preOrder();
		}else {
			System.out.println("?????????????????");
		}
	}
	
	//???????
	public void infixOrder() {
		if(this.root != null) {
			this.root.infixOrder();
		}else {
			System.out.println("?????????????????");
		}
	}
	//???????
	public void postOrder() {
		if(this.root != null) {
			this.root.postOrder();
		}else {
			System.out.println("?????????????????");
		}
	}
	
	//??????
	public HeroNode preOrderSearch(int no) {
		if(root != null) {
			return root.preOrderSearch(no);
		} else {
			return null;
		}
	}
	//???????
	public HeroNode infixOrderSearch(int no) {
		if(root != null) {
			return root.infixOrderSearch(no);
		}else {
			return null;
		}
	}
	//???????
	public HeroNode postOrderSearch(int no) {
		if(root != null) {
			return this.root.postOrderSearch(no);
		}else {
			return null;
		}
	}
}

//?????HeroNode ???
class HeroNode {
	private int no;
	private String name;
	private HeroNode left; //???null
	private HeroNode right; //???null
	public HeroNode(int no, String name) {
		this.no = no;
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HeroNode getLeft() {
		return left;
	}
	public void setLeft(HeroNode left) {
		this.left = left;
	}
	public HeroNode getRight() {
		return right;
	}
	public void setRight(HeroNode right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + "]";
	}
	
	//?????????
	//1.??????????????????????????
	//2.?????????????????????????????
	public void delNode(int no) {
		
		//??
		/*
		 * 	1. ???????????????????????????????????????????????????????????????????????????????????????.
			2. ?????????????????????????????? ??????????????this.left = null; ????????(??????????)
			3. ?????????????????????????????? ??????????????this.right= null ;????????(??????????)
			4. ?????2???3???????????????????????????????????????
			5.  ?????4??????????????????????????????????.

		 */
		//2. ?????????????????????????????? ??????????????this.left = null; ????????(??????????)
		if(this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		//3.?????????????????????????????? ??????????????this.right= null ;????????(??????????)
		if(this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}
		//4.?????????????????????????
		if(this.left != null) {
			this.left.delNode(no);
		}
		//5.??????????????????????
		if(this.right != null) {
			this.right.delNode(no);
		}
	}
	
	//??????????????
	public void preOrder() {
		System.out.println(this); //??????????
		//?????????????????
		if(this.left != null) {
			this.left.preOrder();
		}
		//?????????????????
		if(this.right != null) {
			this.right.preOrder();
		}
	}
	//???????
	public void infixOrder() {
		
		//??????????????????
		if(this.left != null) {
			this.left.infixOrder();
		}
		//????????
		System.out.println(this);
		//??????????????????
		if(this.right != null) {
			this.right.infixOrder();
		}
	}
	//???????
	public void postOrder() {
		if(this.left != null) {
			this.left.postOrder();
		}
		if(this.right != null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}
	
	//??????????
	/**
	 * 
	 * @param no ????no
	 * @return ????????????Node ,????????????? null
	 */
	public HeroNode preOrderSearch(int no) {
		System.out.println("??????????");
		//?????????????
		if(this.no == no) {
			return this;
		}
		//1.???????????????????????????????????????????
		//2.???????????????????????
		HeroNode resNode = null;
		if(this.left != null) {
			resNode = this.left.preOrderSearch(no);
		}
		if(resNode != null) {//????????????????
			return resNode;
		}
		//1.??????????????????????????????
		//2.??????????????????????????????????????????????
		if(this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		return resNode;
	}
	
	//???????????
	public HeroNode infixOrderSearch(int no) {
		//??????????????????????????????????????????
		HeroNode resNode = null;
		if(this.left != null) {
			resNode = this.left.infixOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		System.out.println("???????????");
		//???????????????????????????????????????????????
		if(this.no == no) {
			return this;
		}
		//???????????????????????
		if(this.right != null) {
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;
		
	}
	
	//???????????
	public HeroNode postOrderSearch(int no) {
		
		//?????????????????????????????????????????
		HeroNode resNode = null;
		if(this.left != null) {
			resNode = this.left.postOrderSearch(no);
		}
		if(resNode != null) {//??????????????
			return resNode;
		}
		
		//???????????????????????????????????????????
		if(this.right != null) {
			resNode = this.right.postOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		System.out.println("??????????");
		//???????????????????????????????????
		if(this.no == no) {
			return this;
		}
		return resNode;
	}
	
}

//


