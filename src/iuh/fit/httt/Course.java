package iuh.fit.httt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Course {
    private String id;
    private String title;
    private int credit;
    private String department;

    
    public Course(String id, String title, int credit, String department) {
        if (!isValidId(id)) {
            throw new IllegalArgumentException("ID must have at least 3 characters and contain only letters or digits.");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty.");
        }
        if (credit <= 0) {
            throw new IllegalArgumentException("Credit must be greater than 0.");
        }
        this.id = id;
        this.title = title;
        this.credit = credit;
        this.department = department;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCredit() {
        return credit;
    }

    public String getDepartment() {
        return department;
    }

   
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty.");
        }
        this.title = title;
    }

    public void setCredit(int credit) {
        if (credit <= 0) {
            throw new IllegalArgumentException("Credit must be greater than 0.");
        }
        this.credit = credit;
    }
    
 
    private boolean isValidId(String id) {
        if (id == null || id.length() < 3) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }
}