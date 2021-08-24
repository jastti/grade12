

import java.util.Scanner;

/*
 * Feb,20,2021
 * Author @Jasmine Tian
 * Assignment 1 Part 2 - Hexadecimal
 * Ms. Wong
 * ICS4U
 */
public class PokemonCard {
	// main method to control user input and output
	public static void main(String[] args) {
		// Scanner portion
		Scanner myScanner = new Scanner(System.in); // Create a Scanner object
		String nameInput, typeInput, priceInput, willingInput; // define variables to hold user inputs
		PokemonCard[] myCards = new PokemonCard[50]; // the max number of card can be 50

		int cardIndex = 0; // set the initial card index as 0.
		double dPrice = 0.0; // set the initial card price as 0.
		String strSellingMore = "N"; // set the initial selling more cards as No.
		System.out.println("Bye Bye Pokemon Cards"); // print the first line
		
		do {
			System.out.print("Please enter the name of card #" + (cardIndex + 1) + ":");
			nameInput = myScanner.nextLine(); // Read user's input
			// Only grass, fire, water, poison, fairy, psychic, ghost, dark, other are valid
			// types
			System.out.print(
					"Please enter the energy type of this Pokemon (grass, fire, water, poison, fairy, psychic, ghost, dark, other):");
			typeInput = myScanner.nextLine(); // Read user's input of type

			while (!PokemonCard.checkEnergyType(typeInput)) { // check if the input type is a valid type or not
				System.out.print("Invalid engery type! ");
				System.out.print("Please enter the energy type of this Pokemon (grass, fire, water, poison, fairy,\r\n"
						+ "psychic, ghost, dark, other):");
				typeInput = myScanner.nextLine(); // Read user's input of type
			}
			
			System.out.print("Please enter the amount for this card (with '$' sign please!):");
			priceInput = myScanner.nextLine(); // Read user input of price
			// add logic to check negative sign and non-digit characters, which is invalid
			
			while (checkValidNum(priceInput) == false) {
				System.out.print("Invalid amount! ");
				System.out.print("Please enter the amount for this card (with '$' sign please!):");
				priceInput = myScanner.nextLine(); // Read user's input of price
			}
			
			dPrice = Double.parseDouble(priceInput.substring(priceInput.indexOf('$') + 1));
			// Read user input
			myCards[cardIndex] = new PokemonCard(nameInput.trim(), typeInput.trim(), dPrice);
			// display what is in the card just input
			
			System.out.println(myCards[cardIndex].getCardName());
			System.out.println(myCards[cardIndex].getEnergyType());
			System.out.printf("$%.2f\n", myCards[cardIndex].getPrice());

			System.out.printf("Will Ms. Wong buy this $%.2f card? (y/n):", dPrice);
			// ask if Ms.Wong willing to buy
			willingInput = myScanner.nextLine();

			while (!(willingInput.equalsIgnoreCase("Y") || willingInput.equalsIgnoreCase("N"))) {
				System.out.println("Invalid input. Please enter Y or N! ");
				System.out.printf("Will Ms. Wong buy this $%.2f card? (y/n):", dPrice);
				willingInput = myScanner.nextLine();
				// check user input to see if it is either Y or N
			}
			// Read user input
			
			if (willingInput.equalsIgnoreCase("Y")) {
				myCards[cardIndex].setSold(true);
			}
			// increase cardIndex by 1
			cardIndex++;
			// sell more

			System.out.print("Are you selling anymore cards? (y/n):");
			strSellingMore = myScanner.nextLine();
			// check user input to see if it is either Y or N
			
			while (!(strSellingMore.equalsIgnoreCase("Y") || strSellingMore.equalsIgnoreCase("N"))) {
				System.out.println("Invalid input. Please enter Y or N! ");
				System.out.print("Are you selling anymore cards? (y/n):");
				strSellingMore = myScanner.nextLine();
			}
			// Read user input
			
		} while (strSellingMore.equalsIgnoreCase("Y"));
		// find out card sold, not sold, and most expensive item
		
		
		int totalNumBought = 0; // set the initial value of totalNumBought as 0.
		double totalAmountBought = 0.0; // set the initial value of totalAmountBought as 0.0.
		int totalNumNotSold = 0; // set the initial value of totalNumNotSold as 0.
		double totalAmountNotSold = 0.0; // set the initial value of totalAmountNotSold as 0.0.
		int indexMostExpensive = 0; // set the initial value of indexMostExpensive as 0.
		double amountMostExpensive = 0.0; // set the initial value of amountMostExpensive as 0.0.

		for (int i = 0; i < cardIndex; i++) {
			
			if (myCards[i].isSold() == true) {
				// Ms. Wong bought this item
				totalNumBought++;
				totalAmountBought = totalAmountBought + myCards[i].getPrice();
			} else {
				// this item is not sold
				totalNumNotSold++;
				totalAmountNotSold = totalAmountNotSold + myCards[i].getPrice();
				// check if the unsold item is most expensive or not
				
				if (myCards[i].getPrice() > amountMostExpensive) {
					amountMostExpensive = myCards[i].getPrice();
					// save the index of this most expensive card for later
					indexMostExpensive = i;
				}
			}
		}

		if (totalNumBought == 0) {
			System.out.println("Ms. Wong is not willing to buy any of my cards!");
		} else {
			System.out.printf("Ms. Wong is buying %d cards for $%.2f\n", totalNumBought, totalAmountBought);
		} // print all cards that Ms. Wong bought

		if (totalNumNotSold == 0) {
			System.out.println("You do not have cards to sell!");
			// print all cards are not sold
		} else {

			if (totalNumNotSold > 1) {
				System.out.printf("You still need to sell %d cards for $%.2f\n", totalNumNotSold, totalAmountNotSold);
			} else {
				System.out.printf("You still need to sell %d card for $%.2f\n", totalNumNotSold, totalAmountNotSold);
			}
			// print the total number and amount of unsold cards

			System.out.printf("The most expensive card you are selling is %s for $%.2f\n",
					myCards[indexMostExpensive].getCardName(), amountMostExpensive);
		} // print the most expensive card that are going to sell
	}

	// Name: checkValidNum
	// Input: string
	// Return: boolean, return true if the strign is a valid number, otherwise
	// return false.
	// description: this method is to check if a string is a valid number or not
	
	private static boolean checkValidNum(String strIn) {
		boolean bRet = true; // set the initial bRet as true
		String str = strIn.trim(); // trim the string
		boolean decimalPointExist = false; // set the initial decimalPointExist as false

		if (str.length() <= 1) {
			return false;
			// the string length should more than 1
		}
		if (str.charAt(0) != '$') {
			return false;
			// the unit of the amount of money is '$'
		}

		for (int i = 1; i < str.length(); i++) {// for loop to check one char at a time
			if (str.charAt(i) < '0' || str.charAt(i) > '9') {

				if (str.charAt(i) != '.') {// if the current char is not a digit check decimal point
					bRet = false;
					break;
				} else {
					if (decimalPointExist == false) {
						decimalPointExist = true;
					} else {
						bRet = false;
						break;
					} // you have more than one decimal point, invalid
				}
			}
		}
		return bRet;
	}

	private String cardName; // property for card name

	private String energyType; // property for energy type

	private double price; // card price

	private boolean bSold; // is sold flag

	public PokemonCard(String cardName, String type, double price) {
		this.cardName = cardName;
		this.energyType = type;
		this.price = price;
		this.bSold = false;
	}// constructor method of the card name, energy type, price, and sold flag

	public String getCardName() {
		return cardName;
	}// getter to retrieve card name

	public String getEnergyType() {
		return energyType;
	}// getter to retrieve energy type

	public double getPrice() {
		return price;
	} // getter to retrieve price

	public boolean isSold() {
		return bSold;
	} // getter to retrieve sold flag

	public void setSold(boolean bSold) {
		this.bSold = bSold;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public void setEnergyType(String energyType) {
		this.energyType = energyType;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static boolean checkEnergyType(String strType) {
		String[] cardType = { "grass", "fire", "water", "poison", "fairy", "psychic", "ghost", "dark", "other" };
		// set all valid types of the cards
		boolean bValid = false;

		for (int i = 0; i < cardType.length; i++) {
			if (strType.trim().equalsIgnoreCase(cardType[i])) {
				bValid = true;
				break;
			} // check if the card type is valid
		}
		return bValid;
	}
}
