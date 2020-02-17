package unitTests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import classes.Asset;
import classes.StableAsset;
import classes.Stock;

class AssetTest {

	@Test
	void test() {
		Asset asset1 = new StableAsset("Cash", "CASH", 0);	
		Stock asset2 = new Stock("Apple", "APPL", 0.232066, 0.009271, 0.451528);
		Asset asset3 = new Stock("Alibaba", "BABA", 0.1144187803, 0.2845702889); 
		
		assertEquals("Cash", asset1.getAssetName());
		assertEquals("BABA", asset3.getAssetTag());
		
		Stock stock = new Stock("Apple", "APPL",0.232066, 0.009271, 0.451528 );
		
		
		assertEquals(asset2.getAssetName(), stock.getAssetName());
		assertEquals(asset2.getAssetTag(), stock.getAssetTag());
		assertEquals(asset2.get5YrRate(),stock.get5YrRate());
		assertEquals(asset2.get1YrRate(), stock.get1YrRate());
		assertEquals(asset2.get90DaysRate(), stock.get90DaysRate());
	}

}
