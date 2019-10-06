package tech.artos.controller;

import tech.artos.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class SignInController {

    @GetMapping(path = "/")
    public String login(@AuthenticationPrincipal User user) {
        if (Objects.nonNull(user) && user.getRole().equals("ROLE_ADMIN")) {
            return "redirect:/admin/all_tasks";
        } else {
            return "redirect:/login";
        }
    }
}
