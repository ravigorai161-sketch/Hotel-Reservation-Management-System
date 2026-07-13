package dao;
import util.DBConnection;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {

    public boolean login(String username, String password) {

        String sql = "SELECT * FROM admins WHERE username=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }public boolean changePassword(String currentPassword, String newPassword) {

        String checkSql = "SELECT * FROM admins WHERE password=?";
        String updateSql = "UPDATE admins SET password=? WHERE password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkSql)) {

            checkStmt.setString(1, currentPassword);

            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Current password is incorrect.");
                return false;
            }

            try (PreparedStatement updateStmt = con.prepareStatement(updateSql)) {

                updateStmt.setString(1, newPassword);
                updateStmt.setString(2, currentPassword);

                return updateStmt.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
