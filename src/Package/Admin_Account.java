package Package;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Admin_Account {

    public void checkBooks(MouseEvent event) throws IOException {
        Parent parback = FXMLLoader.load(getClass().getResource("Check_Book.fxml"));
        Scene scene = new Scene(parback);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Book Stats");
        stage.setMaximized(true);
        stage.show();
    }

    public void checkTransactions(ActionEvent event) throws IOException{
        Parent parback = FXMLLoader.load(getClass().getResource("Check_transaction.fxml"));
        Scene scene = new Scene(parback);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Transactions");
        stage.setMaximized(true);
        stage.show();
    }


    public void back(javafx.event.ActionEvent event) throws IOException {
        Parent parback = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene scene = new Scene(parback);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
