package com.amela.controller;

import com.amela.model.User;
import com.amela.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Controller
@SessionAttributes("user")
public class UserController {
    @Autowired
    private IUserService userService;
    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }
    @RequestMapping("/login")
    public String showlogin(@CookieValue(value = "setUser",defaultValue = "")String setUser, Model model){
        Cookie cookie = new Cookie("setUser",setUser);
        model.addAttribute("cookieValue",cookie);

        return "/login";
    }

    @RequestMapping("/list")
    public ModelAndView listProducts() {

        List<User> users = userService.findAll();

        ModelAndView modelAndView = new ModelAndView("/list", "user", users);
        return modelAndView;
    }

    @PostMapping("/checklogin")
    public String checkLogin(@ModelAttribute("user") User user,Model model, @CookieValue(value = "setUser",defaultValue = "") String setUser,
                             HttpServletRequest request, HttpServletResponse response) {
   User userlis = userService.find(user.getUsername(),user.getPassword());

    if(Objects.nonNull(userlis)) {
        user.setUsername("");
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        model.addAttribute("message", "Login failed. Try again.");
       }else {
        if (user.getUsername() != null)
            setUser = user.getUsername();
        Cookie cookie = new Cookie("setUser", setUser);
        cookie.setMaxAge(60);
        response.addCookie(cookie);
        Cookie[] cookies = request.getCookies();
        for (Cookie ck : cookies) {
            //display only the cookie with the name 'setUser'
            if (ck.getName().equals("setUser")) {
                model.addAttribute("cookieValue", ck);
                break;
            } else {
                ck.setValue("");
                model.addAttribute("cookieValue", ck);
                break;
            }
        }
//            model.addAttribute("message", "Login success. Welcome ");


        return "/list";


        }
        return "/login";
    }


}
