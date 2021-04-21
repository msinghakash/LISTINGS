package DatabaseAndConnectors;

public class UserHelperClass {

    String fullName, email, userName, password, date, gender, phoneNumber;

    public UserHelperClass(){}

    // The data here send to OTP_verification_screen when adding a new user.
    public UserHelperClass(String fullName, String email, String userName, String password, String date, String gender, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.date = date;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    //Putting Getter and Setter
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
