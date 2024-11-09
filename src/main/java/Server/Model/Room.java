package Server.Model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String owner;
    private String roomId;
    private List<String> players;
    private boolean isActive;

    public Room(String roomId, String owner) {
        this.owner = owner;
        this.roomId = roomId;
        this.players = new ArrayList<>();
        this.players.add(owner);
        this.isActive = false;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void newRoomOwner() {
        if (players.size() == 1) {
            setOwner(players.get(0));
        }
        if (players.isEmpty()) {
            this.isActive = false;
        }
    }

    public String getRoomId() {
        return roomId;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean addPlayer(String playerId) {
        if (players.size() < 4) {
            players.add(playerId);
            return true;
        }
        return false;
    }

    public boolean removePlayer(String playerId) {
        return players.remove(playerId);
    }

    public void startGame() {
        if (players.size() >= 2) {
            isActive = true;
            System.out.println("Game started in room: " + roomId);

            return;
        }
        System.out.println("Not enough players to start the game.");
    }

    public void endGame() {
        isActive = false;
        System.out.println("Game ended in room: " + roomId);
    }

    public void displayRoomDetails() {
        System.out.println("Room ID: " + roomId);
        System.out.println("Active: " + isActive);
        System.out.println("Players: " + players);
    }
//    public static void main(String[] args) {
//        Room room = new Room("12345");
//        room.addPlayer("Player1");
//        room.addPlayer("Player2");
//        room.displayRoomDetails();
//        room.startGame();
//        room.displayRoomDetails();
//        room.endGame();
//        room.displayRoomDetails();
//    }
}
