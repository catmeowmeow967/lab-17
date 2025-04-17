import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите чему равен x в уравнении y=x-sin(x): ");
        double variable = in.nextDouble();
        in.nextLine();
        Calculator calculator = new Calculator(variable);
        boolean exit = true;
        while (exit){
            System.out.print("Введите что должна сделать программа (save - сохранить данные, upload - выгрузить данные, exit - закрыться): ");
            String command = in.nextLine();
            switch (command){
                case ("save"):
                    Save(calculator);
                    System.out.println("Данные сохранены");
                    break;
                case ("upload"):
                    Calculator calculator1 = Upload();
                    if (calculator1 != null){
                        calculator1.display();
                    }
                    else System.out.println("Не удалось загрузить данные");
                    break;
                case ("exit"):
                    exit = false;
                    break;
                default:
                    System.out.println("Неизвестная команда. Попробуйте снова.");
                    break;
            }
        }
    }

    private static void Save(Calculator calculator) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\USER\\IdeaProjects\\lab 17\\save.txt"), "UTF-8"))) {
            writer.write(Double.toString(calculator.getX()));
            writer.newLine();
            writer.write(Double.toString(calculator.getY()));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static Calculator Upload() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\USER\\IdeaProjects\\lab 17\\save.txt"), "UTF-8"))) {
            double x = Double.parseDouble(reader.readLine());
            double y = Double.parseDouble(reader.readLine());
            return new Calculator(x, y);
        } catch (IOException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}

class Calculator implements Serializable {
    private double x;
    private double y;

    public Calculator(double x) {
        this.x = x;
        this.y = calculate(x);
    }

    // Добавлен дополнительный конструктор для восстановления
    public Calculator(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double calculate(double x) {
        return x - Math.sin(x);
    }

    public void display() {
        System.out.printf("x = %.4f, y = x - sin(x) = %.4f%n", x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

