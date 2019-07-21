package Package;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;


public class Main extends Application {

    @FXML
    private TextField Username;
    @FXML
    private TextField Password;
    @FXML
    private Label label;
    @FXML
    private GridPane pane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Library");
        primaryStage.setScene(new Scene(root,700,700));
        primaryStage.show();
        primaryStage.setMaximized(true);
    }

    public static void main(String args[]){
        launch(args);
    }

    public void onMouseClicked(ActionEvent event){

       try{
           Connection con = DB.getConnection();
           PreparedStatement Id =  con.prepareStatement("select username from librarian;");
           PreparedStatement pass = con.prepareStatement("select password from librarian;");
           ResultSet rs_id = Id.executeQuery();
           ResultSet rs_pass = pass.executeQuery();

           while(rs_id.next()&&rs_pass.next()){
               if(Username.getText().equals(rs_id.getString("username"))&&
                       Password.getText().equals(rs_pass.getString("password"))){

                   Parent parback = FXMLLoader.load(getClass().getResource("Account.fxml"));
                   Scene scene = new Scene(parback);
                   Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                   stage.setScene(scene);
                   stage.setTitle("Admin");

                   Screen screen = Screen.getPrimary();
                   Rectangle2D bounds = screen.getVisualBounds();
                   stage.setWidth(bounds.getWidth());
                   stage.setHeight(bounds.getHeight());
                   break;
               }else{
                   Dialog<ButtonType> dialog = new Dialog<>();
                   dialog.initOwner(pane.getScene().getWindow());
                   try {

                       Parent root = FXMLLoader.load(getClass().getResource("Error_Dialog.fxml"));
                       dialog.getDialogPane().setContent(root);
                   }catch (Exception e){
                       e.printStackTrace();
                   }

                   dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                   dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

                   Optional<ButtonType> result = dialog.showAndWait();

                   if(result.isPresent() && result.get()==ButtonType.OK){
                       System.out.println("pressed");
                   }else{
                       System.out.println("cancelled");
                   }
                   break;
               }
           }

       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public void forgotpassword(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(pane.getScene().getWindow());
        try {

            Parent root = FXMLLoader.load(getClass().getResource("Forgot_password.fxml"));
            dialog.getDialogPane().setContent(root);
        }catch (Exception e){
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get()==ButtonType.OK){
            System.out.println("pressed");
        }else{
            System.out.println("cancelled");
        }
    }

    public void Admin(ActionEvent event) throws IOException {
        Parent parback = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene scene = new Scene(parback);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Admin");

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        stage.setMaximized(true);
        stage.show();
    }
}
