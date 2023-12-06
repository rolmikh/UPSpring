package com.example.prnew.Controllers;

import com.example.prnew.Models.*;
import com.example.prnew.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("/Admin")
public class AdminControllerView {

    public String url = "http://localhost:8080/";
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminControllerView(OrdersRepository ordersRepository, UsersRepository usersRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.ordersRepository = ordersRepository;
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }



    //Просмотр данных ADMIN
    @GetMapping("/AllView")
    public String AllView(Model model){
        Product[] getProduct = getRestTemplate().getForObject(url+"product", Product[].class);
        CategoryProduct[] getCategory = getRestTemplate().getForObject(url+"categoryProduct", CategoryProduct[].class);
        Provider[] getProvider = getRestTemplate().getForObject(url+"provider", Provider[].class);
        StatusProduct[] getStatusProduct = getRestTemplate().getForObject(url+"statusProduct", StatusProduct[].class);
        model.addAttribute("product", getProduct);
        model.addAttribute("categoryProduct", getCategory);
        model.addAttribute("provider", getProvider);
        model.addAttribute("orders", ordersRepository.findAll());
        model.addAttribute("statusProduct", getStatusProduct);
        return "/Admin/AllView";
    }

    //Страны ADMIN
    @GetMapping("/Country")
    public String CountryView(Model model){
        Country[] getCountry = getRestTemplate().getForObject(url+"country", Country[].class);
        model.addAttribute("country", getCountry);
        return "/Admin/Country";
    }
    //Страны ADMIN
    @GetMapping("/CountryNew")
    public String CountryNewView(Model model, @ModelAttribute("newCountry") Country country) {
        return "/Admin/CountryNew";
    }

    //Добавление стран ADMIN
    @PostMapping("/Country")
    public String createCountry(@Valid Country country, BindingResult result, Model model,
                                @RequestParam(required=false, name="nameCountry", defaultValue = " ") String nameCountry){
        if(result.hasErrors()){
            return "/Admin/CountryNew";
        }
        country = new Country(nameCountry);
        Country requestPost = getRestTemplate().postForObject(url+"country",country,Country.class);
        Country[] getCountry = getRestTemplate().getForObject(url+"country", Country[].class);
        model.addAttribute("country", getCountry);
        return "redirect:/Admin/Country";
    }

    //Страны ADMIN
    @GetMapping("/{id}/CountryUpdate")
    public String showCountry(@PathVariable("id") Long id, Model model){
        Country getCountryOne = getRestTemplate().getForObject(url+"country/"+id, Country.class);
        model.addAttribute("countries", getCountryOne);
        return "Admin/CountryUpdate";
    }

    //обновление стран ADMIN
    @PostMapping("/CountryUpdate/{id}")
    public String updateCountry(@PathVariable(value = "id") Long id,
                                @RequestParam(required=false, name="nameCountry", defaultValue = " ") String nameCountry,
                                @Valid Country country, BindingResult result, Model model){
        if(result.hasErrors()){
            return "/Admin/CountryUpdate";
        }
        country = new Country(id, nameCountry);
        getRestTemplate().put(url+"country/"+id,country);
        Country[] getCountry = getRestTemplate().getForObject(url+"country", Country[].class);
        model.addAttribute("country", getCountry);
        return "redirect:/Admin/Country";
    }

    // Удаление стран ADMIN
    @GetMapping("/Country/{id}")
    public String deleteCountry (@PathVariable(value = "id") Long id, Model model) {
        getRestTemplate().delete(url+"country/"+id);
        Country[] getCountry = getRestTemplate().getForObject(url+"country", Country[].class);
        model.addAttribute("country", getCountry);
        return "/Admin/Country";
    }

    //Роли ADMIN
    @GetMapping("/Role")
    public String RoleView(Model model){
        Role[] getRole= getRestTemplate().getForObject(url+"role", Role[].class);
        model.addAttribute("role", getRole);
        return "/Admin/Role";
    }

    //Роли ADMIN
    @GetMapping("/RoleNew")
    public String RoleNewView(Model model, @ModelAttribute("newRole") Role role) {
        return "/Admin/RoleNew";
    }

    //Добавление ролей ADMIN
    @PostMapping("/Role")
    public String createRole(@Valid Role roles, BindingResult result, Model model,
                                @RequestParam(required=false, name="nameRole", defaultValue = " ") String nameRole){
        if(result.hasErrors()){
            return "/Admin/RoleNew";
        }
        roles = new Role(nameRole);
        Role requestPost = getRestTemplate().postForObject(url+"role",roles,Role.class);
        Role[] getRole= getRestTemplate().getForObject(url+"role", Role[].class);
        model.addAttribute("role", getRole);
        return "redirect:/Admin/Role";
    }

    //Роли ADMIN
    @GetMapping("/{id}/RoleUpdate")
    public String showRoles(@PathVariable("id") Long id, Model model){
        Role getRoleOne = getRestTemplate().getForObject(url+"role/"+id, Role.class);
        model.addAttribute("roles", getRoleOne);
        return "Admin/RoleUpdate";
    }

    //обновление ролей ADMIN
    @PostMapping("/RoleUpdate/{id}")
    public String updateRole(@PathVariable(value = "id") Long id,
                                @RequestParam(required=false, name="nameRole", defaultValue = " ") String nameRole,
                                @Valid Role role, BindingResult result, Model model){
        if(result.hasErrors()){
            return "/Admin/RoleUpdate";
        }
        role = new Role(id, nameRole);
        getRestTemplate().put(url+"role/"+id,role);
        Role[] getRole= getRestTemplate().getForObject(url+"role", Role[].class);
        model.addAttribute("role", getRole);
        return "/Admin/Role";
    }

    // Удаление ролей ADMIN
    @GetMapping("/Role/{id}")
    public String deleteRole (@PathVariable(value = "id") Long id, Model model) {
        getRestTemplate().delete(url+"role/"+id);
        Role[] getRole = getRestTemplate().getForObject(url+"role", Role[].class);
        model.addAttribute("role", getRole);
        return "/Admin/Role";
    }

    //Статусы заказов ADMIN
    @GetMapping("/StatusOrder")
    public String StatusOrderView(Model model){
        StatusOrder[] getStatusOrder = getRestTemplate().getForObject(url+"statusOrder", StatusOrder[].class);
        model.addAttribute("StatusOrder", getStatusOrder);
        return "/Admin/StatusOrder";
    }

    //Статусы заказов ADMIN
    @GetMapping("/StatusOrderNew")
    public String StatusOrderNewView(Model model, @ModelAttribute("newStatusOrder") StatusOrder statusOrder) {
        return "/Admin/StatusOrderNew";
    }


    //Добавление статусов ADMIN
    @PostMapping("/StatusOrder")
    public String createStatusOrder(@Valid StatusOrder statusOrder, BindingResult result, Model model,
                             @RequestParam(required=false, name="nameStatusOrder", defaultValue = " ") String nameStatusOrder){
        if(result.hasErrors()){
            return "/Admin/StatusOrderNew";
        }
        statusOrder = new StatusOrder(nameStatusOrder);
        StatusOrder requestPost = getRestTemplate().postForObject(url+"statusOrder",statusOrder,StatusOrder.class);
        StatusOrder[] getStatusOrder = getRestTemplate().getForObject(url+"statusOrder", StatusOrder[].class);
        model.addAttribute("StatusOrder", getStatusOrder);
        return "redirect:/Admin/StatusOrder";
    }

    //Статусы ADMIN
    @GetMapping("/{id}/StatusOrderUpdate")
    public String showStatus(@PathVariable("id") Long id, Model model){
        StatusOrder getStatusOrderOne = getRestTemplate().getForObject(url+"statusOrder/"+id, StatusOrder.class);
        model.addAttribute("status", getStatusOrderOne);
        return "Admin/StatusOrderUpdate";
    }

    //обновление статусов ADMIN
    @PostMapping("/StatusOrderUpdate/{id}")
    public String updateStatus(@PathVariable(value = "id") Long id,
                             @RequestParam(required=false, name="nameStatusOrder", defaultValue = " ") String nameStatusOrder,
                             @Valid StatusOrder statusOrder, BindingResult result, Model model){
        if(result.hasErrors()){
            return "/Admin/StatusOrderUpdate";
        }
        statusOrder = new StatusOrder(id,nameStatusOrder);
        getRestTemplate().put(url+"statusOrder/"+id,statusOrder);
        StatusOrder[] getStatusOrder = getRestTemplate().getForObject(url+"statusOrder", StatusOrder[].class);
        model.addAttribute("StatusOrder", getStatusOrder);
        return "redirect:/Admin/StatusOrder";
    }

    // Удаление статусов ADMIN
    @GetMapping("/StatusOrder/{id}")
    public String deleteStatusOrder (@PathVariable(value = "id") Long id, Model model) {
        getRestTemplate().delete(url+"statusOrder/"+id);
        StatusOrder[] getStatusOrder = getRestTemplate().getForObject(url+"statusOrder", StatusOrder[].class);
        model.addAttribute("StatusOrder", getStatusOrder);
        return "/Admin/StatusOrder";
    }

    //Все пользователи ADMIN
    @GetMapping("/UserCRUD")
    public String UserView(Model model){
//        UsersModel[] getUser = getRestTemplate().getForObject(url+"user", StatusOrder[].class);

        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("users", usersRepository.findAll());
        return "Admin/UserCRUD";

    }

    @GetMapping("/{id}/UserUpdate")
    public String showUser(@PathVariable("id") Long id, Model model ){
        UsersModel usersModel = usersRepository.findById(id).orElseThrow();
        Iterable<Role> roles = roleRepository.findAll();
        model.addAttribute("role", roles);
        model.addAttribute("users", usersModel);
        return "Admin/UserUpdate";
    }

    //Пользователи ADMIN
    @GetMapping("/UserNew")
    public String UserNewView(Model model, @ModelAttribute("newUser") UsersModel usersModel) {
        Iterable<Role> roles = roleRepository.findAll();
        model.addAttribute("role", roles);
        return "Admin/UserNew";
    }

    //Добавление пользователей ADMIN
    @PostMapping("/UserCRUD")
    public String createUser(@RequestParam(required = false, name = "surname", defaultValue = " ") String Surname,
            @RequestParam(required = false, name = "firstName", defaultValue = " ") String FirstName,
            @RequestParam(required = false, name = "middleName", defaultValue = " ") String MiddleName,
            @RequestParam(required = false, name = "emailUser", defaultValue = " ") String emailUser,
            @RequestParam(required = false, name = "passwordUser", defaultValue = " ") String PasswordUser,
            @RequestParam(required = false, name = "role", defaultValue = " ") String role,
            @Valid UsersModel users, BindingResult result, Model model){
        if(result.hasErrors()){
            return "/Admin/UserNew";
        }
        users = new UsersModel(Surname,FirstName,MiddleName,emailUser, passwordEncoder.encode(PasswordUser), true, roleRepository.findByRole(role));
        usersRepository.save(users);
        return "redirect:/Admin/UserCRUD";
    }

    // изменение пользователя ADMIN
//    @PostMapping("/UserUpdate/{id}")
//    private String updateUser(@PathVariable("id") Long id,
//                                @RequestParam(required = false, name = "surname", defaultValue = " ") String Surname,
//                                @RequestParam(required = false, name = "firstName", defaultValue = " ") String FirstName,
//                                @RequestParam(required = false, name = "middleName", defaultValue = " ") String MiddleName,
//                                @RequestParam(required = false, name = "emailUser", defaultValue = " ") String emailUser,
//                                @RequestParam(required = false, name = "passwordUser", defaultValue = " ") String PasswordUser,
//                                @RequestParam(required = false, name = "role", defaultValue = " ") String role,
//                                @Valid UsersModel users, BindingResult result, Model model){
//        if(result.hasErrors()){
//            return "/Admin/UserUpdate";
//        }
//        Role role1 = roleRepository.findByRole(role);
//        users = new UsersModel(id,Surname,FirstName,MiddleName,emailUser, PasswordUser,true, role1);
//        usersRepository.save(users);
//        return "redirect:/Admin/UserCRUD";
//    }


    // Удаление пользователей Admin
    @GetMapping("/UserCRUD/{id}")
    public String deleteUser (@PathVariable(value = "id") Long id, Model model) {
        usersRepository.deleteById(id);
        model.addAttribute("users", usersRepository.findAll());
        return "/Admin/UserCRUD";
    }
}
