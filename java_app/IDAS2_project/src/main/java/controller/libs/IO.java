package controller.libs;

import model.Room;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public final class IO {
    private IO() {

    }

    public static List<Room> importRooms(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "windows-1250"));
        List<Room> rooms = new LinkedList<>();
        bufferedReader.lines().forEach(s -> {
            String[] line = s.split(";");
            if (line[1].equals("cisloMistnosti")) return;
            rooms.add(new Room(line[5], line[1]));
        });
        bufferedReader.close();
        return rooms;
    }
}
