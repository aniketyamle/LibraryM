package Package;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Add_Books {
    @FXML
    private TextField bookName;
    @FXML
    private TextField isbn;
    @FXML
    private TextField author;
    @FXML
    private TextField subject;
    @FXML
    private Label label;


    Connection con = DB.getConnection();

    public void addBook(){
        try {

            String string = isbn.getText();
            int isbn1 = Integer.parseInt(string);
            PreparedStatement ps = con.prepareStatement("insert into books(isbn,name,author,subject,status) value (?,?,?,?,'not issued')");
            ps.setInt(1,isbn1);
            ps.setString(2,bookName.getText());
            ps.setString(3,author.getText());
            ps.setString(4,subject.getText());
            ps.executeUpdate();

            label.setText("Book added successfully");
            label.setTextFill(Color.GREEN);

        } catch (SQLException e) {
            label.setText("Error !! Please Try Again");
            label.setTextFill(Color.RED);
            e.printStackTrace();
        }
    }
}
