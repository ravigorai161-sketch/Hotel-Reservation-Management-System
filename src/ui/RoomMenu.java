package ui;

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
            System.out.println("6. Back");
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
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    private void addRoom() {

        Room room = new Room();

        System.out.print("Room Number: ");
        room.setRoomNumber(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Room Type: ");
        room.setRoomType(scanner.nextLine());

        System.out.print("Price: ");
        room.setPrice(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("Status (Available/Occupied): ");
        room.setStatus(scanner.nextLine());

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

        System.out.print("New Room Type: ");
        room.setRoomType(scanner.nextLine());

        System.out.print("New Price: ");
        room.setPrice(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("New Status: ");
        room.setStatus(scanner.nextLine());

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
}
