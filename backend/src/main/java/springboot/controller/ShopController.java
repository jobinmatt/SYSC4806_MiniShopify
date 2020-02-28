package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springboot.DTO.ShopDTO;
import springboot.Repository.ItemRepository;
import springboot.Repository.ShopRepository;
import springboot.Repository.UserRepository;
import springboot.model.Item;
import springboot.model.Shop;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShopController {

    private ShopRepository shopRepo;
    private UserRepository userRepo;
    private ItemRepository itemRepo;

    @Autowired
    public ShopController(ShopRepository shopRepo, UserRepository userRepo, ItemRepository itemRepo){
        this.shopRepo = shopRepo;
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
    }

    @GetMapping(value = "/api/createShop", consumes = "application/json")
    public ResponseEntity createShop(@RequestBody ShopDTO newShop){
        if(newShop != null){
            Shop s = new Shop();
            s.setDescription(newShop.getDescription());
            s.setName(newShop.getName());
            s.setTags(newShop.getTags());
            if(userRepo.findById(newShop.getUserId()).isPresent()){
                s.setOwner(userRepo.findById(newShop.getUserId()).get());
            } else {
                return ResponseEntity.badRequest().body("User id: " + newShop.getUserId() + " not found"); //Error so we return null obj and
            }
            List<Item> iList = new ArrayList<>();
            for(Long i : newShop.getProductIds()){
                if(itemRepo.findById(i).isPresent()){
                    iList.add(itemRepo.findById(i).get());
                } else {
                    return ResponseEntity.badRequest().body("Item Id:" + i + " not found;");
                }
            }
            s.setProducts(iList);
            return ResponseEntity.ok(s); //Everything is OK so we send the okay response with the new shop in the body
        } else {
            return ResponseEntity.badRequest().body("Shop object was null");
        }
    }
}
