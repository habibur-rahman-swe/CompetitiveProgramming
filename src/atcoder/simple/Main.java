package atcoder.simple;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		A a = new A();
		a.print();
		
		B b = new B();
		b.print();
		
		C c = new C();
		c.print();
	}
}

class A {
	public void print() {
		System.out.println("A");
	}
}

class B extends A {
	@Override
	public void print() {
		System.out.println("B");
	}
}

class C extends B {
	@Override
	public void print() {
		System.out.println("C");
	}
}