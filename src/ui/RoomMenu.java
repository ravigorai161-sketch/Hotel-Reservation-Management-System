package ui;

import validation.Validator;
import model.Room;
import service.RoomService;

import java.util.List;
import java.util.Scanner;

public class RoomMenu {

    private final Scanner scanner;
    private final RoomService roomService;

    public RoomMenu() {
        scanner = new Scanner(System.in);
        roomService = new RoomService();
    }

    public void start() {

        while (true) {

            System.out.println("\n========== ROOM MANAGEMENT ==========");
            System.out.println("1. Add Room");
            System.out.println("2. View All Rooms");
            System.out.println("3. Search Room");
            System.out.println("4. Update Room");
            System.out.println("5. Delete Room");
            System.out.println("6. Search Rooms By Type");
            System.out.println("7. View Available Rooms");
            System.out.println("8. Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addRoom();
                    break;

                case 2:
                    viewRooms();
                    break;

                case 3:
                    searchRoom();
                    break;

                case 4:
                    updateRoom();
                    break;

                case 5:
                    deleteRoom();
                    break;

                case 6:
                    searchRoomsByType();
                    break;

                case 7:
                    viewAvailableRooms();
                    break;

                case 8:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    private void addRoom() {

        Room room = new Room();

        System.out.print("Room Number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        if (!Validator.isValidRoomNumber(roomNumber)) {
            System.out.println("Invalid Room Number.");
            return;
        }

        room.setRoomNumber(roomNumber);

        System.out.print("Room Type (Single/Double/Deluxe): ");
        String roomType = scanner.nextLine();

        if (!Validator.isValidRoomType(roomType)) {
            System.out.println("Invalid Room Type.");
            System.out.println("Allowed: Single, Double, Deluxe");
            return;
        }

        room.setRoomType(roomType);

        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        if (!Validator.isValidPrice(price)) {
            System.out.println("Price must be greater than 0.");
            return;
        }

        room.setPrice(price);

        System.out.print("Status (Available/Booked/Occupied): ");
        String status = scanner.nextLine();

        if (!Validator.isValidRoomStatus(status)) {
            System.out.println("Invalid Status.");
            return;
        }

        room.setStatus(status);

        if (roomService.addRoom(room)) {
            System.out.println("Room Added Successfully.");
        } else {
            System.out.println("Failed to Add Room.");
        }
    }

    private void viewRooms() {

        List<Room> rooms = roomService.getAllRooms();

        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        System.out.println("\n========== ROOM LIST ==========");

        for (Room room : rooms) {

            System.out.println("--------------------------------");
            System.out.println("Room ID     : " + room.getRoomId());
            System.out.println("Room Number : " + room.getRoomNumber());
            System.out.println("Room Type   : " + room.getRoomType());
            System.out.println("Price       : " + room.getPrice());
            System.out.println("Status      : " + room.getStatus());

        }
    }

    private void searchRoom() {

        System.out.print("Enter Room Number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        Room room = roomService.getRoomByNumber(roomNumber);

        if (room == null) {
            System.out.println("Room Not Found.");
            return;
        }

        System.out.println("\nRoom Details");
        System.out.println("----------------------------");
        System.out.println("Room ID     : " + room.getRoomId());
        System.out.println("Room Number : " + room.getRoomNumber());
        System.out.println("Room Type   : " + room.getRoomType());
        System.out.println("Price       : " + room.getPrice());
        System.out.println("Status      : " + room.getStatus());
    }

    private void updateRoom() {

        System.out.print("Enter Room Number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        Room room = roomService.getRoomByNumber(roomNumber);

        if (room == null) {
            System.out.println("Room Not Found.");
            return;
        }

        System.out.print("New Room Type (Single/Double/Deluxe): ");
        String roomType = scanner.nextLine();

        if (!Validator.isValidRoomType(roomType)) {
            System.out.println("Invalid Room Type.");
            return;
        }

        room.setRoomType(roomType);

        System.out.print("New Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        if (!Validator.isValidPrice(price)) {
            System.out.println("Price must be greater than 0.");
            return;
        }

        room.setPrice(price);

        System.out.print("New Status (Available/Booked/Occupied): ");
        String status = scanner.nextLine();

        if (!Validator.isValidRoomStatus(status)) {
            System.out.println("Invalid Status.");
            return;
        }

        room.setStatus(status);

        if (roomService.updateRoom(room)) {
            System.out.println("Room Updated Successfully.");
        } else {
            System.out.println("Update Failed.");
        }
    }

    private void deleteRoom() {

        System.out.print("Enter Room Number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        if (roomService.deleteRoom(roomNumber)) {
            System.out.println("Room Deleted Successfully.");
        } else {
            System.out.println("Room Not Found.");
        }

    }
    private void searchRoomsByType() {

        System.out.print("Enter Room Type (Single/Double/Deluxe): ");
        String type = scanner.nextLine();

        List<Room> rooms = roomService.getRoomsByType(type);

        if (rooms.isEmpty()) {

            System.out.println("No rooms found.");
            return;
        }

        for (Room room : rooms) {

            System.out.println("--------------------------------");
            System.out.println("Room ID     : " + room.getRoomId());
            System.out.println("Room Number : " + room.getRoomNumber());
            System.out.println("Room Type   : " + room.getRoomType());
            System.out.println("Price       : " + room.getPrice());
            System.out.println("Status      : " + room.getStatus());
        }
    }private void viewAvailableRooms() {

        List<Room> rooms = roomService.getAvailableRooms();

        if (rooms.isEmpty()) {
            System.out.println("No Available Rooms.");
            return;
        }

        System.out.println("\n========== AVAILABLE ROOMS ==========");

        for (Room room : rooms) {

            System.out.println("--------------------------------");
            System.out.println("Room ID     : " + room.getRoomId());
            System.out.println("Room Number : " + room.getRoomNumber());
            System.out.println("Room Type   : " + room.getRoomType());
            System.out.println("Price       : ₹" + room.getPrice());
            System.out.println("Status      : " + room.getStatus());
        }
    }
}
