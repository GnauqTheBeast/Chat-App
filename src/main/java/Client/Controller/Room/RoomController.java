package Client.Controller.Room;

import Client.ClientRun;
import Client.Model.Room;
import Client.Model.User;
import Client.View.RoomView;

public class RoomController {
    public static final int MESSAGE_PARTS_LENGTH = 4;
    public RoomController() {}

    public void ownerJoinRoomHandler(String message) {
        if (!message.startsWith("NOTIFY_OWNER_JOIN_ROOM")) {
            return;
        }

        String[] messageParts = message.split("\\|");
        if (messageParts.length != MESSAGE_PARTS_LENGTH) {
            return;
        }

        String competitor_username = messageParts[1];
        String competitor_email = messageParts[2];
        ClientRun.competitor = new User(competitor_username, competitor_email);
        RoomView.setPlayerInfo(ClientRun.competitor);
    }

    public void competitorJoinRoomHandler(String message) {
        if (!message.startsWith("JOIN_ROOM")) {
            return;
        }

        String[] messageParts = message.split("\\|");
        if (messageParts.length != MESSAGE_PARTS_LENGTH) {
            return;
        }

        String competitor_username = messageParts[2];
        String room_ID = messageParts[3];
        ClientRun.competitor = new User(competitor_username, "fake email competitor");

        ClientRun.currentRoom = new Room();
        ClientRun.currentRoom.joinRoom(competitor_username, room_ID);

        ClientRun.navigateScene(ClientRun.SceneName.ROOM);
        RoomView.setPlayerInfo(ClientRun.currentUser);
        RoomView.setPlayerInfo(ClientRun.competitor);
    }

    public void outRoomHandler(String message) {

    }
}
