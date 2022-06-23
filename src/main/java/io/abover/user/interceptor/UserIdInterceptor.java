package io.abover.user.interceptor;

import io.abover.user.dto.UserDto;
import io.abover.user.service.UserService;
import io.abover.user.util.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@Component
public class UserIdInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService service;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws ApiException {
        try {
            Map<String, String> pathVariables = (Map<String, String>) req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

            String userId = pathVariables.get("id");

            if (userId != null && !userId.isEmpty() && !userId.isBlank()) {
                UUID validatedUserId = UUID.fromString(userId);

                UserDto activeUser = this.service.findById(validatedUserId);

                req.setAttribute("user", activeUser.getUser());

                return true;
            }

            throw new ApiException(HttpStatus.BAD_REQUEST, "User ID cannot be empty");
        } catch (IllegalArgumentException err) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid user ID");
        } catch (ApiException err) {
            throw err;
        } catch (Exception err) {
            throw new ApiException();
        }
    }
}
