abstract class Shape {

    abstract double getPerimeter();

    abstract double getArea();
}

class Triangle extends Shape {
    double side1;
    double side2;
    double side3;

    Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
    @Override
    double getPerimeter() {
        return side1 + side2 + side3;
    }
    @Override
    double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }
}

class Rectangle extends Shape {
    double length;
    double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    @Override
    double getPerimeter() {
        return 2 * (length + width);
    }
    @Override
    double getArea() {
        return length * width;
    }
}

class Circle extends Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }
    @Override
    double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    @Override
    double getArea() {
        return Math.PI * radius * radius;
    }
}