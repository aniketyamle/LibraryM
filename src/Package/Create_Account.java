package Package;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
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
    @FXML
    private ComboBox combox;
    @FXML
    private PasswordField answer;
    public void validate(){
        Connection con = DB.getConnection();
        try{
            String output = combox.getSelectionModel().getSelectedItem().toString();
            PreparedStatement preparedStatement = con.prepareStatement("insert into users(firstname,secondname,email,mobileno,question,answer)values(?,?,?,?,?,?)");
            preparedStatement.setString(1,FirstName.getText());
            preparedStatement.setString(2,LastName.getText());
            preparedStatement.setString(3,Email.getText());
            preparedStatement.setString(4,MobileNo.getText());
            preparedStatement.setString(5,output);
            preparedStatement.setString(6,answer.getText());

            preparedStatement.executeUpdate();
            label.setText("Account created by name : "+FirstName.getText()+" "+LastName.getText());
            label.setTextFill(Color.GREEN);


        }catch(SQLException e){
            e.printStackTrace();
        }
     }

}
