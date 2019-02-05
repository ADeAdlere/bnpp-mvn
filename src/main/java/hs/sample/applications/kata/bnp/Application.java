package hs.sample.applications.kata.bnp;

public class Application {

	public static void main(String[] args) {
		try {
			BasketAmountCalculator calculator = new BasketAmountCalculator();
			System.out.println();
			calculator.getBasketContents();
			System.out.println();
			String formatedBasketPrice = String.format("%.2f", calculator.getBasketPrice());
			System.out.println("*** Total basket price is : " + formatedBasketPrice);
		} catch (Exception e) {
			System.out.println("Please check the initialization file");
		}
	}

}
