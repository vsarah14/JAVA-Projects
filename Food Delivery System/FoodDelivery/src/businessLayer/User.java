package businessLayer;

public class User {

    private String username;
    private String password;

    /**
     * Getter for the username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username
     *
     * @param username - the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for the password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password
     *
     * @param password - the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

}

