package springboot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springboot.Repository.ShopRepository;
import springboot.model.Shop;
import springboot.model.ShopDTO;
import springboot.util.DTO;

import java.util.List;

@Controller
@RequestMapping("/shops")
public class ShopController {

    private ShopRepository shopRepository;

    public ShopController(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @GetMapping
    public List<Shop> getShops() {
        return shopRepository.findAll();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void newShop(@DTO(ShopDTO.class) Shop shop) {
        shopRepository.save(shop);
    }
}
