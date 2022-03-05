package cc.towerdefence.openmatch.frontend.client;

import cc.towerdefence.openmatch.frontend.client.model.SearchFields;
import cc.towerdefence.openmatch.frontend.client.model.Ticket;
import cc.towerdefence.openmatch.frontend.model.TDMode;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class OpenMatchClient {
    public static final @NotNull String BASE_FRONTEND_URL = "http://localhost:51504/v1/frontendservice";

    private final @NotNull HttpClient httpClient = HttpClient.newHttpClient();

    public @NotNull CompletableFuture<Ticket> createTicket(@NotNull TDMode mode) {
        JsonObject requestJson = new JsonObject();
        Ticket ticket = Ticket.builder()
            .searchFields(
                SearchFields.builder().tags(
                    Set.of(mode.getOpenMatchMode())
                ).build()
            ).build();
        requestJson.add("ticket", ticket.toJson());

        return this.httpClient.sendAsync(HttpRequest.newBuilder()
                .uri(URI.create(BASE_FRONTEND_URL + "/tickets"))
                .POST(HttpRequest.BodyPublishers.ofString(requestJson.toString())).build(), HttpResponse.BodyHandlers.ofString())
            .thenApply(response -> Ticket.fromJson(JsonParser.parseString(response.body()).getAsJsonObject()));
    }

    public @NotNull CompletableFuture<Ticket> getTicket(@NotNull String id) {
        return this.httpClient.sendAsync(HttpRequest.newBuilder()
                .uri(URI.create(BASE_FRONTEND_URL + "/tickets/" + id))
                .GET().build(), HttpResponse.BodyHandlers.ofString())
            .thenApply(response -> Ticket.fromJson(JsonParser.parseString(response.body()).getAsJsonObject()));
    }

    public CompletableFuture<HttpResponse<Void>> deleteTicket(@NotNull String id) {
        return this.httpClient.sendAsync(HttpRequest.newBuilder()
                .uri(URI.create(BASE_FRONTEND_URL + "/tickets/" + id))
                .DELETE().build(), HttpResponse.BodyHandlers.discarding());
    }
}
