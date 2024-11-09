package Client.Model;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.generateRandomString;

public class Room {
    private String ID;
    private String owner;
    private List<User> players;
    private List<String> chatMessages;
    private int maxPlayers;

    public Room() {
        this.maxPlayers = 2;
        this.chatMessages = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public void joinRoom(String owner, String room_ID) {
        this.owner = owner;
        this.ID = room_ID;
        this.players.add(new User(owner, room_ID));
    }

    public void createRoom(String owner) {
        this.owner = owner;
        this.ID = generateRandomString();
    }

    public String getID() {
        return ID;
    }

    public String getOwner() {
        return owner;
    }
}
