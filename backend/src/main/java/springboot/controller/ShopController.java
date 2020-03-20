package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.DTO.ItemDTO;
import springboot.DTO.ShopDTO;
import springboot.Repository.ItemRepository;
import springboot.Repository.ShopRepository;
import springboot.Repository.OwnerRepository;
import springboot.model.Item;
import springboot.model.Shop;
import springboot.model.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ShopController {

    private ShopRepository shopRepo;
    private OwnerRepository userRepo;
    private ItemRepository itemRepo;

    @Autowired
    public ShopController(ShopRepository shopRepo, OwnerRepository userRepo, ItemRepository itemRepo){
        this.shopRepo = shopRepo;
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
    }

    @RequestMapping(value = "/api/shop",method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity createShop(@RequestBody ShopDTO newShop){
        if(newShop != null && !isEmptyNull(newShop.getName()) && !isEmptyNull(newShop.getDescription())
               && newShop.getUserId() != null){
            Shop s = new Shop();
            s.setDescription(newShop.getDescription());
            s.setName(newShop.getName());
            if(newShop.getTags() != null && !newShop.getTags().isEmpty()){
                s.setTags(newShop.getTags());
            }
            if(newShop.getProducts() != null && !newShop.getProducts().isEmpty()){
                for(ItemDTO i : newShop.getProducts()){
                    if(!isEmptyNull(i.getName()) && !isEmptyNull(i.getDescription())
                            && i.getStockQuantity() != 0){
                        Item item = new Item();
                        item.setDescription(i.getDescription());
                        item.setName(i.getName());
                        item.setShop(s);
                        item.setStockQuantity(i.getStockQuantity());
                        s.getProducts().add(item);
                    }
                }
            }
            Optional<Owner> owner = userRepo.findById(newShop.getUserId());
            if(owner.isPresent()){
                s.setOwner(owner.get());
            } else {
                return ResponseEntity.badRequest().body("User id: " + newShop.getUserId() + " not found"); //Error so we return null obj and
            }
            shopRepo.save(s);
            return ResponseEntity.ok(s); //Everything is OK so we send the okay response with the new shop in the body
        } else {
            return ResponseEntity.badRequest().body("Shop object was null");
        }
    }

    @GetMapping(value = "/api/public/shop")
    public ResponseEntity getShop(@RequestParam(value = "shopId") Long shopId){
        if(shopId != null){
            Optional<Shop> shop = shopRepo.findById(shopId);
            if(shop.isPresent()){
                return ResponseEntity.ok(shop);
            } else {
                return ResponseEntity.badRequest().body("Shop ID: " + shopId + " not found");
            }
        } else {
            return ResponseEntity.badRequest().body("Shop ID is empty");
        }
    }

    @RequestMapping(value = "/api/shop", method = RequestMethod.PATCH, consumes = "application/json")
    public ResponseEntity editShop(@RequestBody ShopDTO editShop){
        if(editShop != null && editShop.getId() != null){
            Optional<Shop> shop = shopRepo.findById(editShop.getId());
            if(shop.isPresent()){
                Shop newShop = shop.get();
                if(!isEmptyNull(editShop.getName()) && !newShop.getName().equals(editShop.getName())){
                    newShop.setName(editShop.getName());
                }
                if(!isEmptyNull(editShop.getDescription()) && !newShop.getDescription().equals(editShop.getDescription())){
                    newShop.setDescription(editShop.getDescription());
                }
                if(editShop.getProducts() != null){
                    List<Item> iList = new ArrayList<>();
                    for(ItemDTO i : editShop.getProducts()){
                        if(itemRepo.findById(i.getId()).isPresent()){
                            iList.add(itemRepo.findById(i.getId()).get());
                        } else {
                            return ResponseEntity.badRequest().body("Item Id:" + i + " not found;");
                        }
                    }
                    newShop.setProducts(iList);
                }

                if(editShop.getTags() != null){
                    newShop.setTags(editShop.getTags());
                }

                if(editShop.getUserId() != null && newShop.getOwner().getId() != editShop.getUserId()){
                    Optional<Owner> owner = userRepo.findById(editShop.getUserId());
                    if(owner.isPresent()){
                        newShop.setOwner(owner.get());
                    } else {
                        return ResponseEntity.badRequest().body("New owner ID: " + editShop.getUserId() + " not found");
                    }
                }
                shopRepo.save(newShop);

                return ResponseEntity.ok(newShop);

            } else {
                return ResponseEntity.badRequest().body("Shop ID: " + editShop.getId() + " not found");
            }
        } else {
            return ResponseEntity.badRequest().body("Shop null or no id found");
        }
    }

    @DeleteMapping(value = "/api/shop")
    public ResponseEntity deleteShop(@RequestParam(value = "shopId") Long shopId, @RequestParam(value = "ownerId") Long ownerId){
        if(shopId != null && ownerId != null){
           Optional<Owner> owner = userRepo.findById(ownerId);
           Shop shopToDel = null;
           if(owner.isPresent()){
               for(Shop s : owner.get().getOwnedShops()){
                   if(s.getId() == shopId){
                       shopToDel = s;
                       break;
                   }
               }
               if(shopToDel != null){
                   shopRepo.delete(shopToDel);
                   owner.get().getOwnedShops().remove(shopToDel);
               } else {
                   return ResponseEntity.badRequest().body("Shop ID: " + shopId + " not found");
               }
               return ResponseEntity.ok("Shop ID: " + shopId + " deleted");
           } else{
               return ResponseEntity.ok("Owner ID: " + ownerId + "not found");
           }
        } else {
            return ResponseEntity.badRequest().body("Shop ID or Owner ID is null");
        }
    }

    //True if null or empty
    public boolean isEmptyNull( String s ) {
        return s == null || s.trim().isEmpty();
    }

}
