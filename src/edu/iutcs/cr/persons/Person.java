package edu.iutcs.cr.persons;

import java.io.Serializable;
import java.util.Objects;
import edu.iutcs.cr.util.InputHelper;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String id;
    private String email;

    public Person() {
        promptName();
        promptId();
        promptEmail();
    }

    public Person(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    /** Prompts the user and assigns the name. */
    public void promptName() {
        setName(InputHelper.readNonBlank("Enter name: ", "Name is mandatory!"));
    }

    /** Pure setter — assigns name without any I/O. */
    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    /** Prompts the user and assigns the id. */
    public void promptId() {
        setId(InputHelper.readNonBlank("Enter id: ", "Id is mandatory!"));
    }

    /** Pure setter — assigns id without any I/O. */
    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    /** Prompts the user and assigns the email. */
    public void promptEmail() {
        setEmail(InputHelper.readNonBlank("Enter email: ", "Email is mandatory!"));
    }

    /** Pure setter — assigns email without any I/O. */
    public void setEmail(String email) {
        this.email = email;
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
