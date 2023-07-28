package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.Gender;



@Data
@NoArgsConstructor
@AllArgsConstructor
// @Entity will create the table in the database;
@Entity
@Table(name = "tbl_user")
public class User {

    //@Id will make the column primary key;
    @Id
    // @GeneratedValue will create unique value for the tokenId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;

    @Pattern(regexp = "^.+@(?![Ii][Nn][Ss][Tt][Aa][Aa][Dd][Mm][Ii][Nn]\\.[Cc][Oo][Mm]$).+$")

    @Column(unique = true)  // every value should be unique
    private String userEmail;
    @NotBlank  // this field can not be blank
    private String userPassword;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Gender getUserGender() {
        return userGender;
    }

    public void setUserGender(Gender userGender) {
        this.userGender = userGender;
    }

    private Gender userGender;
}
