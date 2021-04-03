package ua.knu.gra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.knu.gra.data.auth.CookieData;
import ua.knu.gra.data.auth.LoginData;
import ua.knu.gra.model.UserRole;
import ua.knu.gra.repository.UserRepository;
import ua.knu.gra.service.AuthService;

import javax.servlet.http.HttpSession;

public abstract class AbstractController {

    protected static final String SESSION_ID_COOKIE_NAME = "SESSIONID";
    protected static final String ROLE_COOKIE_NAME = "ROLE";

    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected AuthService authService;
    @Autowired
    protected UserRepository repository;

    protected CookieData setDataToSession(HttpSession session, LoginData data, UserRole role) {
        String sessionId = passwordEncoder.encode(data.getUid());
        session.setAttribute(SESSION_ID_COOKIE_NAME, sessionId);
        session.setAttribute(ROLE_COOKIE_NAME, role.name());
        return new CookieData(sessionId, role.name());
    }

    protected boolean checkSessionValues(HttpSession session, String cookiesId, String role) {
        return session.getAttribute(SESSION_ID_COOKIE_NAME).equals(cookiesId)
                && session.getAttribute(ROLE_COOKIE_NAME).equals(role);
    }
}
