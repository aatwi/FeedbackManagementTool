package dev.aatwi.fmt.services.account.management.models;

public final class UserBuilder {
    String email;
    String name;
    String password;

    private UserBuilder() {
    }

    public static UserBuilder newUserBuilder() {
        return new UserBuilder();
    }

    public static User newNullUser() {
        return newUserBuilder().build();
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public User build() {
        return new User(this.email, this.name, this.password);
    }
}
