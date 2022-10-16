package com.example.mytable;
import java.time.LocalDate;
import java.util.regex.*;

public class User {
    private String email;
    private String password;
    private LocalDate creationDate;
    private boolean loginStatus;

    public User() { //
        loginStatus = false;
        creationDate = LocalDate.now();
    }

    public boolean verifyLogin() {
        return(loginStatus);
    }

    public boolean userLogin() { // allows user to login
        return true; // not implemented yet
    }

    public String getEmail() { // returns user Email
        return(email);
    }

    public String getPassword() { // returns user Password
        return(password);
    }

    public boolean setEmail(String newEmail) { // sets user Email
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"; // regex for email validation from https://howtodoinjava.com/java/regex/java-regex-validate-email-address/

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(newEmail);

        if(matcher.matches()) { // returns true if email is valid, false if not
            email = newEmail;
            return(true);
        } else {
            return(false);
        }


    }

    public boolean setPassword(String newPassword) { // sets user Email
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"; // regex for password validation from https://mkyong.com/regular-expressions/how-to-validate-password-with-regular-expression/

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(newPassword);

        if(matcher.matches()) { // returns true if password is valid, false if not
            password = newPassword;
            return(true);
        } else {
            return(false);
        }
    }

    public LocalDate getCreationDate() { // gets user creationDate
        return(creationDate);
    }

    public boolean setCreationDate(LocalDate newCreationDate) { // sets user creationDate
        creationDate = newCreationDate;
        return true;
    }

}
