package com.example.prnew.Controllers;

import com.example.prnew.Models.*;
import com.example.prnew.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@PreAuthorize("hasAnyAuthority('USER')")
@RequestMapping("/Users")
public class UserController {
    public String url = "http://localhost:8080/";
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;

    private final CategoryProductRepository categoryProductRepository;

    private final UsersRepository usersRepository;

    private final OrdersRepository ordersRepository;
    private final StatusProductRepository statusProductRepository;

    private final StatusOrderRepository statusOrderRepository;

    @Autowired
    public UserController(ProductRepository productRepository, ProviderRepository providerRepository, CategoryProductRepository categoryProductRepository, UsersRepository usersRepository, OrdersRepository ordersRepository, StatusProductRepository statusProductRepository, StatusOrderRepository statusOrderRepository) {
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
        this.categoryProductRepository = categoryProductRepository;
        this.usersRepository = usersRepository;
        this.ordersRepository = ordersRepository;
        this.statusProductRepository = statusProductRepository;
        this.statusOrderRepository = statusOrderRepository;
    }

    //Заказы USER
    @GetMapping("/OrderNew")
    public String OrderNewView(@ModelAttribute("newOrders")Orders orders, Model model) {
        Iterable<StatusOrder> statusOrders = statusOrderRepository.findAll();
        Iterable<UsersModel> usersModels = usersRepository.findAll();
        model.addAttribute("status", statusOrders);
        model.addAttribute("users", usersModels);
        return "/Users/OrderNew";
    }

    //добавление заказов USER
    @PostMapping("/Order")
    public String createOrder(@RequestParam(required=false, name="numberOrder", defaultValue = " ") String numberOrder,
                              @RequestParam(required=false, name="priceOrder", defaultValue = " ") int priceOrder,
                              @RequestParam(required=false, name="dateOrder", defaultValue = " ") String dateOrder,
                              @RequestParam(required=false, name="nameStatusOrder", defaultValue = " ") String nameStatusOrder,
                              @RequestParam(required=false, name="emailUser", defaultValue = " ") String emailUser,
                              @Valid Orders orders, BindingResult result, Model model){
        if(result.hasErrors()){
            return "/Users/OrderNew";
        }
        StatusOrder statusOrder = statusOrderRepository.findByNameStatusOrder(nameStatusOrder);
        UsersModel usersModel = usersRepository.findByEmailUser(emailUser);
        orders = new Orders(priceOrder, numberOrder,statusOrder, usersModel, dateOrder);
        ordersRepository.save(orders);
        model.addAttribute("orders", ordersRepository.findAll());
        return "redirect:/Users/Order";
    }

    //Заказы USER
    @GetMapping("/{id}/OrderUpdate")
    public String showOrder(@PathVariable("id") Long id, Model model){
        Orders orders = ordersRepository.findById(id).orElseThrow();
        model.addAttribute("status", statusOrderRepository.findAll());
        model.addAttribute("users",usersRepository.findAll());
        model.addAttribute("orders", orders);
        return "Users/OrderUpdate";
    }

    //обновление заказов USER
    @PostMapping("/OrderUpdate/{id}")
    public String updateOrder(@PathVariable(value = "id") Long id,
                                @RequestParam(required=false, name="numberOrder", defaultValue = " ") String numberOrder,
                                @RequestParam(required=false, name="priceOrder", defaultValue = " ") int priceOrder,
                                @RequestParam(required=false, name="dateOrder", defaultValue = " ") String dateOrder,
                                @RequestParam(required=false, name="nameStatusOrder", defaultValue = " ") String nameStatusOrder,
                                @RequestParam(required=false, name="emailUser", defaultValue = " ") String emailUser,
                                @Valid Orders orders, BindingResult result, Model model){
        if(result.hasErrors()){
            return "/Users/OrderUpdate";
        }
        StatusOrder statusOrder = statusOrderRepository.findByNameStatusOrder(nameStatusOrder);
        UsersModel usersModel = usersRepository.findByEmailUser(emailUser);
        orders = new Orders(id, priceOrder, numberOrder,statusOrder, usersModel, dateOrder);
        ordersRepository.save(orders);
        model.addAttribute("orders", ordersRepository.findAll());
        return "redirect:/Users/Order";
    }


    //Все заказы пользователя USER
    @GetMapping("/Order")
    public String OrderView(Model model){
        model.addAttribute("status", statusOrderRepository.findAll());
        model.addAttribute("user", usersRepository.findAll());
        model.addAttribute("orders", ordersRepository.findAll());
        return "Users/Order";
    }

    // Удаление заказов USER
    @GetMapping("/Order/{id}")
    public String deleteOrder (@PathVariable(value = "id") Long id, Model model) {
        ordersRepository.deleteById(id);
        model.addAttribute("orders", ordersRepository.findAll());
        return "/Users/Order";
    }

    //каталог товаров USER
    @GetMapping("/ProductCatalog")
    public String CatalogView(Model model){
        Product[] getProduct= getRestTemplate().getForObject(url+"product", Product[].class);
        model.addAttribute("products", getProduct);
        return "Users/ProductCatalog";
    }



}
