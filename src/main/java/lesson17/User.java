package lesson17;

import java.io.Serializable;

class User implements Serializable {
    private int id;

    private String name;

    private String username;

    private String email;

    User() {
        super();
    }

    User(final int id, final String name, final String username, final String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "%s[%d] %s/%s%n",
                name,
                id,
                username,
                email
        );
    }
}
