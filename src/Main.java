import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static Integer result;

    static void sum(LinkedList<Integer> ourInput) {
        result = 0;
        for (Integer i : ourInput) {
            result += i;
        }
    }

    static void multiplication(LinkedList<Integer> ourInput) {
        result = 1;
        for (Integer i : ourInput) {
            result *= i;
        }
    }

    static void multAndSum(LinkedList<Integer> ourInput) {
        if (ourInput.size() < 3) {
            System.out.println("Insufficient arguments in input");
            System.exit(1);
        } else {
            result = (ourInput.get(0) * ourInput.get(1)) + ourInput.get(2);
        }
    }


    public static void main(String[] args) {
        boolean isConsole = false;
        String inputFileName = "";
        String outputFileName = "";
        String command = "";

        //проверяем, как нам предлагают работать: с файлом, или с консолью?
        if (args[0].startsWith("-")) {
            isConsole = true;
        } else {
            if (args.length < 2) {
                System.out.println("It's seems you forgot something");
                System.exit(1);
            } else {
                inputFileName = args[0];
                outputFileName = args[1];
            }
        }

        //считываем данные в список
        LinkedList<Integer> ourInput = new LinkedList<>();
        try {
            Scanner in;
            if (isConsole) {
                in = new Scanner(System.in);
            } else {
                in = new Scanner(new File(inputFileName));
            }
            String[] splitted = in.nextLine().split(" ");
            if (splitted.length < 3) {
                System.out.println("Insufficient arguments in input file");
                System.exit(1);
            } else {
                command = splitted[0];
                for (int i = 1; i < splitted.length; i++) {
                    ourInput.add(Integer.parseInt(splitted[i]));
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot read file");
            System.exit(1);
        }


        //вычисляем ответ
        switch (command) {
            case "add":
                sum(ourInput);
                break;
            case "mul":
                multiplication(ourInput);
                break;
            case "muladd":
                multAndSum(ourInput);
                break;
            default:
                System.out.println("Sorry, this command is not from my list of orders");
                System.exit(1);
        }

        //выдаём ответ
        if (isConsole) {
            System.out.println(result);
        } else {
            try {
                FileWriter writer = new FileWriter(outputFileName, false);
                writer.append(result.toString());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Cannot write file");
                System.exit(1);
            }
        }
    }
}
