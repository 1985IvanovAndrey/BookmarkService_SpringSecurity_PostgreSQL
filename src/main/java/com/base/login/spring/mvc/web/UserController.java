package com.base.login.spring.mvc.web;

import com.base.login.spring.mvc.dao.GroupDao;
import com.base.login.spring.mvc.dao.model.UserInfo;
import com.base.login.spring.mvc.dao.repository.UserRepository;
import com.base.login.spring.mvc.service.SecurityService;
import com.base.login.spring.mvc.service.UserService;
import com.base.login.spring.mvc.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupDao groupDao;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new UserInfo());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") UserInfo userInfoForm, BindingResult bindingResult, Model model) {
//        userValidator.validate(userInfoForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
        //userInfoForm.getUsername().toLowerCase();
        int count = 0;
        if (userInfoForm.getUsername().length() < 6 || userInfoForm.getUsername().length() > 32) {
            model.addAttribute("email", "Please use between 6 and 32 characters");
            count++;
        }
        if (userInfoForm.getPassword() == "") {
            model.addAttribute("password", "Try one with at least 8 characters");
            count++;
        }
        if (!userInfoForm.getPasswordConfirm().equals(userInfoForm.getPassword())) {
            model.addAttribute("password_confirm", "These passwords don't match");
            count++;
        }
        if (userService.findByUsername(userInfoForm.getUsername().toLowerCase()) != null) {
            model.addAttribute("duplicate", "Someone already has that email");
            count++;
        }
        if (count > 0) {
            return "registration";

        } else {
            String str = userInfoForm.getUsername().toLowerCase();
            userInfoForm.setUsername(str);
            userService.save(userInfoForm);
            securityService.autologin(userInfoForm.getUsername(), userInfoForm.getPasswordConfirm());
            return "redirect:/test";
        }

    }

    @RequestMapping(value = "/login1", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your email address and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "login1";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (UserInfo info : userRepository.findAll()) {
            if (info.getUsername().equals(authentication.getName())) {
                System.out.println(info.getId());
            }
        }
        groupDao.listBookmark().clear();
        return "index_test";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.userList());
        return "users";
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public String deleteUser(Model model, @PathVariable("id") int id) {
        userService.delUser(id);
        System.out.println(id);
        model.addAttribute("userList", userService.userList());
        return "users";
    }
}
