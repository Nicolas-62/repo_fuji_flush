package com.test.nico;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> l = new ArrayList<Integer>();
		l.add(2);
		l.add(3);
		System.out.println("test"+ test(l));
		System.out.println("test"+ test(l));

	}
	public static Integer test(List l) {
		return (Integer) l.remove(1);
	}

}
