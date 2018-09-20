package Package;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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
        Connection con = DB.getConnection();
        try{
            PreparedStatement book = con.prepareStatement("select name from books where name = ?");
            PreparedStatement firstname = con.prepareStatement("select firstname from Users where firstname = ?");
            PreparedStatement lastname = con.prepareStatement("select secondname from Users where secondname = ?");
            book.setString(1,Book.getText());
            firstname.setString(1,FirstName.getText());
            lastname.setString(1,LastName.getText());
            book.executeQuery();
            firstname.executeQuery();
            lastname.executeQuery();

            ResultSet bookResultSet = book.getResultSet();
            ResultSet firstnameResultSet = firstname.getResultSet();
            ResultSet lastnameResultSet = lastname.getResultSet();

                PreparedStatement insert_query =
                        con.prepareStatement("insert into transactions(firstname,secondname,book,issuedate,returndate,fine)values(?,?,?,NOW(),NUll,NULL)");
                insert_query.setString(1,FirstName.getText());
                insert_query.setString(2,LastName.getText());
                insert_query.setString(3,Book.getText());

                insert_query.executeUpdate();


                label.setText("Book "+Book.getText()+" issued to "+FirstName.getText()+" "+LastName.getText());
                label.setTextFill(Color.GREEN);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
