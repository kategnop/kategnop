package iuh.fit.httt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;

public class CourseList {
    private int count;
    private List<Course> courses;

    
    public CourseList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Length of the array must be greater than 0.");
        }
        this.courses = new ArrayList<>(initialCapacity);
        this.count = 0;
    }

    public boolean addCourse(Course course) {
        for (Course c : courses) {
            if (c.getId().equals(course.getId())) {
                System.err.println("Lỗi: Mã khóa học đã tồn tại.");
                return false;
            }
        }
        courses.add(course);
        count++;
        return true;
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }
    
    public boolean removeCourse(String id) {
        Course courseToRemove = searchCourseById(id);
        if (courseToRemove != null) {
            courses.remove(courseToRemove);
            count--;
            return true;
        } else {
            return false;
        }
    }
    
    public Course searchCourseById(String id) {
        for (Course c : courses) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }
    
    public List<Course> searchCourseByTitle(String title) {
        List<Course> foundCourses = new ArrayList<>();
        for (Course c : courses) {
            if (c.getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundCourses.add(c);
            }
        }
        return foundCourses;
    }

    public List<Course> searchCourseByDepartment(String department) {
        List<Course> foundCourses = new ArrayList<>();
        for (Course c : courses) {
            if (c.getDepartment().equalsIgnoreCase(department)) {
                foundCourses.add(c);
            }
        }
        return foundCourses;
    }
    
    public List<Course> sortCourses() {
        List<Course> sortedList = new ArrayList<>(courses);
        Collections.sort(sortedList, Comparator.comparing(Course::getTitle));
        return sortedList;
    }
    
    public List<Course> findMaxCreditCourses() {
        if (courses.isEmpty()) {
            return new ArrayList<>();
        }
        int maxCredit = 0;
        for (Course c : courses) {
            if (c.getCredit() > maxCredit) {
                maxCredit = c.getCredit();
            }
        }
        List<Course> result = new ArrayList<>();
        for (Course c : courses) {
            if (c.getCredit() == maxCredit) {
                result.add(c);
            }
        }
        return result;
    }

    public String findDepartmentWithMostCourses() {
        if (courses.isEmpty()) {
            return null;
        }
        Map<String, Long> departmentCount = courses.stream()
                .collect(Collectors.groupingBy(Course::getDepartment, Collectors.counting()));
        
        return departmentCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    
    public boolean exist(Course course) {
        return searchCourseById(course.getId()) != null;
    }
}