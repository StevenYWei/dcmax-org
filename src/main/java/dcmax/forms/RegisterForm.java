package dcmax.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterForm {
    @NotNull
    @Size(min = 2, max = 30, message = "Username size should be in the range [2...30]")
    private String username;

    @NotNull
    @Size(min = 1, max = 50, message = "Username size should be in the range [2...30]")
    private String password;

    @NotNull
    @Size(min = 1, max = 50)
    private String passwordConfirmation;

    @NotNull
    @Size(min = 1, max = 50)
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
