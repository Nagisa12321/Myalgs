package com.jtchen.typeofdata;

import org.junit.Test;

import java.util.Arrays;

public class MyAVLTreeTest {

	@Test
	public void test1() {
		int[] numbers = {10, 70, 20, 30, 40, 50, 60};
		MyAVLTree<Integer> tree = new MyAVLTree<>();

		System.out.println("//////////////////     Test #1     //////////////////");

		for (int k = 0; k < numbers.length; k++)
			tree.add(numbers[k]);

		System.out.println("INORDER PRINT:");
		System.out.println(tree.inorder());

		System.out.println("\nPREORDER PRINT:");
		System.out.println(tree.preorder());
		System.out.println("\n");

		printStats(tree);
	}

	@Test
	public void test2() {
		int[] numbers = {10, 70, 20, 30, 40, 50, 60};
		MyAVLTree<Integer> tree = new MyAVLTree<>();
		for (int k = 0; k < numbers.length; k++)
			tree.add(numbers[k]);

		System.out.println("//////////////////     Test #2     //////////////////");
		int z = 89;
		System.out.println(z + " is in tree? " + tree.contains(z));
		z = 50;
		System.out.println(z + " is in tree? " + tree.contains(z));
	}

	@Test
	public void test3() {
		int[] numbers = {10, 70, 20, 30, 40, 50, 60};
		int[] delnumbers = {40, 60, 70, 35};
		MyAVLTree<Integer> tree = new MyAVLTree<>();
		for (int k = 0; k < numbers.length; k++)
			tree.add(numbers[k]);
		System.out.println("INORDER PRINT:");
		System.out.println(tree.inorder());

		System.out.println("PREORDER PRINT:");
		System.out.println(tree.preorder());
		System.out.println("//////////////////     Test #3     //////////////////");

		for (int k = 0; k < delnumbers.length; k++) {
			System.out.println("Delete: " + delnumbers[k]);
			tree.remove(delnumbers[k]);
			System.out.println("INORDER PRINT:");
			System.out.println(tree.inorder());
			System.out.println("PREORDER PRINT:");
			System.out.println(tree.preorder().toString() + '\n');
		}

		System.out.println("/////////////////////////////");
		System.out.println("INORDER PRINT:");
		System.out.println(tree.inorder());
		System.out.println("PREORDER PRINT:");
		System.out.println(tree.preorder());
		System.out.println("\n");

		printStats(tree);
	}

	public void printStats(MyAVLTree tree) {
		System.out.println("CHECK BALANCE : " + tree.isHeightBalanced());
		System.out.println("CHECK HEIGHT: " + tree.height());
		System.out.println("CHECK NODE: " + tree.size());
	}


	@Test
	public void test4() {
		System.out.println("//////////////////     Test #4     //////////////////");
		MyAVLTree<Integer> tree = new MyAVLTree<>();

		System.out.println("New tree4 size: " + 0);
		int k = 100;
		while (tree.size() != 10) {
			tree.add(k);
			k++;
			System.out.println("HEIGHT:" + tree.height());
		}
		System.out.println("/////////////////////////////");
		System.out.println("INORDER PRINT:");
		System.out.println(tree.inorder());
		System.out.println("PREORDER PRINT:");
		System.out.println(tree.preorder());
	}

	@Test
	public void test5() {
		System.out.println("//////////////////     Test #5     //////////////////");
		MyAVLTree<Integer> tree;
		int[] rand;

		tree = new MyAVLTree<>();

		rand = new int[10000];
		for (int i = 0; i < 10000; i++) {
			int rndnum = (int) (Math.random() * 100);
			rand[i] = rndnum;
			tree.add(rndnum);
		}

		System.out.println("/////////////////////////////");

		System.out.println("is balanced? :");
		System.out.println(tree.isHeightBalanced());
		System.out.println("random nums:");
		System.out.println(Arrays.toString(rand));
		System.out.println("INORDER PRINT:");
		System.out.println(tree.inorder());
		System.out.println("PREORDER PRINT:");
		System.out.println(tree.preorder());

		for (int i = 0; i < 50; i++) {
			tree.remove((int) (Math.random() * 100));
		}

		System.out.println("/////////////////////////////");
		System.out.println("INORDER PRINT:");
		System.out.println(tree.inorder());
		System.out.println("PREORDER PRINT:");
		System.out.println(tree.preorder());
		System.out.println("is balanced? :");
		System.out.println(tree.isHeightBalanced());
	}

	@Test
	public void test6() {
		System.out.println("//////////////////     Test #6     //////////////////");
		MyAVLTree<Integer> tree = new MyAVLTree<>();

		int[] rand = {34, 99, 89, 54, 83, 86, 35, 16, 73, 14};
		// int[] rand = {41, 20, 65, 11, 29, 50, 91, 32, 72, 99};
		for (int i = 0; i < 10; i++) {
			tree.add(rand[i]);
		}
		System.out.println("///////////////////////////////////////////////////////");
		System.out.println("random nums:");
		System.out.println(Arrays.toString(rand));
		System.out.println("INORDER PRINT:");
		System.out.println(tree.inorder());
		System.out.println("PREORDER PRINT:");
		System.out.println(tree.preorder());
		System.out.println("is balanced? :");
		System.out.println(tree.isHeightBalanced());

	}
}