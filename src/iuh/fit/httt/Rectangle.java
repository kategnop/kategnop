package iuh.fit.httt;

public class Rectangle {
    private double length;
    private double width;
    public Rectangle() {
       
    }

    public Rectangle(double length, double width) {
        if (length <= 0 || width <= 0) {
            throw new IllegalArgumentException("Length and width must be greater than 0");
        }
        this.length = length;
        this.width = width;
    }
    public double getLength() {
        return length;
    }
    public double getWidth() {
        return width;
    }
    public void setLength(double length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        this.length = length;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be greater than 0");
        }
        this.width = width;
    }
    public double getArea() {
        return length * width;
    }

    public double getPerimeter() {
        return 2 * (length + width);
    }
}

   

