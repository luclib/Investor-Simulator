package classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InvestorSimulator {


	final private static int TOTAL_ASSETS = 18;

	public static void main(String[] args) throws Exception{
		
		// To display input to user
		FileInputStream infile = new FileInputStream("assetData.csv");
		Scanner scnr = new Scanner(infile);
		
		Scanner keyboard = new Scanner(System.in);
		
		Boolean userDone = false;
		int amount = 0;
		String assetSymbol = "";
		int expectedReturn = 0;
		
		int totalAmount =0;
		int totalExpected = 0;


		ArrayList<Asset> assets = loadAssets(scnr);
		
		System.out.println("Available assets for investment");
		System.out.println("-------------------------------");

		for(Asset a: assets){
			System.out.println("\t" + a.getAssetName() + " (" + a.getAssetTag() +")");
		}

		System.out.println("\n");
		
		// To record user output.
		FileOutputStream outFile = new FileOutputStream("portfolio.txt");
		PrintWriter outStream = new PrintWriter(outFile);
		
		// Begin outputting data onto portfolio.txt
		outStream.println("+--------------+-----------------+--------------------+");
		outStream.println("| ASSET SYMBOL | AMOUNT INVESTED | VALUE IN TEN YEARS |");
		outStream.println("+==============+=================+====================+");
		do {
			
			// Create asset instance to represent asset chosen by user.
			Asset chosenAsset = null;
			try {				
				// Prompt user for investment amount
			System.out.print("Enter the amount to invest in dollars: ");
				amount = keyboard.nextInt();
				if(amount > 0) {
					Boolean assetFound = false;
					System.out.print("Enter the asset symbol to invest in: ");
					do{
						try {
							assetSymbol = keyboard.next();
							
							for(Asset a: assets) {
								if(a.getAssetTag().equals(assetSymbol)) {
									assetSymbol = a.getAssetTag();
									assetFound = true;
									chosenAsset = a;
									break;
								}
							}
							
							expectedReturn = chosenAsset.Invest(amount);
							
							// increment the total amount invested and the total value
							// in ten years.
							totalAmount += amount;
							totalExpected += expectedReturn;
							
						
							outStream.printf("| %-12s | %-15d | %-18d |\n", chosenAsset.getAssetTag(), amount, expectedReturn );	
							
							
						}
						catch(RuntimeException e){
							System.out.println("Invalid symbol, please check list above");
						}
					}while(!assetFound);
					

				}
				else if(amount < 0){
					// Print total
					outStream.println("+--------------+-----------------+--------------------+");
					outStream.printf("| %-13s | %-15d | %-18d |\n" , "TOTAL", totalAmount, totalExpected );
					outStream.println("+--------------+-----------------+--------------------+");
					
					// Close the Printwriter.
					outStream.flush();
					outStream.close();
					keyboard.close();
					scnr.close();
					
					userDone = true;
					
					// Display message.
					System.out.println("Exiting program; investment data saved to portfolio.text.");

				}
			}catch(InputMismatchException e) {
				System.out.print("\nMust enter integers only.");
				break;
			}
		}while(!userDone);
	}
				
			
				
	// The load investments takes the asset information stored in assetData.cscv
	// and stores them in an array that is returned to the user.
	public static ArrayLisft<Asset> loadAssets(Scanner scnr) {

		
		scnr.useDelimiter(",");
			
		// Potential investments
		ArrayList<Asset> assets = new ArrayList<Asset>();
		
		// Variables for stable assets.
		String assetTag;
		String assetName;
		double rateOfReturn;	

		String commaHolder = "";

		// Variables for stocks.
		double return5Yrs;
		double return1Yr;
		double return90Days;	
		
		for(int i = 0 ; i < TOTAL_ASSETS; i++) {
			assetTag = scnr.next();
			assetName = scnr.next();	

			// Now check to see what type of asset it is by using the hasNextDouble()
			if(scnr.hasNextDouble()) {
				return5Yrs = Double.parseDouble(scnr.next());
				return1Yr = Double.parseDouble(scnr.next());
				
				commaHolder = scnr.nextLine();
					// Check for a comma
					if(commaHolder.contains(","));{
						// remove the comma
						commaHolder = commaHolder.replace(",", "");
					}

					return90Days = Double.parseDouble(commaHolder);
					
					// Create a stock item and add it to the array
					Stock stock = new Stock(assetName, assetTag, return5Yrs, return1Yr, return90Days);
					assets.add(stock);
				}
				else if (scnr.hasNext()) {
					commaHolder = scnr.nextLine();
					commaHolder = commaHolder.replaceFirst(",", "");

					// Create a new scanner object
					Scanner wordScnr = new Scanner(commaHolder);
					wordScnr.useDelimiter(",");
					
					commaHolder = wordScnr.next();
					
					// Now check to see if the string still contains a ","...
					if(commaHolder.contains(",")){
						// If so, then the two remaining elements will correspond to the two return rates
						// of our second stock constructor.
						String[] rates = commaHolder.split(",");

						return1Yr = Double.parseDouble(rates[0]);
						return90Days = Double.parseDouble(rates[1]);

						// Create a new stock object
						Stock stock = new Stock(assetName, assetTag, return1Yr, return90Days);
						// Add stock to assets list.
						assets.add(stock);	
					}
					else{
						// Check to see if the remainder of the string can be converted to a double.
						if(InvestorSimulator.tryParseDouble(commaHolder)){
							// If so, then it is an asset.
							rateOfReturn = Double.parseDouble(commaHolder);

							StableAsset asset = new StableAsset(assetName, assetTag, rateOfReturn);
							assets.add(asset);
						}
					}
					wordScnr.close();
				}
			
		}
		return assets;
	}
		
		

	public static Boolean tryParseDouble(String input){
		try{
			Double.parseDouble(input);
			return true;
		}
		catch (NumberFormatException e){
			return false;
		}
	}
}
		
