import ui.LoginMenu;

public class Main {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("      HOTEL RESERVATION SYSTEM          ");
        System.out.println("========================================");

        LoginMenu loginMenu = new LoginMenu();
        loginMenu.start();

        System.out.println("========================================");
        System.out.println("    Thank You For Using Our System      ");
        System.out.println("========================================");
    }
}