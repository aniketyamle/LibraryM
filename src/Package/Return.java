package Package;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Return {
    @FXML
    TextField Book;
    @FXML
    TextField FirstName;
    @FXML
    TextField LastName;
    @FXML
    Label label;
    public void Returnit(){
        int x = 1;
        Connection con = DB.getConnection();
        try{
            PreparedStatement book = con.prepareStatement("select book from transactions where book = ?");
            PreparedStatement firstname = con.prepareStatement("select firstname from transactions where firstname = ?");
            PreparedStatement lastname = con.prepareStatement("select secondname from transactions where secondname = ?");
            book.setString(1,Book.getText());
            firstname.setString(1,FirstName.getText());
            lastname.setString(1,LastName.getText());
            book.executeQuery();
            firstname.executeQuery();
            lastname.executeQuery();


            /*ResultSet bookResultSet = book.getResultSet();
            ResultSet firstnameResultSet = firstname.getResultSet();
            ResultSet lastnameResultSet = lastname.getResultSet();
            */
            PreparedStatement returnquery =
                    con.prepareStatement("update transactions set returndate = NOW() where firstname = ? and secondname = ? and book = ?");
            returnquery.setString(1,FirstName.getText());
            returnquery.setString(2,LastName.getText());
            returnquery.setString(3,Book.getText());
            returnquery.executeUpdate();

            PreparedStatement finePreparedStatement =
                    con.prepareStatement("select fine from transactions where firstname = ? and secondname = ? and book = ?");
                        finePreparedStatement.setString(1,FirstName.getText());
                        finePreparedStatement.setString(2,LastName.getText());
                        finePreparedStatement.setString(3,Book.getText());
                        finePreparedStatement.executeQuery();

             ResultSet finePreparedStatementResultSet = finePreparedStatement.getResultSet();


             while (finePreparedStatementResultSet.next()){
                 x = finePreparedStatementResultSet.getInt("fine");
             }


            PreparedStatement fine =
                    con.prepareStatement("insert into fine(firstname,lastname,fine) values(?,?,?)");
                    fine.setString(1,FirstName.getText());
                    fine.setString(2,LastName.getText());
                    fine.setInt(3,x);
                    fine.executeUpdate();
                    label.setText("Book "+Book.getText()+" has been successfully returned by "+ FirstName.getText()+" "+ LastName.getText());
                    label.setTextFill(Color.GREEN);


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
