package rpc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import entity.Item;
import entity.Item.ItemBuilder;

public class RpcHelperTest {

	@Test
	public void testGetJSONArray() throws JSONException {
		Set<String> category = new HashSet<String>();
		category.add("category one");
		ItemBuilder oneBuilder = new ItemBuilder();
		oneBuilder.setItemId("one");
		oneBuilder.setRating(5.0);
		oneBuilder.setCategories(category);
		Item one = oneBuilder.build();
		
		ItemBuilder secondBuilder = new ItemBuilder();
		secondBuilder.setItemId("one");
		secondBuilder.setRating(5.0);
		secondBuilder.setCategories(category);
		Item two = secondBuilder.build();
		
		List<Item> listItem = new ArrayList<Item>();
		listItem.add(one);
		listItem.add(two);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(one.toJSONObject());
		jsonArray.put(two.toJSONObject());
		
		JSONAssert.assertEquals(jsonArray, RpcHelper.getJSONArray(listItem), true);
		
		Item empty = new ItemBuilder().build();
		jsonArray.put(empty.toJSONObject());
		listItem.add(empty);
		JSONAssert.assertEquals(jsonArray, RpcHelper.getJSONArray(listItem), true);
	}
}

