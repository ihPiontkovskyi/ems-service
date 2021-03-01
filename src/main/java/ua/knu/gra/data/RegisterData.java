package ua.knu.gra.data;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class RegisterData {
    @Size(min = 6)
    private String uid;
    private String password;
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @Pattern(regexp = "(^Student$)|(^Lecturer$)")
    private String role;
}
