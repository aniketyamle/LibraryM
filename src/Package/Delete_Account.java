package Package;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Delete_Account {
    @FXML
    TextField delete;
    @FXML
    Label label;

    public void validate(){
        Connection con = DB.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement("delete from Users where user_id = ?");
            preparedStatement.setString(1,delete.getText());
            preparedStatement.executeUpdate();

            label.setText("Account deleted successfully");
            label.setTextFill(Color.GREEN);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
