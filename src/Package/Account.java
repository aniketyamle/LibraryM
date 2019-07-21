package Package;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class Account {

    @FXML
    private GridPane pane;

    public void OnCreate(ActionEvent event){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(pane.getScene().getWindow());
        try {

            Parent root = FXMLLoader.load(getClass().getResource("Create_Account.fxml"));
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

    public void OnDelete(ActionEvent event){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(pane.getScene().getWindow());
        try {

            Parent root = FXMLLoader.load(getClass().getResource("Delete_Account.fxml"));
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


    public void onIssue(ActionEvent event){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(pane.getScene().getWindow());
        try {

            Parent root = FXMLLoader.load(getClass().getResource("Issue.fxml"));
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


    public void onReturn(ActionEvent event){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(pane.getScene().getWindow());
        try {

            Parent root = FXMLLoader.load(getClass().getResource("Return.fxml"));
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


    public void back(ActionEvent event)throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }
}
