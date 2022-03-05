package cc.towerdefence.openmatch.frontend.client.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@ToString
public class Ticket {
    private static final Gson GSON = new Gson();

    private final String id;
    private final SearchFields searchFields;
    private final Instant createTime;

    protected Ticket(String id, SearchFields searchFields, Instant createTime) {
        this.id = id;
        this.searchFields = searchFields;
        this.createTime = createTime;
    }

    @Builder
    public Ticket(SearchFields searchFields) {
        this.id = null;
        this.searchFields = searchFields;
        this.createTime = null;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();

        if (this.id != null)
            jsonObject.addProperty("id", this.id);

        if (this.searchFields != null)
            jsonObject.add("search_fields", this.searchFields.toJson());

        if (this.createTime != null)
            jsonObject.addProperty("create_time", this.createTime.toString());

        return jsonObject;
    }

    public static Ticket fromJson(JsonObject jsonObject) {
        String id = jsonObject.get("id").getAsString();
        SearchFields searchFields = SearchFields.fromJson(jsonObject.get("search_fields").getAsJsonObject());
        Instant createTime = Instant.parse(jsonObject.get("create_time").getAsString());

        return new Ticket(
            id,
            searchFields,
            createTime
        );
    }
}
