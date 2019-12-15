package dev.aatwi.fmtservices.dto;

import java.util.Objects;

public class UserDTO
{
    private String email;
    private String name;
    private String password;


    protected UserDTO(String email, String name, String password)
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


    @Override public String toString()
    {
        return "UserDTO{" +
            "  email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", password='" + password + '\'' +
            '}';
    }


    @Override public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(email, userDTO.email) &&
            Objects.equals(name, userDTO.name) &&
            Objects.equals(password, userDTO.password);
    }


    @Override public int hashCode()
    {
        return Objects.hash(email, name, password);
    }
}
