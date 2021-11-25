package sample;

import java.io.Serializable;

public class UserAccount implements Serializable {
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private int pit;

    public UserAccount(String userName, String email,
                       String firstName, String lastName, int pit) {
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pit = pit;
    }

    public UserAccount() {
        this.userName = "";
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.pit = 0;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPit() {
        return pit;
    }

    public void setPit(int pit) {
        this.pit = pit;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pit=" + pit +
                '}';
    }
}
