package numerical;

import java.util.*;
import java.io.*;

public class Main {
	
	private static Scanner sc;
	
	public static void main(String[] args) throws FileNotFoundException {
		sc = new Scanner(new FileReader(new File("input.txt")));
		
		double a = 0, b = 1;
		
		System.out.println("Secant		: " + secant(a, b));
		System.out.println("Bisection	: " + bisection(a, b));
		System.out.println("Falseposition	: " + falseposition(a, b));
		System.out.println("Iteration 	: " + iteration(a));
		System.out.println("Newton 		: " + newton(a));
	}
	
	// Closed Methods
//	1. Bisection Method
	
	private static double bisection(double a, double b) {
		double fa = f(a);
		double fb = f(b);
		
		double c = a, fc;
		
		while ((int)(Math.abs(fa - fb) * 10000) > 0) {
			c = (a + b) / 2;
			fc = f(c);
			if (fc < 0 && fa < 0 || fc > 0 && fa > 0) {
				a = c;
				fa = fc;
			} else {
				b = c;
				fb = fc;
			}
//			System.out.println("c: " + c);
		}
		
		return c;
	}
	
//	2. Secant Method
	private static double secant(double a, double b) {
		double x0 = a;
		double x1 = b;
		double x2;
		
		do {
			x2 = (x0 * f(x1) - x1 * f(x0)) / (f(x1) - f(x0));
			x0 = x1;
			x1 = x2;
//			System.out.println(x1);
		} while ((int)(Math.abs(x0 - x1) * 10000) > 0);
		
		return x1;
	}
	
//	3. False Position
	
	private static double falseposition(double a, double b) {
		double c = 0, fa = f(a), fb = f(b), fc;
		
		while ((int)(Math.abs(fa - fb) * 10000) > 0) {
			c = (a * fb - b * fa) / (fb - fa);
			fc = f(c);
			
			if (fc < 0 && fa < 0 || fc > 0 && fa > 0) {
				fa = fc;
				a = c;
			} else {
				fb = fc;
				b = c;
			}
		}
		
		return c;
	}
	
	// Open methods
//	1. Newton's method
	
	private static double newton(double x) {
		double x_next, x_pres;
		
		do {
			x_next = x - (f(x) / d(x));
			x_pres = x;
			x = x_next;
//			System.out.println("xn: " + x_pres);
		} while ((int)(Math.abs(x_pres - x_next) * 10000) > 0);
		
		return x_next;
	}
	
//	2. Iteration Method
//	Find g(x) from f(x) where g(x) = f'(x) && 0 < g(x) < 1
	
	private static double iteration(double x) {
		double x_next, x_pres = x;
		
		do {
			x_next = fi(x);
			x_pres = x;
			x = x_next;
		} while ((int)(Math.abs(x_next - x_pres) * 10000) > 0);
		
		return x_pres;
	}
	
	private static double fi(double x) {
		return (Math.cos(x) + 1) / 3;
	}
	
	private static double f(double x) {
		double val = 3 * x - Math.cos(x) - 1;
		return val;
	}
	
	private static double d(double x) {
		double val = 3 - Math.sin(x);
		return val;
	}
	
}

