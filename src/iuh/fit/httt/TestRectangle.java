package iuh.fit.httt;

import java.util.Scanner;

public class TestRectangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("--- 1. Tạo hình chữ nhật với kích thước hợp lệ");
            System.out.print("Nhập chiều dài: ");
            double validLength = scanner.nextDouble();
            System.out.print("Nhập chiều rộng: ");
            double validWidth = scanner.nextDouble();
            
            Rectangle rect = new Rectangle(validLength, validWidth);
            System.out.println("Tạo thành công: " + rect.toString());
            System.out.println("Chu vi: " + rect.getPerimeter());
            System.out.println("Diện tích: " + rect.getArea());
            
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

        System.out.println("\n-------------------------------------------------");
        
       
        try {
            System.out.println("--- 2. Thử tạo hình chữ nhật với kích thước không hợp lệ (ví dụ: -5) ---");
            Rectangle invalidRect = new Rectangle(-5.0, 10.0);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

        System.out.println("\n-------------------------------------------------");

        try {
            System.out.println("--- 3. Thử cập nhật kích thước không hợp lệ bằng setter ---");
            Rectangle rectToUpdate = new Rectangle(20.0, 10.0);
            System.out.println("Hình chữ nhật ban đầu: " + rectToUpdate.toString());
            
            rectToUpdate.setWidth(-3.0);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi khi cập nhật: " + e.getMessage());
        }
        
        scanner.close();
    }
}

