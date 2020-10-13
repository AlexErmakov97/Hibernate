package utill;

import entity.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Util {

    public static void insertDataToUser(Connection connection, Statement statement, String uri) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(uri)));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                statement.execute(line);
            }
        } catch (Exception e) {
            if (e instanceof SQLException) {
                System.out.println("Connection failure.");
                e.printStackTrace();
            } else if (e instanceof FileNotFoundException) {
                System.out.println("File not found");
            } else if (e instanceof NullPointerException) {
                System.out.println("Insert is done");
            } else e.printStackTrace();
        }
    }

//    public static void viewData(ResultSet rs) {
//        try {
//            while (rs.next()) {
//                System.out.printf("%-5.5s  %-5.5s  %-5.5s  %-5.5s  %-5.5s%n",
//                        rs.getRowId("user_id"),
//                        rs.getString("first_name"),
//                        rs.getString("last_name"),
//                        rs.getString("email"),
//                        rs.getString("phone_number"));
//            }
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }
//    }

    public static void viewData(List list) {
        System.out.printf("%-5.7s  %-20.15s  %-15.15s  %-35.100s  %-100.100s%n",
                "user_id", "first_name", "last_name", "email", "phone_number");
        for (int i = 0; i < list.size(); i++) {
            User u = (User) list.get(i);
            System.out.printf("%-5.7s  %-20.15s  %-15.15s  %-40.50s  %-40.50s%n",
                    u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPhoneNumber());
        }
    }

    public static void checkConnection(Connection con) {
        if (con != null) {
            System.out.println("Connect to database");
        } else {
            System.out.println("Error");
            System.exit(0);
        }
    }
}
