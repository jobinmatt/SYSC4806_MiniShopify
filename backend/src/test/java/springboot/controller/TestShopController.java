package springboot.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import springboot.Application;
import springboot.Repository.OwnerRepository;
import springboot.Repository.ShopRepository;
import springboot.model.Owner;
import springboot.model.Shop;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class TestShopController{

    @Autowired
    private MockMvc mockMvc;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @MockBean
    private ShopRepository shopRepository;

    private Owner userJohn, userTom;

    private Shop s1, s2;
//
//    @MockBean
//    ShopController shopController;

    @Before
    public void setup(){
        userJohn = new Owner("John", "Doe", "john@doe.com", "password");


        userTom = new Owner("Tom", "Doe", "tom@doe.com", "password");


        s1 = new Shop("s1", "desc", Arrays.asList("test", "test2"), userJohn, null);

        s2 = new Shop("s2", "asdasd", Arrays.asList("test", "test13122"), userTom, null);


    }



    @Test
    public void testGet() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(shopRepository.findById(Mockito.anyLong())).thenReturn(s1);
        MvcResult res = mockMvc.perform(get("/api/shop").param("shopId", String.valueOf(1234)))
                .andReturn();

        String content = res.getResponse().getContentAsString();
    }


}
