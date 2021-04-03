package ua.knu.gra.data.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Setter
@Getter
public class LoginData {
    @Size(min = 6)
    private String uid;
    private String password;
}
