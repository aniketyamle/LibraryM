package Package;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Account {
    public void OnCreate(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Create_Account.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create Account");
            stage.setScene(new Scene(root,700,700));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void OnDelete(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Delete_Account.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Delete Account");
            stage.setScene(new Scene(root,700,700));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onIssue(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Issue.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Isssue");
            stage.setScene(new Scene(root,700,700));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onReturn(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Return.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Return");
            stage.setScene(new Scene(root,700,700));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
