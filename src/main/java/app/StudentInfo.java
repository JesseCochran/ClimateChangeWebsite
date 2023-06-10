package app;

import java.util.ArrayList;

public class StudentInfo {
    private String studentNumber;
    private String fName;
    private String lName;
    private String email;
  

    public StudentInfo(String studentNumber, String fName, String lName, String email) {
        this.studentNumber = studentNumber;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
    
    public String getFname() {
        return fName;
    }

    public void setFname(String fName) {
        this.fName = fName;
    }

    public String getLname() {
        return lName;
    }

    public void setLname(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String quote) {
        this.email = email;
    }

   
   
}


