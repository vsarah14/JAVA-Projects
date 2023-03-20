package model;

public class Client {

    private int clientId;
    private String name;
    private String email;

    /**
     * Getter for the client id.
     *
     * @return - the client id
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Setter for the client id.
     *
     * @param clientId - the cliend id
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Getter for the name.
     *
     * @return - the name of the client
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name.
     *
     * @param name - the name of the client
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the email.
     *
     * @return - the email of the client
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for the email.
     *
     * @param email - the email of the client
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Constructor for the client.
     *
     * @param clientId - client id
     * @param name     - name
     * @param email    - email
     */
    public Client(int clientId, String name, String email) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
    }

}
