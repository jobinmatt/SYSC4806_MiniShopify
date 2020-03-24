import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import springboot.Application;
import springboot.DTO.ShopDTO;
import springboot.Repository.ItemRepository;
import springboot.Repository.OwnerRepository;
import springboot.Repository.ShopRepository;
import springboot.controller.ShopController;
import springboot.model.Item;
import springboot.model.Owner;
import springboot.model.Shop;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(classes = Application.class)
@WebMvcTest(Application.class)
public class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopRepository shopRepository;

    @MockBean
    private OwnerRepository ownerRepository;

    @MockBean
    private ItemRepository itemRepository;

    private Owner userJohn, userTom;

    private Shop s1, s2;

    private ObjectMapper mapper = new ObjectMapper();

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private Item i1, i2;
    @InjectMocks
    private ShopController shopController = spy(new ShopController(shopRepository, ownerRepository, itemRepository));

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        userJohn = new Owner("John", "Doe", "john@doe.com", "password");
        i1 = new Item("i1", "test", 2, s1);
        i2 = new Item("i2", "test2", 3, s2);

        userTom = new Owner("Tom", "Doe", "tom@doe.com", "password");


        s1 = new Shop("s1", "desc", Arrays.asList("test", "test2"), userJohn, new ArrayList(Arrays.asList(i1)));

        s2 = new Shop("s2", "asdasd", Arrays.asList("test", "test13122"), userTom,  new ArrayList(Arrays.asList(i2)));
        s1.setId(1234);
        s2.setId(4000);
        userJohn.setOwnedShops(new ArrayList(Arrays.asList(s1)));
    }
    
    @Test
    public void testGet() throws Exception {
        doReturn(Optional.of(s1)).when(shopRepository).findById(Mockito.anyLong());
        MvcResult res = mockMvc.perform(get("/api/shop").param("shopId", String.valueOf(1234)))
                .andReturn();

        String content = res.getResponse().getContentAsString();

        Shop resShop = mapper.readValue(content, Shop.class);

        assert(resShop.getId() == s1.getId());
    }

    @Test
    public void testPatch() throws Exception{
        doReturn(Optional.of(s2)).when(shopRepository).findById(Mockito.anyLong());

        ShopDTO oldShop = new ShopDTO((long) 21, "newShop", "new desc", null, null, null);

        String oldShopStr = mapper.writeValueAsString(oldShop);

        MvcResult res = mockMvc.perform(patch("/api/shop").contentType(APPLICATION_JSON_UTF8)
                .content(oldShopStr)).andReturn();

        String content = res.getResponse().getContentAsString();

        Shop newShop = mapper.readValue(content, Shop.class);

        verify(shopRepository).save(any());
        assert (newShop.getName().equals("newShop"));
        assert(newShop.getDescription().equals("new desc"));
        assert (newShop.getProducts() != null);
    }

    @Test
    public void testCreate() throws Exception{
        doReturn(Optional.of(userJohn)).when(ownerRepository).findById(Mockito.anyLong());
        ShopDTO oldShop = new ShopDTO((long) 21, "newShop1", "new desc2", Arrays.asList("new"), userJohn.getId(), null);
        String oldShopStr = mapper.writeValueAsString(oldShop);
        MvcResult res = mockMvc.perform(post("/api/shop").contentType(APPLICATION_JSON_UTF8)
                .content(oldShopStr)).andReturn();

        String content = res.getResponse().getContentAsString();

        Shop newShop = mapper.readValue(content, Shop.class);

        verify(shopRepository).save(any());
        assert (newShop.getName().equals("newShop1"));
        assert(newShop.getDescription().equals("new desc2"));
        assert (newShop.getProducts().isEmpty());
        assert(newShop.getOwner().getFirstName().equals(userJohn.getFirstName()));
    }

    @Test
    public void testDelete() throws Exception{
        doReturn(Optional.of(s1)).when(shopRepository).findById(Mockito.anyLong());
        doReturn(Optional.of(userJohn)).when(ownerRepository).findById(Mockito.anyLong());
        MvcResult res = mockMvc.perform(delete("/api/shop").param("shopId", String.valueOf(1234)).param("ownerId", String.valueOf(12)))
                .andReturn();

        String content = res.getResponse().getContentAsString();
        verify(shopRepository).delete(any());
        assert(content.equals("Shop ID: " + s1.getId() + " deleted"));
    }

}
