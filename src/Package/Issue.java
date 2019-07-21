package Package;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Issue {
    @FXML
    TextField Book;
    @FXML
    TextField FirstName;
    @FXML
    TextField LastName;
    @FXML
    Label label;

    public void Issueit(){

        try{
            Connection con = DB.getConnection();
            PreparedStatement book = con.prepareStatement("select name,status from books;");
            PreparedStatement Users = con.prepareStatement("select firstname,secondname from users;");

            ResultSet boResultSet = book.executeQuery();
            ResultSet userResultSet = Users.executeQuery();


            while(boResultSet.next()){
                if(Book.getText().equals(boResultSet.getString("name"))&&boResultSet.getString("status").equals("not issued")) {
                    while (userResultSet.next()) {
                        if (FirstName.getText().equals(userResultSet.getString("firstname"))
                                && LastName.getText().equals(userResultSet.getString("secondname"))) {
                            PreparedStatement insert_query =
                                    con.prepareStatement("insert into transactions(firstname,secondname,book,issuedate,returndate,fine,status)values(?,?,?,NOW(),NUll,NULL,'not completed')");
                            insert_query.setString(1, FirstName.getText());
                            insert_query.setString(2, LastName.getText());
                            insert_query.setString(3, Book.getText());
                            insert_query.executeUpdate();

                            PreparedStatement isbn = con.prepareStatement("select isbn from books where name = ? and status = 'not issued'");
                            isbn.setString(1, Book.getText());

                            ResultSet isbnsSet = isbn.executeQuery();

                            int i = 0;
                            while (isbnsSet.next() && i == 0) {

                                PreparedStatement isbna = con.prepareStatement("update books set status='issued' where isbn = ?");
                                isbna.setInt(1, isbnsSet.getInt("isbn"));
                                isbna.executeUpdate();


                                label.setText("Book " + Book.getText() + " issued to " + FirstName.getText() + " " + LastName.getText());
                                label.setTextFill(Color.GREEN);
                                i = 1;
                            }

                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
