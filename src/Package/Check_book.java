package Package;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.SimpleStyleableStringProperty;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Check_book implements Initializable {

    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User,Integer> isbn;
    @FXML
    private TableColumn<User,String> author;
    @FXML
    private TableColumn<User,String> name;
    @FXML
    private TableColumn<User,String> status;
    @FXML
    private TableColumn<User,String> subject;
    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isbn.setCellValueFactory(
                new PropertyValueFactory<User,Integer>("isbn"));
        author.setCellValueFactory(
                new PropertyValueFactory<User,String>("author"));
        name.setCellValueFactory(
                new PropertyValueFactory<User,String>("name"));
        status.setCellValueFactory(
                new PropertyValueFactory<User,String>("status"));
        subject.setCellValueFactory(
                new PropertyValueFactory<User,String>("subject"));

        tableView.setItems(getBooks());


    }

    public ObservableList<User> getBooks(){
        ObservableList<User> book = FXCollections.observableArrayList();

        try{
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from books");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                book.add(new User(rs.getInt("isbn"),rs.getString("name"),
                        rs.getString("author"),rs.getString("subject"),rs.getString("status")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return book;
    }

    public void addBooks(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(pane.getScene().getWindow());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Add_Books.fxml"));
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


    public void back(javafx.event.ActionEvent event) throws IOException {
        Parent parback = FXMLLoader.load(getClass().getResource("Admin Account.fxml"));
        Scene scene = new Scene(parback);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}

