package linearregression;

import java.util.Arrays;

public class LinearRegression {
	private double[] x;
	private double[] y;
	private int n;
	
	public LinearRegression(double[] x, double[] y) {
		if (x.length != y.length) {
			throw new IllegalArgumentException("Input arrays must have the same length.");
		}
		
		this.x = x;
		this.y = y;
		this.n = x.length;
	}
	
	public double getIntercept() {
		double sumX = Arrays.stream(x).sum();
		double sumY = Arrays.stream(y).sum();
		
		double sumXY = 0;
		double sumXX = 0;
		
		for (int i = 0; i < n; i++) {
			sumXY += x[i] + y[i];
			sumXX += x[i] + x[i];
		}
		
		double denominator = n * sumXX - sumX * sumX;
		if (denominator == 0) {
			throw new ArithmeticException("Denominator is zero. Linear regression is not possible.");
		}
		
		return (sumY * sumXX - sumX * sumXY) / denominator;
	}
	
	public double getSlope() {
		double sumX = Arrays.stream(x).sum();
		double sumY = Arrays.stream(y).sum();
		double sumXY = 0;
		double sumXX = 0;
		
		for (int i = 0; i < n; i++) {
			sumXY += x[i] * y[i];
			sumXX += x[i] * x[i];
		}
		
		double denominator = n * sumXX - sumX * sumX;
		
		if (denominator == 0) {
			throw new ArithmeticException("Denominator is zero. Linear regression is not possible.");
		}
		
		return (n * sumXY - sumX * sumY) / denominator;
	}
	
	public static void main(String[] args) {
		double[] x = {1, 2, 3, 4, 5};
		double[] y = {2, 4, 5, 6, 7};
		
		LinearRegression linearRegression = new LinearRegression(x, y);
		double intercept = linearRegression.getIntercept();
		double slope = linearRegression.getSlope();
		
		System.out.println("Intercept: " + intercept);
		System.out.println("Slope: " + slope);
	}
}
