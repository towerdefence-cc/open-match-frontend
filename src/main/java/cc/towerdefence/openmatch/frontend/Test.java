package cc.towerdefence.openmatch.frontend;

import cc.towerdefence.openmatch.frontend.client.OpenMatchClient;
import cc.towerdefence.openmatch.frontend.model.TDMode;

public class Test {

    public static void main(String... args) {
        OpenMatchClient client = new OpenMatchClient();

        client.createTicket(TDMode.STANDARD).thenAccept(ticket -> {
            System.out.println("Created ticket: " + ticket);
            client.getTicket(ticket.getId()).thenAccept(ticket2 -> {
                System.out.println("Retrieved ticket: " + ticket2);
            }).join();
        }).join();
    }
}
