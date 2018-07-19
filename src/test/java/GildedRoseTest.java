import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}

//	* All items have a SellIn value which denotes the number of days we have to sell the item

	// * All items have a Quality value which denotes how valuable the item is
//	* At the end of each day our system lowers both values for every item
//	* The Quality of an item is never negative
	@Test
	public void normalItemAfterSellDate() {
		checkUpdateQuality("abc", 0, 0, 0);
	}
	

	@Test
	public void normalItemAfterSellDate1() {
		checkUpdateQuality("abc", 1, 10, 9);
	}

	@Test
	public void normalItemAfterSellDate2() {
		checkUpdateQuality("abc", 0, 10, 8);
	}

	@Test
	public void normalItemAfterSellDate3() {
		checkUpdateQuality("abc", -1, 10, 8);
	}

//	Pretty simple, right? Well this is where it gets interesting:
//
//	* Once the sell by date has passed, Quality degrades twice as fast

//	* The Quality of an item is never negative
//	* "Aged Brie" actually increases in Quality the older it gets
	@Test
	public void whenAgedBrieAgesThenItIncreasesInQualityTwiceAsFast() {
		checkUpdateQuality(GildedRose.AGED_BRIE, 0, 0, 2);
	}

	@Test
	public void whenAgedBrieAgesThenItIncreasesInQuality() {
		checkUpdateQuality(GildedRose.AGED_BRIE, 2, 0, 1);
	}
	
	

//	* The Quality of an item is never more than 50
//	* "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
//	* "Backstage passes", like aged brie, increases in Quality as it's SellIn value approaches; Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but Quality drops to 0 after the concert
//
//	We have recently signed a supplier of conjured items. This requires an update to our system:
//
//	* "Conjured" items degrade in Quality twice as fast as normal items
	@Test
	public void conjuredItemBeforeSellDate() {
		checkUpdateQuality(GildedRose.CONJURED_MANA_CAKE, 1, 2, 0);
	}
	
	@Test
	public void conjuredItemAfterSellDate1() {
		checkUpdateQuality(GildedRose.CONJURED_MANA_CAKE, 1, 10, 8);
	}
	
	@Test
	public void conjuredItemAfterSellDate2() {
		checkUpdateQuality(GildedRose.CONJURED_MANA_CAKE, 0, 10, 6);
	}
	
	@Test
	public void conjuredItemAfterSellDate3() {
		checkUpdateQuality(GildedRose.CONJURED_MANA_CAKE, -1, 10, 6);
	}
	
	@Test
	public void conjuredItemQualityDoesntDropBelowZero() {
		checkUpdateQuality(GildedRose.CONJURED_MANA_CAKE, 1, 0, 0);
	}
	
	public void checkUpdateQuality(String name, int sellIn, int initialQuality, int expectedQuality) {
		Item item = new Item(name, sellIn, initialQuality);
		GildedRose.updateQuality(item);
		assertEquals("quality", expectedQuality, item.quality);
		assertEquals("sellin", sellIn - 1, item.sellIn);
	}

}
