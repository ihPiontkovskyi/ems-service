package ua.knu.gra.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.knu.gra.data.auth.LoginData;
import ua.knu.gra.model.UserModel;
import ua.knu.gra.model.UserRole;
import ua.knu.gra.repository.UserRepository;
import ua.knu.gra.service.AuthService;
import ua.knu.gra.service.common.RegisterReverseDataConverter;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RegisterReverseDataConverter registerDataConverter;

    @InjectMocks
    private AuthService testInstance;

    private LoginData incorrectLoginData = new LoginData();
    private LoginData correctLoginData = new LoginData();
    private UserModel correctUsedModel = new UserModel();

    @Before
    public void setUp() {
        incorrectLoginData.setPassword("incorrectPassword");
        incorrectLoginData.setUid("correctUid");

        correctLoginData.setPassword("correctPassword");
        correctLoginData.setUid("correctUid");

        correctUsedModel.setId(1);
        correctUsedModel.setFirstName("firstName");
        correctUsedModel.setLastName("lastName");
        correctUsedModel.setRole(UserRole.LECTURER);
        correctUsedModel.setUid("correctUid");
        correctUsedModel.setPassword("correctPasswordHash");
    }

    @Test
    public void loginShouldReturnNullIfWrongCredential() {
        when(userRepository.findUserModelByUid(incorrectLoginData.getUid())).thenReturn(Optional.empty());

        UserRole role = testInstance.login(incorrectLoginData);

        Assert.assertThat(role, isNull());
    }

    @Test
    public void loginShouldReturnRoleIfCorrectCredential() {
        when(userRepository.findUserModelByUid(incorrectLoginData.getUid())).thenReturn(Optional.of(correctUsedModel));
        when(passwordEncoder.matches(correctLoginData.getPassword(), correctUsedModel.getPassword())).thenReturn(true);

        UserRole role = testInstance.login(incorrectLoginData);

        Assert.assertThat(role, equalTo(UserRole.LECTURER));
    }
}
