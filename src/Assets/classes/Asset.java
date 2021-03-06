package classes;

public abstract class Asset {

    private String assetName;
    private String assetTag;

    // Default constructor
    public Asset(){
        this.assetName = "";
        this.assetTag = "";
    }

    // Parameterized constructor to be used for loading investments.
    public Asset(String assetName, String symbol){
        this.assetName = assetName;
        this.assetTag = symbol;
    }

    // Setter and Getter methods.
    public String getAssetName(){ return this.assetName; }
    public String getAssetTag(){ return this.assetTag;}

    abstract public int Invest(int amount);


}
