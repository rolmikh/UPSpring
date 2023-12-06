package com.example.prnew.Controllers;

import com.example.prnew.Models.*;
import com.example.prnew.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyAuthority('WAREHOUSE')")
@RequestMapping("/Warehouse")
public class WarehouseController {

    public String url = "http://localhost:8080/";
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    private final ProviderRepository providerRepository;

    private final CountryRepository countryRepository;


    private final CategoryProductRepository categoryProductRepository;

    private final StatusProductRepository statusProductRepository;
    @Autowired
    public WarehouseController(ProviderRepository providerRepository, CountryRepository countryRepository,  CategoryProductRepository categoryProductRepository, StatusProductRepository statusProductRepository) {
        this.providerRepository = providerRepository;
        this.countryRepository = countryRepository;
        this.categoryProductRepository = categoryProductRepository;
        this.statusProductRepository = statusProductRepository;
    }

    //Поставщики WAREHOUSE
    @GetMapping("/ProviderCRUD")
    public String ProviderCRUDView(Model model) {
        Provider[] getProvider = getRestTemplate().getForObject(url+"provider", Provider[].class);
        model.addAttribute("providers", getProvider);
        return "/Warehouse/ProviderCRUD";
    }
    //Поставщики WAREHOUSE
    @GetMapping("/ProviderNew")
    public String ProviderNewView(Model model, @ModelAttribute("newProvider") Provider provider) {
        Country[] getCountry = getRestTemplate().getForObject(url+"country", Country[].class);
        model.addAttribute("country", getCountry);
        return "/Warehouse/ProviderNew";
    }

    //Добавление поставщиков WAREHOUSE
    @PostMapping()
    public String create(@Valid Provider provider, BindingResult result, Model model,
                         @RequestParam(required=false, name="nameProvider", defaultValue = " ") String nameProvider,
                         @RequestParam(required=false, name="nameCountry", defaultValue = " ") String nameCountry){
        if(result.hasErrors()){
            return "/Warehouse/ProviderCRUD";
        }
        Country country = countryRepository.findByNameCountry(nameCountry);
        provider = new Provider(nameProvider, country);
        Provider requestPost = getRestTemplate().postForObject(url+"provider",provider,Provider.class);
        Provider[] getProvider = getRestTemplate().getForObject(url+"provider", Provider[].class);
        model.addAttribute("providers", getProvider);
        return "redirect:/Warehouse/ProviderCRUD";
    }

    //Поставщики WAREHOUSE
    @GetMapping("/{id}/ProviderUpdate")
    public String showProvider(@PathVariable("id") Long id, Model model){
        Provider getProviderOne = getRestTemplate().getForObject(url+"provider/"+id, Provider.class);
        model.addAttribute("provider", getProviderOne);
        Country[] getCountry = getRestTemplate().getForObject(url+"country", Country[].class);
        model.addAttribute("countries", getCountry);
        return "Warehouse/ProviderUpdate";
    }
    //Обновление поставщиков WAREHOUSE
    @PostMapping("/ProviderUpdate/{id}")
    public String updateProvider(@PathVariable(value = "id") Long id,
                         @RequestParam(required=false, name="nameProvider", defaultValue = " ") String nameProvider,
                         @RequestParam(required=false, name="idCountry", defaultValue = " ") String nameCountry,
                                 @Valid Provider provider, BindingResult result, Model model){
        Country country = countryRepository.findByNameCountry(nameCountry);
        if(result.hasErrors()){
            return "/Warehouse/ProviderCRUD";
        }
        provider = new Provider(id,nameProvider, country);
        getRestTemplate().put(url+"provider/"+id,provider);
        Provider[] getProvider = getRestTemplate().getForObject(url+"provider", Provider[].class);
        model.addAttribute("providers", getProvider);
        return "/Warehouse/ProviderCRUD";
    }


    // Удаление поставщиков WAREHOUSE
    @GetMapping("/ProviderCRUD/{id}")
    public String deleteProvider (@PathVariable(value = "id") Long id, Model model) {
        getRestTemplate().delete(url+"provider/"+id);
        Provider[] getProvider = getRestTemplate().getForObject(url+"provider", Provider[].class);
        model.addAttribute("providers", getProvider);
        return "/Warehouse/ProviderCRUD";
    }


    //Товары WAREHOUSE
    @GetMapping("/Product")
    public String ProductView(Model model){
        Product[] getProduct= getRestTemplate().getForObject(url+"product", Product[].class);
        model.addAttribute("products", getProduct);
        return "Warehouse/Product";
    }

    //Товары WAREHOUSE
    @GetMapping("/ProductNew")
    public String ProductNewView(Model model, @ModelAttribute("newProduct") Product product) {
        CategoryProduct[] getCategory = getRestTemplate().getForObject(url+"categoryProduct", CategoryProduct[].class);
        Provider[] getProvider = getRestTemplate().getForObject(url+"provider", Provider[].class);
        StatusProduct[] getStatusProduct = getRestTemplate().getForObject(url+"statusProduct", StatusProduct[].class);
        model.addAttribute("categoryProducts", getCategory);
        model.addAttribute("providers", getProvider);
        model.addAttribute("statusProducts", getStatusProduct);
        return "/Warehouse/ProductNew";
    }
    //Добавление товары WAREHOUSE
    @PostMapping("/Product")
    public String createProduct(@Valid Product product, BindingResult result, Model model,
                                @RequestParam(required=false, name="nameProduct", defaultValue = " ") String nameProduct,
                                @RequestParam(required=false, name="priceProduct", defaultValue = " ") int priceProduct,
                                @RequestParam(required=false, name="countProduct", defaultValue = " ") int countProduct,
                                @RequestParam(required=false, name="nameCategoryProduct", defaultValue = " ") String nameCategoryProduct,
                                @RequestParam(required=false, name="nameProvider", defaultValue = " ") String nameProvider,
                                @RequestParam(required=false, name="nameStatusProduct", defaultValue = " ") String nameStatusProduct){
        if(result.hasErrors()){
            return "/Warehouse/ProductNew";
        }

        CategoryProduct categoryProduct = categoryProductRepository.findByNameCategoryProduct(nameCategoryProduct);
        Provider provider = providerRepository.findByNameProvider(nameProvider);
        StatusProduct statusProduct = statusProductRepository.findByNameStatusProduct(nameStatusProduct);
        product = new Product(nameProduct,priceProduct,countProduct,categoryProduct,provider,statusProduct);
        Product requestPost = getRestTemplate().postForObject(url+"product",product, Product.class);
        Product[] getProduct= getRestTemplate().getForObject(url+"product", Product[].class);
        model.addAttribute("products", getProduct);
        return "redirect:/Warehouse/Product";
    }

    //Товары WAREHOUSE
    @GetMapping("/{id}/ProductUpdate")
    public String showProduct(@PathVariable("id") Long id, Model model){
        Product getProductOne = getRestTemplate().getForObject(url+"product/"+id, Product.class);
        model.addAttribute("product", getProductOne);
        CategoryProduct[] getCategory = getRestTemplate().getForObject(url+"categoryProduct", CategoryProduct[].class);
        Provider[] getProvider = getRestTemplate().getForObject(url+"provider", Provider[].class);
        StatusProduct[] getStatusProduct = getRestTemplate().getForObject(url+"statusProduct", StatusProduct[].class);
        model.addAttribute("categories", getCategory);
        model.addAttribute("providers", getProvider);
        model.addAttribute("statusProduct", getStatusProduct);
        return "Warehouse/ProductUpdate";
    }


    //обновление товаров WAREHOUSE
    @PostMapping("/ProductUpdate/{id}")
    public String updateProduct(@PathVariable(value = "id") Long id,
                                @RequestParam(required=false, name="nameProduct", defaultValue = " ") String nameProduct,
                                @RequestParam(required=false, name="priceProduct", defaultValue = " ") int priceProduct,
                                @RequestParam(required=false, name="countProduct", defaultValue = " ") int countProduct,
                                @RequestParam(required=false, name="nameCategoryProduct", defaultValue = " ") String nameCategoryProduct,
                                @RequestParam(required=false, name="nameProvider", defaultValue = " ") String nameProvider,
                                @RequestParam(required=false, name="nameStatusProduct", defaultValue = " ") String nameStatusProduct,
                                @Valid Product product, BindingResult result, Model model){
        if(result.hasErrors()){
            return "/Warehouse/ProductUpdate";
        }
        CategoryProduct categoryProduct = categoryProductRepository.findByNameCategoryProduct(nameCategoryProduct);
        Provider provider = providerRepository.findByNameProvider(nameProvider);
        StatusProduct statusProduct = statusProductRepository.findByNameStatusProduct(nameStatusProduct);
        product = new Product(id,nameProduct,priceProduct,countProduct,categoryProduct,provider,statusProduct);
        getRestTemplate().put(url+"product/"+id,product);
        Product[] getProduct= getRestTemplate().getForObject(url+"product", Product[].class);
        model.addAttribute("products", getProduct);
        return "redirect:/Warehouse/Product";
    }

    // Удаление товаров WAREHOUSE
    @GetMapping("/Product/{id}")
    public String deleteProduct (@PathVariable(value = "id") Long id, Model model) {
        getRestTemplate().delete(url+"product/"+id);
        Product[] getProduct= getRestTemplate().getForObject(url+"product", Product[].class);
        model.addAttribute("products", getProduct);
        return "/Warehouse/Product";
    }

    //Категории товаров WAREHOUSE
    @GetMapping("/CategoryProduct")
    public String CategoryProductView(Model model){
        CategoryProduct[] getCategoryProduct = getRestTemplate().getForObject(url+"categoryProduct", CategoryProduct[].class);
        model.addAttribute("categories", getCategoryProduct);
        return "Warehouse/CategoryProduct";
    }

    //Категории товаров WAREHOUSE
    @GetMapping("/CategoryProductNew")
    public String CategoryProductNewView(@ModelAttribute("newCategoryProduct") CategoryProduct categoryProduct) {
        return "/Warehouse/CategoryProductNew";
    }


    //Добавление категорий товаров WAREHOUSE
    @PostMapping("/CategoryProduct")
    public String createCategoryProduct(@RequestParam(required=false, name="nameCategoryProduct", defaultValue = " ") String nameCategoryProduct,
                                        @Valid CategoryProduct categoryProduct, BindingResult result, Model model){
        if(result.hasErrors()){
            return "/Warehouse/CategoryProductNew";
        }
        categoryProduct = new CategoryProduct(nameCategoryProduct);

        CategoryProduct requestPost = getRestTemplate().postForObject(url+"categoryProduct",categoryProduct, CategoryProduct.class);
        CategoryProduct[] getCategoryProduct = getRestTemplate().getForObject(url+"categoryProduct", CategoryProduct[].class);
        model.addAttribute("categories", getCategoryProduct);
        return "redirect:/Warehouse/CategoryProduct";
    }

    //категории товаров WAREHOUSE
    @GetMapping("/{id}/CategoryProductUpdate")
    public String showCategoryProduct(@PathVariable("id") Long id, Model model){
        CategoryProduct getCategoryProductOne = getRestTemplate().getForObject(url+"categoryProduct/"+id, CategoryProduct.class);
        model.addAttribute("categoryProduct", getCategoryProductOne);
        return "Warehouse/CategoryProductUpdate";
    }

    //обновление категорий товаров WAREHOUSE
    @PostMapping("/CategoryProductUpdate/{id}")
    public String updateCategoryProduct(@PathVariable(value = "id") Long id,
                                @RequestParam(required=false, name="nameCategoryProduct", defaultValue = " ") String nameCategoryProduct,
                                @Valid CategoryProduct categoryProduct, BindingResult result, Model model){
        if(result.hasErrors()){
            return "/Warehouse/CategoryProductUpdate";
        }
        categoryProduct = new CategoryProduct(id, nameCategoryProduct);

        getRestTemplate().put(url+"categoryProduct/"+id,categoryProduct);
        CategoryProduct[] getCategoryProduct = getRestTemplate().getForObject(url+"categoryProduct", CategoryProduct[].class);
        model.addAttribute("categories", getCategoryProduct);
        return "redirect:/Warehouse/CategoryProduct";
    }


    // Удаление категории товаров WAREHOUSE
    @GetMapping("/CategoryProduct/{id}")
    public String deleteCategoryProduct (@PathVariable(value = "id") Long id, Model model) {
        getRestTemplate().delete(url + "categoryProduct/"+id);
        CategoryProduct[] getCategoryProduct = getRestTemplate().getForObject(url+"categoryProduct", CategoryProduct[].class);
        model.addAttribute("categories", getCategoryProduct);
        return "/Warehouse/CategoryProduct";
    }
}
