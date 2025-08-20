package iuh.fit.httt;

import java.util.List;
import java.util.Scanner;

public class TestCourse {
    private static final String TABLE_FORMAT = "%-15s %-40s %-15s %-15s%n";
    private static CourseList courseList;
    private static Scanner scanner = new Scanner(System.in);

    private static void initData(CourseList list) {
        System.out.println("Đang khởi tạo dữ liệu mẫu...");
        list.addCourse(new Course("IT101", "Introduction to Programming", 3, "IT"));
        list.addCourse(new Course("IT202", "Object-Oriented Programming", 4, "IT"));
        list.addCourse(new Course("MA105", "Calculus I", 3, "Math"));
        list.addCourse(new Course("MA202", "Linear Algebra", 4, "Math"));
        list.addCourse(new Course("IT305", "Database Systems", 3, "IT"));
        list.addCourse(new Course("EN101", "English for Beginners", 2, "Language"));
        System.out.println("Khởi tạo hoàn tất!");
    }

    public static void main(String[] args) {
        courseList = new CourseList(10);
        initData(courseList);

        int choice;
        do {
            showMenu();
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    showAllCourses();
                    break;
                case 3:
                    removeCourse();
                    break;
                case 4:
                    findCourseById();
                    break;
                case 5:
                    findCoursesByTitle();
                    break;
                case 6:
                    findCoursesByDepartment();
                    break;
                case 7:
                    sortCoursesByTitle();
                    break;
                case 8:
                    findCoursesWithMaxCredit();
                    break;
                case 9:
                    findDepartmentWithMostCourses();
                    break;
                case 0:
                    System.out.println("Kết thúc chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("\n--- MENU QUẢN LÝ KHÓA HỌC ---");
        System.out.println("1. Thêm một khóa học mới");
        System.out.println("2. Hiển thị tất cả khóa học");
        System.out.println("3. Xóa một khóa học theo mã");
        System.out.println("4. Tìm kiếm khóa học theo mã");
        System.out.println("5. Tìm kiếm khóa học theo tên (tương đối)");
        System.out.println("6. Tìm kiếm khóa học theo khoa");
        System.out.println("7. Sắp xếp khóa học theo tên");
        System.out.println("8. Tìm khóa học có số tín chỉ lớn nhất");
        System.out.println("9. Tìm khoa có nhiều khóa học nhất");
        System.out.println("0. Thoát chương trình");
    }

    private static void addCourse() {
        try {
            System.out.print("Nhập mã khóa học: ");
            String id = scanner.nextLine();
            System.out.print("Nhập tên khóa học: ");
            String title = scanner.nextLine();
            System.out.print("Nhập số tín chỉ: ");
            int credit = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nhập khoa phụ trách: ");
            String department = scanner.nextLine();

            Course newCourse = new Course(id, title, credit, department);
            if (courseList.addCourse(newCourse)) {
                System.out.println("Thêm khóa học thành công!");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi: Dữ liệu nhập không hợp lệ.");
            scanner.nextLine();
        }
    }

    private static void showAllCourses() {
        List<Course> courses = courseList.getCourses();
        if (courses.isEmpty()) {
            System.out.println("Danh sách rỗng.");
            return;
        }
        printCourses(courses);
    }

    private static void removeCourse() {
        System.out.print("Nhập mã khóa học cần xóa: ");
        String id = scanner.nextLine();
        if (courseList.removeCourse(id)) {
            System.out.println("Xóa thành công.");
        } else {
            System.out.println("Không tìm thấy khóa học có mã " + id + " để xóa.");
        }
    }

    private static void findCourseById() {
        System.out.print("Nhập mã khóa học cần tìm: ");
        String id = scanner.nextLine();
        Course course = courseList.searchCourseById(id);
        if (course != null) {
            System.out.println("Tìm thấy khóa học:");
            printCourses(List.of(course));
        } else {
            System.out.println("Không tìm thấy khóa học với mã: " + id);
        }
    }

    private static void findCoursesByTitle() {
        System.out.print("Nhập tên khóa học (tương đối): ");
        String title = scanner.nextLine();
        List<Course> foundCourses = courseList.searchCourseByTitle(title);
        if (foundCourses != null && !foundCourses.isEmpty()) {
            System.out.println("Kết quả tìm kiếm theo tên:");
            printCourses(foundCourses);
        } else {
            System.out.println("Không tìm thấy khóa học nào có tên tương ứng.");
        }
    }

    private static void findCoursesByDepartment() {
        System.out.print("Nhập tên khoa phụ trách: ");
        String department = scanner.nextLine();
        List<Course> foundCourses = courseList.searchCourseByDepartment(department);
        if (foundCourses != null && !foundCourses.isEmpty()) {
            System.out.println("Kết quả tìm kiếm theo khoa:");
            printCourses(foundCourses);
        } else {
            System.out.println("Không tìm thấy khóa học nào thuộc khoa này.");
        }
    }

    private static void sortCoursesByTitle() {
        List<Course> sortedList = courseList.sortCourses();
        if (sortedList.isEmpty()) {
            System.out.println("Danh sách rỗng, không thể sắp xếp.");
            return;
        }
        System.out.println("Danh sách khóa học sau khi sắp xếp theo tên:");
        printCourses(sortedList);
    }

    private static void findCoursesWithMaxCredit() {
        List<Course> maxCreditCourses = courseList.findMaxCreditCourses();
        if (maxCreditCourses != null && !maxCreditCourses.isEmpty()) {
            System.out.println("Các khóa học có số tín chỉ lớn nhất:");
            printCourses(maxCreditCourses);
        } else {
            System.out.println("Không có khóa học nào trong danh sách.");
        }
    }

    private static void findDepartmentWithMostCourses() {
        String department = courseList.findDepartmentWithMostCourses();
        if (department != null) {
            System.out.println("Khoa có nhiều khóa học nhất là: " + department);
        } else {
            System.out.println("Không có khóa học nào trong danh sách.");
        }
    }

    private static void printCourses(List<Course> courses) {
        System.out.format(TABLE_FORMAT, "Mã khóa học", "Tên khóa học", "Tín chỉ", "Khoa");
        System.out.format(TABLE_FORMAT, "-------------", "--------------------------------------", "-------------", "-------------");
        for (Course course : courses) {
            System.out.format(TABLE_FORMAT, course.getId(), course.getTitle(), course.getCredit(), course.getDepartment());
        }
    }
}

