package Package;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Main extends Application {

    @FXML
    private TextField Username;
    @FXML
    private TextField Password;
    @FXML
    private Label label;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Library");
        primaryStage.setScene(new Scene(root,300,300));
        primaryStage.show();
    }

    public static void main(String args[]){
        launch(args);
    }

    public void onMouseClicked(){

       try{
           Connection con = DB.getConnection();
           PreparedStatement Id =  con.prepareStatement("select username from librarian;");
           PreparedStatement pass = con.prepareStatement("select password from librarian;");
           ResultSet rs_id = Id.executeQuery();
           ResultSet rs_pass = pass.executeQuery();

           while(rs_id.next()&&rs_pass.next()){
               if(Username.getText().equals(rs_id.getString("username"))&&
                       Password.getText().equals(rs_pass.getString("password"))){

                       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Account.fxml"));
                       Parent root = fxmlLoader.load();
                       Stage stage = new Stage();
                       stage.setTitle("Account");
                       stage.setScene(new Scene(root));
                       stage.show();

               }
           }
        label.setText("Welcome  "+ Username.getText()+"!");
           label.setTextFill(Color.GREEN);
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
