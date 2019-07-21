package Package;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Forgot_Password {
    @FXML
    private TextField username;
    @FXML
    private TextField answer;
    @FXML
    private Label label;

    public void check(){
        Connection con = DB.getConnection();
        {
            try {
                PreparedStatement ps = con.prepareStatement("select username,password,answer from librarian");
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    if (username.getText().equals(rs.getString("username")) && answer.getText().equals(rs.getString("answer"))) {
                        label.setText("Password is : " + rs.getString("password"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
