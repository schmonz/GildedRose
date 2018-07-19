import java.util.ArrayList;
import java.util.List;

public class GildedRose {

	static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";
	static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
	static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
	static final String AGED_BRIE = "Aged Brie";
	private static List<Item> items = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("OMGHAI!");

		items = new ArrayList<Item>();
		items.add(new Item("+5 Dexterity Vest", 10, 20));
		items.add(new Item(AGED_BRIE, 2, 0));
		items.add(new Item("Elixir of the Mongoose", 5, 7));
		items.add(new Item(SULFURAS_HAND_OF_RAGNAROS, 0, 80));
		items.add(new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 20));
		items.add(new Item(CONJURED_MANA_CAKE, 3, 6));

		updateQuality();
	}

	public static void updateQuality() {
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			updateQuality(item);
		}
	}

	static void updateQuality(Item item) {
		if ((!AGED_BRIE.equals(item.getName()))
				&& !BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT.equals(item.getName())) {
			if (item.getQuality() > 0) {
				if (!SULFURAS_HAND_OF_RAGNAROS.equals(item.getName())) {
					item.setQuality(item.getQuality() - 1);
				}
			}
		} else {
			if (item.getQuality() < 50) {
				item.setQuality(item.getQuality() + 1);

				if (BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT.equals(item.getName())) {
					if (item.getSellIn() < 11) {
						if (item.getQuality() < 50) {
							item.setQuality(item.getQuality() + 1);
						}
					}

					if (item.getSellIn() < 6) {
						if (item.getQuality() < 50) {
							item.setQuality(item.getQuality() + 1);
						}
					}
				}
			}
		}

		if (!SULFURAS_HAND_OF_RAGNAROS.equals(item.getName())) {
			item.setSellIn(item.getSellIn() - 1);
		}

		if (item.getSellIn() < 0) {
			if (!AGED_BRIE.equals(item.getName())) {
				if (!BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT.equals(item.getName())) {
					if (item.getQuality() > 0) {
						if (!SULFURAS_HAND_OF_RAGNAROS.equals(item.getName())) {
							item.setQuality(item.getQuality() - 1);
						}
					}
				} else {
					item.setQuality(item.getQuality() - item.getQuality());
				}
			} else {
				if (item.getQuality() < 50) {
					item.setQuality(item.getQuality() + 1);
				}
			}
		}
	}

}