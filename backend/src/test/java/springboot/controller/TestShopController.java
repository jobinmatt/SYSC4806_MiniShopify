package springboot.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import springboot.Repository.ItemRepository;
import springboot.Repository.OwnerRepository;
import springboot.Repository.ShopRepository;
import springboot.model.Owner;
import springboot.model.Shop;

import java.util.Arrays;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//
//@SpringBootTest
//@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(ShopController.class)
public class TestShopController{

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

    @InjectMocks
    private ShopController shopController = spy(new ShopController(shopRepository, ownerRepository, itemRepository));

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        userJohn = new Owner("John", "Doe", "john@doe.com", "password");


        userTom = new Owner("Tom", "Doe", "tom@doe.com", "password");


        s1 = new Shop("s1", "desc", Arrays.asList("test", "test2"), userJohn, null);

        s2 = new Shop("s2", "asdasd", Arrays.asList("test", "test13122"), userTom, null);

    }



    @Test
    public void testGet() throws Exception {
        doReturn(s1).when(shopRepository).findByShopId(Mockito.anyLong());
        MvcResult res = mockMvc.perform(get("/api/shop").param("shopId", String.valueOf(1234)))
                .andReturn();

        String content = res.getResponse().getContentAsString();
    }


}
