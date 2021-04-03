package ua.knu.gra.service.common;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.knu.gra.data.auth.RegisterData;
import ua.knu.gra.model.UserModel;
import ua.knu.gra.model.UserRole;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterReverseDataConverter {

    private final PasswordEncoder passwordEncoder;

    public UserModel convert(RegisterData data) {
        UserModel userModel = new UserModel();
        userModel.setFirstName(data.getFirstName());
        userModel.setLastName(data.getLastName());
        userModel.setUid(data.getUid());
        userModel.setRole(UserRole.valueOf(data.getRole().toUpperCase()));
        userModel.setPassword(passwordEncoder.encode(data.getPassword()));
        return userModel;
    }
}
