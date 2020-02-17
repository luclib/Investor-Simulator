package classes;

// Wildy variable rate of return connected to the
// successes and failures of a company or companies.
public class Stock extends Asset {

	private double return5Yrs;
	private double return1Yr;
	private double return90Days;

	// Getter and Setter methods for use in unit testing.
	public double get5YrRate() 	  { return this.return5Yrs; }
	public double get1YrRate() 	  { return this.return1Yr; }
	public double get90DaysRate() { return this.return90Days; }
	
	// Default constructor.
	public Stock(){
		super();
		this.return5Yrs = 0.0;
		this.return1Yr = 0.0;
		this.return90Days = 0.0;
	}

	// Constructor for stock object with all three return types
	public Stock(String assetName, String assetTag, double return5Yrs,
				 double return1Yr, double return90Days)
	{
		super(assetName, assetTag);
		this.return5Yrs = return5Yrs;
		this.return1Yr = return1Yr;
		this.return90Days = return90Days;
	}

	// Constructor for stock object with 1-year and 90-day returns.
	public Stock(String assetName, String assetTag, double return1Yr,
	double return90Days)
	{
		super(assetName, assetTag);
		this.return5Yrs = 0.0;
		this.return1Yr = return1Yr;
		this.return90Days = return90Days;
	}
	

	@Override
	public int Invest(int amount) {

		int expectedReturn =0;
		// Determine how many return rates it has.
		if(this.get5YrRate() !=  0.0){

			double interest5Yr = Math.pow(1.0 + this.get5YrRate(), 10);
			double interest1Yr = Math.pow(1.0 + this.get1YrRate(), 10);
			double interest90Days = Math.pow(1.0 + this.get90DaysRate(), 10);

			expectedReturn = (int)(amount * ((0.6 * interest5Yr) + (0.2 * interest1Yr) + (0.2 * interest90Days)) );

		}
		else{
			double interest1Yr = Math.pow(1.0 + this.get1YrRate(), 10);
			double interest90Days = Math.pow(1.0 + this.get90DaysRate(), 10);
			
			expectedReturn = (int)(amount * ((0.6 * interest1Yr) + (0.4 * interest90Days)) );
		}
		return expectedReturn;
	}

}
