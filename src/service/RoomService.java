package service;

import dao.RoomDAO;
import model.Room;

import java.util.List;

public class RoomService {

    private RoomDAO roomDAO;

    public RoomService() {
        roomDAO = new RoomDAO();
    }

    // ==========================
    // Add Room
    // ==========================
    public boolean addRoom(Room room) {

        if (room == null) {
            System.out.println("Room cannot be null.");
            return false;
        }

        if (room.getRoomNumber() <= 0) {
            System.out.println("Invalid room number.");
            return false;
        }

        if (room.getRoomType() == null || room.getRoomType().trim().isEmpty()) {
            System.out.println("Room type cannot be empty.");
            return false;
        }

        if (room.getPrice() <= 0) {
            System.out.println("Room price must be greater than 0.");
            return false;
        }

        if (room.getStatus() == null || room.getStatus().trim().isEmpty()) {
            room.setStatus("Available");
        }

        if (roomDAO.getRoomByNumber(room.getRoomNumber()) != null) {
            System.out.println("Room already exists.");
            return false;
        }

        return roomDAO.addRoom(room);
    }

    // ==========================
    // Update Room
    // ==========================
    public boolean updateRoom(Room room) {

        if (room == null) {
            return false;
        }

        if (roomDAO.getRoomByNumber(room.getRoomNumber()) == null) {
            System.out.println("Room not found.");
            return false;
        }

        return roomDAO.updateRoom(room);
    }

    // ==========================
    // Delete Room
    // ==========================
    public boolean deleteRoom(int roomNumber) {

        if (roomDAO.getRoomByNumber(roomNumber) == null) {
            System.out.println("Room not found.");
            return false;
        }

        return roomDAO.deleteRoom(roomNumber);
    }

    // ==========================
    // Get Room By Number
    // ==========================
    public Room getRoomByNumber(int roomNumber) {

        return roomDAO.getRoomByNumber(roomNumber);
    }

    // ==========================
    // View All Rooms
    // ==========================
    public List<Room> getAllRooms() {

        return roomDAO.getAllRooms();
    }

    // ==========================
    // Check Room Availability
    // ==========================
    public boolean isRoomAvailable(int roomNumber) {

        Room room = roomDAO.getRoomByNumber(roomNumber);

        if (room == null) {
            return false;
        }

        return room.getStatus().equalsIgnoreCase("Available");
    }

    // ==========================
    // Change Room Status
    // ==========================
    public boolean changeRoomStatus(int roomNumber, String status) {

        Room room = roomDAO.getRoomByNumber(roomNumber);

        if (room == null) {
            System.out.println("Room not found.");
            return false;
        }

        room.setStatus(status);

        return roomDAO.updateRoom(room);
    }

    // ==========================
    // Count Rooms
    // ==========================
    public int getRoomCount() {

        return roomDAO.getAllRooms().size();
    }

    // ==========================
    // Count Available Rooms
    // ==========================
    public int getAvailableRoomCount() {

        int count = 0;

        for (Room room : roomDAO.getAllRooms()) {

            if (room.getStatus().equalsIgnoreCase("Available")) {
                count++;
            }

        }

        return count;
    }

    // ==========================
    // Count Booked Rooms
    // ==========================
    public int getBookedRoomCount() {

        int count = 0;

        for (Room room : roomDAO.getAllRooms()) {

            if (room.getStatus().equalsIgnoreCase("Booked") ||
                    room.getStatus().equalsIgnoreCase("Occupied")) {

                count++;
            }

        }

        return count;
    }
}
