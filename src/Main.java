import gui.LoginFrame;
import ui.LoginMenu;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("      HOTEL RESERVATION SYSTEM          ");
        System.out.println("========================================");

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new LoginFrame();


        System.out.println("========================================");
        System.out.println("    Thank You For Using Our System      ");
        System.out.println("========================================");
    }
}