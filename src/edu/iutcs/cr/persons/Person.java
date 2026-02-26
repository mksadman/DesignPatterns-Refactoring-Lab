package edu.iutcs.cr.persons;

import java.io.Serializable;
import java.util.Objects;
import edu.iutcs.cr.util.InputHelper;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Person implements Serializable {

    private String name;
    private String id;
    private String email;

    public Person() {
        setName();
        setId();
        setEmail();
    }

    public Person(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = InputHelper.readNonBlank("Enter name: ", "Name is mandatory!");
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = InputHelper.readNonBlank("Enter id: ", "Id is mandatory!");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = InputHelper.readNonBlank("Enter email: ", "Email is mandatory!");
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;

        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
