package Package;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.text.TabExpander;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class Check_transaction implements Initializable {

    @FXML
    private TableView<Transactions> tableView;
    @FXML
    private TableColumn<Transactions,Integer> id;
    @FXML
    private TableColumn<Transactions,String> firstname;
    @FXML
    private TableColumn<Transactions,String> lastname;
    @FXML
    private TableColumn<Transactions,String> book;
    @FXML
    private TableColumn<Transactions, LocalDate> issuedate;
    @FXML
    private TableColumn<Transactions, LocalDate> returndate;
    @FXML
    private TableColumn<Transactions,Integer> fine;
    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setCellValueFactory(
                new PropertyValueFactory<Transactions,Integer>("id"));
        firstname.setCellValueFactory(
                new PropertyValueFactory<Transactions,String>("firstname"));
        lastname.setCellValueFactory(
                new PropertyValueFactory<Transactions,String>("lastname"));
        book.setCellValueFactory(
                new PropertyValueFactory<Transactions,String>("book"));
        issuedate.setCellValueFactory(
                new PropertyValueFactory<Transactions, LocalDate>("issuedate"));
        returndate.setCellValueFactory(
                new PropertyValueFactory<Transactions, LocalDate>("returndate"));
        fine.setCellValueFactory(
                new PropertyValueFactory<Transactions,Integer>("fine"));
        tableView.setItems(getTransactions());

    }


    public ObservableList<Transactions> getTransactions(){
        ObservableList<Transactions> transactions = FXCollections.observableArrayList();

        try{
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from Library.transactions");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                transactions.add(new Transactions(rs.getInt("fine"),rs.getInt("transaction_id"),rs.getString("firstname"),rs.getString("secondname")
                        ,rs.getString("book"),rs.getDate("issuedate"),rs.getDate("returndate")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return transactions;
    }

    @FXML
    public void showDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(pane.getScene().getWindow());
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Dialog_transactions.fxml"));
            dialog.getDialogPane().setContent(root);

        }catch (IOException e){
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
