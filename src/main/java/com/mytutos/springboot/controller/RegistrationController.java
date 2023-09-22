package com.mytutos.springboot.controller;

import com.mytutos.springboot.entity.User;
import com.mytutos.springboot.entity.WebUser;
import com.mytutos.springboot.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    private final UserService userService;

    //@Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/show-registration-form")
    public String showRegistrationForm(Model model) {
        model.addAttribute("webUser", new WebUser());
        return "register/registration-form";
    }


    @PostMapping("/process-registration-form")
    public String processRegistrationForm(@Valid @ModelAttribute("webUser") WebUser theWebUser,
                                          BindingResult theBindingResult,
                                          HttpSession session, Model theModel) {

        String userName = theWebUser.getUserName();
        LOGGER.info("Processing registration form for: " + userName);

        // Form Validation
        if (theBindingResult.hasErrors()) {
            return "register/registration-form";
        }

        // check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null) {
            theModel.addAttribute("webUser", new WebUser());
            theModel.addAttribute("registrationError", "User name already exists.");
            LOGGER.warning("User name already exists.");
            return "register/registration-form";
        }

        // create user account and store in the DB...
        userService.save(theWebUser);
        LOGGER.info("Successfully created user: " + userName);


        // place user in the web http session for later use
        session.setAttribute("user", theWebUser);

        return "register/registration-confirmation";
    }

}
