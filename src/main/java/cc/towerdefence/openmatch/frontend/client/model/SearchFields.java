package cc.towerdefence.openmatch.frontend.client.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Getter
@Builder
@ToString
public class SearchFields {
    private final Map<String, Double> doubleArgs;
    private final Map<String, String> stringArgs;
    private final Set<String> tags;

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        if (this.doubleArgs != null && !this.doubleArgs.isEmpty()) {
            JsonObject doubleArgsJson = new JsonObject();
            for (Map.Entry<String, Double> entry : this.doubleArgs.entrySet()) {
                doubleArgsJson.addProperty(entry.getKey(), entry.getValue());
            }
            jsonObject.add("double_args", doubleArgsJson);
        }

        if (this.stringArgs != null && !this.stringArgs.isEmpty()) {
            JsonObject stringArgsJson = new JsonObject();
            for (Map.Entry<String, String> entry : this.stringArgs.entrySet()) {
                stringArgsJson.addProperty(entry.getKey(), entry.getValue());
            }
            jsonObject.add("string_args", stringArgsJson);
        }

        if (this.tags != null && !this.tags.isEmpty()) {
            JsonArray tagsArray = new JsonArray();
            for (String tag : this.tags)
                tagsArray.add(tag);
            jsonObject.add("tags", tagsArray);
        }

        return jsonObject;
    }

    public static SearchFields fromJson(JsonObject jsonObject) {
        SearchFieldsBuilder builder = SearchFields.builder();
        if (jsonObject.has("double_args")) {
            JsonObject doubleArgsObject = jsonObject.get("double_args").getAsJsonObject();

            Map<String, Double> doubleArgs = new HashMap<>();
            for (Map.Entry<String, JsonElement> entry : doubleArgsObject.entrySet()) {
                doubleArgs.put(entry.getKey(), entry.getValue().getAsDouble());
            }
            builder.doubleArgs(doubleArgs);
        }
        if (jsonObject.has("string_args")) {
            JsonObject stringArgsObject = jsonObject.get("string_args").getAsJsonObject();

            Map<String, String> stringArgs = new HashMap<>();
            for (Map.Entry<String, JsonElement> entry : stringArgsObject.entrySet()) {
                stringArgs.put(entry.getKey(), entry.getValue().getAsString());
            }
            builder.stringArgs(stringArgs);
        }
        if (jsonObject.has("tags")) {
            Iterator<JsonElement> iterator = jsonObject.get("tags").getAsJsonArray().iterator();
            Set<String> tags = new HashSet<>();
            while (iterator.hasNext()) {
                JsonElement element = iterator.next();
                String tag = element.getAsString();
                tags.add(tag);
            }
            builder.tags(tags);
        }
        return builder.build();
    }
}
