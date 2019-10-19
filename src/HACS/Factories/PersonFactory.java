package HACS.Factories;

import HACS.Enums.UserType;
import HACS.Person.Person;
import HACS.Person.Student;
import HACS.Person.Teacher;

public class PersonFactory {

    public Person getPerson(UserType userType){
        Person person = null;
        switch (userType){
            case STUDENT:
                person = new Student();
                break;
            case TEACHER:
                person = new Teacher();
                break;
        }
        return person;
    }
}
