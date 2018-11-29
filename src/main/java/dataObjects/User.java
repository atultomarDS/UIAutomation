package dataObjects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class User {
    public String name;
    public String email;
    public String password;
    public String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("email", email)
                .append("password", password)
                .toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (!(object instanceof User)) return false;
        User actualUser = (User) object;

        return new EqualsBuilder()
                .append(getEmail(), actualUser.getEmail())
                .append(getName(), actualUser.getName())
                .append(getPassword(), actualUser.getPassword())
                .isEquals();
    }
}
