import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


/**
 * @author Alex Betschart
 * Simple login program. Allows users to create an account (with a username,password and name). By inputting text
 * into a text file. Usernames are guaranteed to be to be unique through the use of a hashmap. The passwords and names
 * are stored in plain text.
 */

public class Exercise1 {
    public static void main(String[] args) {
        //Uses username as Key and password as Value
        HashMap<String, String> userAndPass = new HashMap<>();

        //Uses username as key and full name as Value
        HashMap<String, String> userAndName = new HashMap<>();

        Scanner in = new Scanner(System.in);
        Scanner hashScanner;

        //Enters a loop to ask user to make a scanner of their file
        //Exits when file is found and scanner successfully created
        while (true) {
            System.out.println("Enter the path of your file.");
            String fileName = in.nextLine();

            try {
                hashScanner = new Scanner(new File(fileName));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Your file was not found, try again.");
            }
        }

        //Add Values and keys to their respective hashmaps.
        while (hashScanner.hasNextLine()) {
            String nextLine = hashScanner.nextLine();

            String[] inputString = nextLine.split("\t");

            String name = inputString[0];
            String username = inputString[1];
            String password = inputString[2];

            userAndPass.put(username, password);
            userAndName.put(username, name);
        }

        String inputUsername;
        String inputPassword;

        int failCount = 0;
        while (failCount < 3) {

            //Ask user to login
            System.out.printf("Login: ");
            inputUsername = in.nextLine();

            System.out.printf("Password: ");
            inputPassword = in.nextLine();
            System.out.println();

            //Verify login information and print error/success messages.
            if (userAndPass.containsKey(inputUsername) && userAndPass.get(inputUsername).equals(inputPassword)) {
                System.out.printf("Welcome %s", userAndName.get(inputUsername));
                break;

            } else {
                failCount++;
                if (failCount == 3) {
                    System.out.println("Sorry! Incorrect login. Please contact the system administrator.");
                    break;
                } else {
                    System.out.printf("Either the username or password is incorrect. You have %s more attempts", 3 - failCount);
                    if (failCount < 2) {
                        System.out.printf("s.\n");
                    } else {
                        System.out.printf(".\n");
                    }
                }
            }
        }
    }
}
