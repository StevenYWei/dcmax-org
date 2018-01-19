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
    private String rePassword;

    @NotNull
    @Size(min = 1, max = 50)
    private String email;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public String getEmail() {
        return email;
    }

}
