package HACS;

import HACS.Enums.UserType;
import HACS.Interfaces.FacadeManager;
import HACS.Person.UserInfoItem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        FacadeManager facadeManager = Facade.getFacadeManager();

        while(true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("1) Login \n2) Sign Up \n0) Exit\nEnter Choice:");

            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                break;
            }

            System.out.println("Please enter your User Name: ");
            String userName = scanner.nextLine();
            System.out.println("Please enter your Password: ");
            String password = scanner.nextLine();

            boolean loginSuccess = false;
            if(choice == 1) {
                loginSuccess = facadeManager.login(userName, password);
            }else if(choice == 2) {
                try {
                    facadeManager.createUser(new UserInfoItem(UserType.TEACHER, userName, password));
                    loginSuccess = facadeManager.login(userName, password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (loginSuccess) {
                System.out.println("You are Logged in!!");

                facadeManager.createCourseList();
                facadeManager.selectCourse();

                System.out.println("Do you want to take this course (0/1): ");
                choice = Integer.parseInt(scanner.nextLine());
                if(choice == 1) {
                    facadeManager.attachCourseToUser();
                }
                facadeManager.courseOperation();


                System.out.println("Do you want to add an Assignment (0/1): ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    System.out.println("Please enter your Assignment: ");
                    String assignment = scanner.nextLine();
                    facadeManager.addAssignment(assignment);
                }
                facadeManager.viewAssignment();

                facadeManager.submitSolution();
                facadeManager.gradeSolution();
                facadeManager.reportSolutions();

                facadeManager.remind();

                System.out.println("\n\nYou have been logged out.");
                System.out.println("To continue sign in again.\n\n");
            } else {
                System.out.println("Wrong Credentials!!!");
            }
        }
    }

}
