package az.turing.bookingspring.controller;

import az.turing.bookingspring.dto.PassengerDto;
import az.turing.bookingspring.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebPassengerController {
    private final PassengerService passengerService;

    @Autowired
    public WebPassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerPassenger(@RequestParam String name, @RequestParam String login,
                                    @RequestParam String password, Model model) {
        PassengerDto passengerDto = PassengerDto.builder()
                .name(name)
                .login(login)
                .password(password)
                .build();
        try {
            passengerService.registerPassenger(passengerDto);
            model.addAttribute("message", "Registration successful!");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPassenger(@RequestParam String login, @RequestParam String password, Model model) {
        try {
            PassengerDto passengerDto = passengerService.loginPassenger(login, password);
            model.addAttribute("passenger", passengerDto);
            return "redirect:/console";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "login";
    }
}
