package features.data;

import java.io.Serializable;
import java.util.Objects;

public class UserFeature implements Serializable {
    private String email;
    private String name;
    private String password;

    public UserFeature(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserFeature{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserFeature userFeature = (UserFeature) o;
        return Objects.equals(email, userFeature.email) &&
                Objects.equals(name, userFeature.name) &&
                Objects.equals(password, userFeature.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, password);
    }
}
