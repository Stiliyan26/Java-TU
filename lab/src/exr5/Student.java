package exr5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
    private static final Pattern emailRegexPattern = Pattern.compile("([a-z0-9]{3,}@tu-sofia\\.bg)");
    private static final Pattern numberPattern = Pattern.compile("[1-9]{10}");

    private String email;
    private String studentNumber;

    public Student(String email, String studentNumber) throws StudentException {
        setEmail(email);
        setStudentNumber(studentNumber);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws StudentException {
        Matcher matcher = emailRegexPattern.matcher(email);

        if (matcher.matches()) {
            this.email = email;
        } else {
            throw new StudentException("Student email does not match!");
        }
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) throws StudentException {
        Matcher matcher = numberPattern.matcher(studentNumber);

        if (matcher.matches()) {
            this.studentNumber = studentNumber;
        } else {
            throw new StudentException("Student number does not match!");
        }
    }

    public String Info() {
        StringBuilder sb = new StringBuilder();

        sb.append("My ");
        sb.append("email is ");
        sb.append(this.email);

        return  sb.toString();
    }

    public String EmailToUpperCase() {
        int index = this.email.indexOf('@');

        return email.substring(0, index).toUpperCase();
    }
}
