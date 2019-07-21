package Package;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Dialog_transaction implements Initializable {

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
        tableView.setItems(checkIncomplete());

    }

    public ObservableList<Transactions> checkIncomplete(){

        ObservableList<Transactions> transactions = FXCollections.observableArrayList();

        try{
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from Library.transactions");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                if (rs.getDate("returndate") == null) {
                    transactions.add(new Transactions(rs.getInt("fine"), rs.getInt("transaction_id"), rs.getString("firstname"), rs.getString("secondname")
                            , rs.getString("book"), rs.getDate("issuedate"), rs.getDate("returndate")));

                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return transactions;
    }


}
