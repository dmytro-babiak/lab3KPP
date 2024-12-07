package models;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String subject;
    private String text;
    private Date date;  // Поле дата не серіалізується в YAML

    public Message() {
    }

    public Message(String subject, String text, Date date) {
        this.subject = subject;
        this.text = text;
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Реалізація методу getTopic()
    public String getTopic() {
        return subject;
    }
}
