package Package;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Return {
    @FXML
    TextField Book;
    @FXML
    TextField FirstName;
    @FXML
    TextField LastName;
    @FXML
    DialogPane material;
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

            PreparedStatement returnquery =
                    con.prepareStatement("update transactions set returndate = NOW() where firstname = ? and secondname = ? and book = ?");
            returnquery.setString(1,FirstName.getText());
            returnquery.setString(2,LastName.getText());
            returnquery.setString(3,Book.getText());
            returnquery.executeUpdate();

            PreparedStatement getDates = con.prepareStatement("select issuedate,returndate from transactions where firstname = ? and secondname = ? and book = ? and status ='not completed' ");
            getDates.setString(1,FirstName.getText());
            getDates.setString(2,LastName.getText());
            getDates.setString(3,Book.getText());
            getDates.executeQuery();
            ResultSet getdataResultSet = getDates.getResultSet();

            while(getdataResultSet.next()){
                Date issuedate = getdataResultSet.getDate("issuedate");
                Date returndate = getdataResultSet.getDate("returndate");
                long diff = returndate.getTime()-issuedate.getTime();
                long difference = TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);

                long days = difference - 10;
                long fineamount;
                if(days<=0){
                    fineamount=0;
                }else{
                    fineamount=days*10;
                }
                System.out.println("Days : "+difference);

                PreparedStatement updatefine = con.prepareStatement("update transactions set fine = ? where firstname = ? and secondname = ? and book = ? and status ='not completed'");
                updatefine.setLong(1,fineamount);
                updatefine.setString(2,FirstName.getText());
                updatefine.setString(3,LastName.getText());
                updatefine.setString(4,Book.getText());
                updatefine.executeUpdate();
            }
            PreparedStatement finePreparedStatement =
                    con.prepareStatement("select fine from transactions where firstname = ? and secondname = ? and book = ? and status ='not completed'");
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


            PreparedStatement status = con.prepareStatement("update transactions set status = 'completed' where firstname = ? and secondname = ? and book = ?");
            status.setString(1,FirstName.getText());
            status.setString(2,LastName.getText());
            status.setString(3,Book.getText());
            status.executeUpdate();


            PreparedStatement setIssued =
                    con.prepareStatement("update books set Library.books.status = 'not issued' where Library.books.name = ?");
            setIssued.setString(1,Book.getText());
            setIssued.executeUpdate();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(material.getScene().getWindow());
            try {

                Parent root = FXMLLoader.load(getClass().getResource("Success_Dialog.fxml"));
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

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
