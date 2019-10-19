package HACS;

import HACS.Assignment.AssignmentMenu;
import HACS.Course.Course;
import HACS.Course.CourseMenu;
import HACS.Database.DatabaseSystem;
import HACS.Enums.CourseLevelType;
import HACS.Enums.UserType;
import HACS.Interfaces.FacadeManager;
import HACS.Database.Login;
import HACS.Assignment.Assignment;
import HACS.Factories.AssignmentMenuFactory;
import HACS.Factories.PersonFactory;
import HACS.Iterators.CourseIterator;
import HACS.Iterators.Lists.ClassCourseList;
import HACS.Iterators.Lists.SolutionList;
import HACS.Iterators.SolutionIterator;
import HACS.Person.Person;
import HACS.Person.UserInfoItem;
import HACS.Interfaces.Reminder;
import HACS.Reminders.NodeVisitor;
import HACS.Reminders.ReminderVisitor;
import HACS.Solutions.Solution;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Facade implements FacadeManager, Reminder {
    private UserType userType;
    private Course selectedCourse;
    private CourseLevelType courseLevelType;
    private Person person;
    private Login login;
    private Assignment selectedAssignment;
    public List<Course> courseList = new ArrayList<>(0);

    private Facade(){
    }

    public static FacadeManager getFacadeManager() {
        return new Facade();
    }

    @Override
    public boolean login(String userName, String password) {
        login = new Login(DatabaseSystem.getInstance());

        DatabaseSystem.User user = login.verifyUser(userName, password);
        if(user == null){
            return false;
        }
        userType = user.getUserType();
        person = new PersonFactory().getPerson(userType);
        return true;
    }

    @Override
    public void addAssignment(String assignment) {
        Date dueDate = new Date(new Date().getTime() + 604800000);
        Assignment a = new Assignment(assignment, dueDate);
        if(selectedCourse != null){
            selectedCourse.setAssignment(a);
        }
        AssignmentMenuFactory assignmentMenuFactory = new AssignmentMenuFactory(a);
        AssignmentMenu assignmentMenu = assignmentMenuFactory.getAssignmentMenu(userType);
        assignmentMenu.showAssignmentMenu();
    }

    @Override
    public void createUser(UserInfoItem userInfoItem) throws Exception {
        userType = userInfoItem.getUserType();
        person = new PersonFactory().getPerson(userInfoItem.getUserType());
        DatabaseSystem.getInstance().createUser(userInfoItem);
    }

    @Override
    public void createCourseList() {
        courseList = DatabaseSystem.getInstance().getCourseList();
    }

    @Override
    public void attachCourseToUser() {
        //if user is not yet created or not logged in throw UserNotAvailableException
        if(person == null || selectedCourse == null){
            return;
        }
        person.addCourse(selectedCourse);
    }

    @Override
    public void selectCourse() {
        selectCourseFromList(courseList);
    }

    @Override
    public void courseOperation() {
        CourseMenu courseMenu = person.createCourseMenu(courseLevelType);
        courseMenu.showMenu();
    }

    @Override
    public void viewAssignment() {
        List<Assignment> assignments = selectedCourse.getAssignments();
        getSelectedAssignment(assignments);
        if (selectedAssignment != null) {
            AssignmentMenuFactory assignmentMenuFactory = new AssignmentMenuFactory(selectedAssignment);
            AssignmentMenu assignmentMenu = assignmentMenuFactory.getAssignmentMenu(userType);
            assignmentMenu.viewAssignment();
        }
    }

    @Override
    public void gradeSolution() {
        if(userType == UserType.TEACHER) {
            System.out.println("Please enter the Solution: ");
            Scanner scanner = new Scanner(System.in);
            String sol = scanner.nextLine();
            Solution solution = setSolution(sol);
            solution.setGrade(10);
        }
    }

    @Override
    public void reportSolutions() {
        if(userType == UserType.TEACHER) {
            Solution solution = getSolution();
            if (solution.isReported()) {
                print(selectedAssignment.getAssignmentText() + " is graded " + solution.getGrade());
                print("The Solution is " + solution.getSolution());
            }
        }

    }

    @Override
    public void submitSolution() {
        if(userType == UserType.STUDENT) {
            print("Do you want to submit " + selectedAssignment.getAssignmentText() + " (0/1): ");
            Scanner scanner = new Scanner(System.in);
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                selectedAssignment.submitAssignment();
                print(selectedAssignment.getAssignmentText() + " is submitted");
            }
        }
    }

    @Override
    public void remind() {
        ReminderVisitor visitor = new ReminderVisitor();
        accept(visitor);
        selectedCourse.accept(visitor);
        selectedAssignment.accept(visitor);
    }

    private void selectCourseFromList(List<Course> courseList) {
        int number = getSelectedCourse(courseList);
        if(number == 0){
            return;
        }
        if(courseList.size() >= number){
            selectedCourse = courseList.get(number-1);
            courseLevelType = selectedCourse.getCourseLevelType();
            print("Selected Course Details:");
            print("Name: " + selectedCourse.getCourseName());
            print("Level: " + courseLevelType);
        }else{
            getSelectedCourse(courseList);
        }
    }

    private void getSelectedAssignment(List<Assignment> assignments) {
        print("Select Assignment:");
        if (assignments.size() > 0) {
            for (int index = 0; index < assignments.size(); index++) {
                print( (index+1) +")" + assignments.get(index).getAssignmentText());
            }
            print("Please select any one assignment:");
            Scanner scanner = new Scanner(System.in);
            selectedAssignment = assignments.get(Integer.parseInt(scanner.nextLine()) - 1);
        } else {
            print("No Assignment Available");
        }
    }

    private int getSelectedCourse(List<Course> list) {
        ClassCourseList classCourseList = new ClassCourseList(list);
        CourseIterator iterator = new CourseIterator(classCourseList);
        print("Course List:");
        while(iterator.hasNext()) {
            int index = iterator.getCurrentIndex();
            Course course = (Course) iterator.next();
            print( (index+1) +")" + course.getCourseName());
        }
        print("Please select any one course:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private Solution setSolution(String sol) {
        Solution solution = new Solution(sol);
        ArrayList<Solution> list = new ArrayList<Solution>();
        SolutionList solutionList = new SolutionList(list);
        solutionList.list.add(solution);
        selectedAssignment.setSolutionList(solutionList);
        return solution;
    }

    private Solution getSolution() {
        Solution solution = null;
        SolutionList solutionList = selectedAssignment.getSolutionList();
        SolutionIterator iterator = new SolutionIterator(solutionList);
        while(iterator.hasNext()) {
            solution = solutionList.list.get(iterator.getCurrentIndex());
            if(solution.isGraded()) {
                solution.reportSolution();
                break;
            }
            iterator.next();
        }
        return solution;
    }
    private void print(String message){
        System.out.println(message);
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitFacade(this);
    }
}
