package Package;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;

public class Create_Account {
    @FXML
    private TextField FirstName;
    @FXML
    private TextField LastName;
    @FXML
    private TextField Email;
    @FXML
    public DatePicker DateOfBirth;
    @FXML
    private TextField MobileNo;
    @FXML
    private Label label;
    public void validate(){
        Connection con = DB.getConnection();
        try{
            PreparedStatement preparedStatement = con.prepareStatement("insert into Users(firstname,secondname,email,mobileno)values(?,?,?,?)");
            preparedStatement.setString(1,FirstName.getText());
            preparedStatement.setString(2,LastName.getText());
            preparedStatement.setString(3,Email.getText());
            preparedStatement.setString(4,MobileNo.getText());

            preparedStatement.executeUpdate();
            label.setText("Account created by name : "+FirstName.getText()+" "+LastName.getText());
            label.setTextFill(Color.GREEN);

        }catch(SQLException e){
            e.printStackTrace();
        }
     }
}
