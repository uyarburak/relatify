package technology.tabula.json;

import java.lang.reflect.Type;
import java.util.List;

import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public final class TableSerializer implements JsonSerializer<Table> {

	public static final TableSerializer INSTANCE = new TableSerializer();

	private TableSerializer() {
		// singleton
	}

	@Override
	public JsonElement serialize(Table src, Type typeOfSrc, JsonSerializationContext context) {
		JsonArray data = new JsonArray();

		for (List<RectangularTextContainer> srcRow : src.getRows()) {
			if (srcRow.get(0).getText().matches("\\d+")){
				JsonArray row = new JsonArray();
				for (RectangularTextContainer textChunk : srcRow) {
					row.add(textChunk.getText().replaceAll("\\s+", " ").trim());
				}
				data.add(row);
			}
		}

		return data;
	}

}
