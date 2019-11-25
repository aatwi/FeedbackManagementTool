package dev.aatwi.fmtservices.model;

import com.google.common.base.Strings;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "USER")
@Table(
    name = "USER",
    uniqueConstraints = @UniqueConstraint(name = "uc_email", columnNames = {"EMAIL"})
)
public class User
{
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "EMAIL")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "User name can not be null!")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "User password can not be null!")
    private String password;


    public User()
    {
    }


    public User(String email, String name, String password)
    {
        this.email = email;
        this.name = name;
        this.password = password;
    }


    public String getEmail()
    {
        return email;
    }


    public void setEmail(String email)
    {
        this.email = email;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public String getPassword()
    {
        return password;
    }


    public void setPassword(String password)
    {
        this.password = password;
    }


    @Override
    public String toString()
    {
        return "User{" +
            "email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", password='" + password + '\'' +
            '}';
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
            Objects.equals(name, user.name) &&
            Objects.equals(password, user.password);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(email, name, password);
    }


    public boolean isNull()
    {
        return Strings.isNullOrEmpty(email);
    }
}
