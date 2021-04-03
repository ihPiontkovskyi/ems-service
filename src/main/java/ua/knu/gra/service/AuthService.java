package ua.knu.gra.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.knu.gra.data.auth.LoginData;
import ua.knu.gra.data.auth.RegisterData;
import ua.knu.gra.model.UserModel;
import ua.knu.gra.model.UserRole;
import ua.knu.gra.repository.UserRepository;
import ua.knu.gra.service.common.RegisterReverseDataConverter;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegisterReverseDataConverter registerDataConverter;

    public UserRole login(LoginData loginData) {
        Optional<UserModel> user = userRepository.findUserModelByUid(loginData.getUid());
        if (user.isPresent() && passwordEncoder.matches(loginData.getPassword(), user.get().getPassword())) {
            return user.get().getRole();
        }
        return null;
    }

    public boolean register(RegisterData data) {
        if (userRepository.findUserModelByUid(data.getUid()).isPresent()) {
            return false;
        }

        userRepository.save(registerDataConverter.convert(data));
        return true;
    }
}
