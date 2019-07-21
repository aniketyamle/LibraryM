package Package;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

public class Transactions {

    private SimpleIntegerProperty fine,id;
    private SimpleStringProperty firstname,lastname,book;
    private java.sql.Date issuedate,returndate;

    public Transactions(int fine, int id, String firstname, String lastname, String book, Date issuedate, Date returndate) {
        this.fine = new SimpleIntegerProperty(fine);
        this.id = new SimpleIntegerProperty(id);
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.book = new SimpleStringProperty(book);
        this.issuedate = issuedate;
        this.returndate = returndate;
    }

    public int getFine() {
        return fine.get();
    }

    public SimpleIntegerProperty fineProperty() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine.set(fine);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public SimpleStringProperty firstnameProperty() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public SimpleStringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getBook() {
        return book.get();
    }

    public SimpleStringProperty bookProperty() {
        return book;
    }

    public void setBook(String book) {
        this.book.set(book);
    }

    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issuedate) {
        this.issuedate = issuedate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }
}
