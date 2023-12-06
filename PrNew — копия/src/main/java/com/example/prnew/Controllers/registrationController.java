package com.example.prnew.Controllers;

import com.example.prnew.Models.RoleEnum;
import com.example.prnew.Models.UsersModel;
import com.example.prnew.Repositories.RoleRepository;
import com.example.prnew.Repositories.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class registrationController {

    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;


    private final PasswordEncoder passwordEncoder;
    @Autowired
    public registrationController(PasswordEncoder passwordEncoder,UsersRepository usersRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/main")
    public String mainView(){
        return "/main";
    }


    @GetMapping("/registration")
    public String RegView(@ModelAttribute("users") UsersModel usersModel)
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String Registration(UsersModel users, Model model,
                               @RequestParam(required = false, name = "surname", defaultValue = " ") String Surname,
                               @RequestParam(required = false, name = "firstName", defaultValue = " ") String FirstName,
                               @RequestParam(required = false, name = "middleName", defaultValue = " ") String MiddleName,
                               @RequestParam(required = false, name = "emailUser", defaultValue = " ") String emailUser,
                               @RequestParam(required = false, name = "passwordUser", defaultValue = " ") String PasswordUser) {
        UsersModel user_from_db = usersRepository.findByEmailUser(emailUser);
        if (user_from_db != null) {
            model.addAttribute("message", "Пользователь с такой электронной почтой уже существует!");
            return "registration";
        }

        users.setRole(Collections.singleton(RoleEnum.USER));
        users = new UsersModel(Surname, FirstName, MiddleName, emailUser, passwordEncoder.encode(PasswordUser), true, roleRepository.findByRole("USER"));
        usersRepository.save(users);
        return "redirect: /login";
    }
}
