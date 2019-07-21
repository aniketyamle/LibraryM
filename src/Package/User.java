package Package;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
    private SimpleIntegerProperty isbn;
    private SimpleStringProperty name,author,subject,status;

    public User(int isbn, String name, String author, String subject, String status) {
        this.isbn = new SimpleIntegerProperty(isbn);
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.subject = new SimpleStringProperty(subject);
        this.status = new SimpleStringProperty(status);
    }

    public int getIsbn() {
        return isbn.get();
    }

    public void setIsbn(int isbn) {
        this.isbn.set(isbn);
    }

    public String getName() {
        return name.get();
    }


    public void setName(String name) {
        this.name.set(name);
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getSubject() {
        return subject.get();
    }


    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public String getStatus() {
        return status.get();
    }


    public void setStatus(String status) {
        this.status.set(status);
    }
}
