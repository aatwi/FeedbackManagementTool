package dev.aatwi.fmtservices.user.management.dto;

public final class UserDTOBuilder {
    private String email;
    private String name;
    private String password;

    private UserDTOBuilder() {
    }

    public static UserDTOBuilder newUserDTOBuilder() {
        return new UserDTOBuilder();
    }

    public UserDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserDTOBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDTO build() {
        return new UserDTO(email, name, password);
    }
}
