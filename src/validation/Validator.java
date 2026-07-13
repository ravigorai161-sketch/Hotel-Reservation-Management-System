package validation;

import java.util.regex.Pattern;

public class Validator {

    // Name
    public static boolean isValidName(String name) {

        return name != null &&
                name.trim().length() >= 3;
    }

    // Age
    public static boolean isValidAge(int age) {

        return age >= 18 && age <= 120;
    }

    // Phone Number
    public static boolean isValidPhone(String phone) {

        return Pattern.matches("[6-9][0-9]{9}", phone);
    }

    // Email
    public static boolean isValidEmail(String email) {

        return Pattern.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
                email
        );
    }

    // Room Price
    public static boolean isValidPrice(double price) {

        return price > 0;
    }

    // Room Number
    public static boolean isValidRoomNumber(int roomNumber) {

        return roomNumber > 0;
    }// Room Type
    public static boolean isValidRoomType(String roomType) {

        return roomType.equalsIgnoreCase("Single")
                || roomType.equalsIgnoreCase("Double")
                || roomType.equalsIgnoreCase("Deluxe");
    }

    // Room Status
    public static boolean isValidRoomStatus(String status) {

        return status.equalsIgnoreCase("Available")
                || status.equalsIgnoreCase("Booked")
                || status.equalsIgnoreCase("Occupied");
    }
}
