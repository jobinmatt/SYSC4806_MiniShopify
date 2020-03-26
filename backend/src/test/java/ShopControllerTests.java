import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springboot.Application;
import springboot.config.WebSecurity;
import springboot.controller.ShopController;
import springboot.filters.JWTAuthenticationFilter;
import springboot.filters.JWTAuthorizationFilter;
import springboot.model.Item;
import springboot.model.Owner;
import springboot.model.Shop;
import springboot.repository.ItemRepository;
import springboot.repository.OwnerRepository;
import springboot.repository.ShopRepository;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static springboot.constants.JWTConstants.HEADER_STRING;
import static springboot.constants.JWTConstants.SIGN_UP_URL;

@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
@RunWith(MockitoJUnitRunner.class)
public class ShopControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopRepository shopRepository;

    @MockBean
    private OwnerRepository ownerRepository;

    @MockBean
    private ItemRepository itemRepository;


    @Autowired
    private WebApplicationContext wac;

    private JWTAuthenticationFilter jwtAuthenticationFilter;

    private JWTAuthorizationFilter jwtAuthorizationFilter;

    private AuthenticationManager authenticationManager;

    @Autowired
    private WebSecurity webSecurity;

    private Shop s1;
    private Shop s2;
    private Owner userJohn;
    private Item i1;
    private Item i2;
    private Owner admin;
    private ObjectMapper mapper = new ObjectMapper();
    private String token="";
    @BeforeEach
    public void init() {
//        mockMvc = webAppContextSetup((WebApplicationContext) webSecurity).build();
//        AuthenticationManager authenticationManager = this.wac.getBean(AuthenticationManager.class);
        MockitoAnnotations.initMocks(this);
        this.admin = new Owner("ADMIN", "ADMIN", "ADMIN@gmail.com", "Pass4Admin");
        admin.setId(69);
        ArrayList<String> tempTags = new ArrayList<String>();
        tempTags.add("tag1");
        tempTags.add("tag2");
        this.i1 = new Item ("toothbrush","use it lol", 1, s1);
        this.i1 = new Item ("toothpaste","hi", 2, s1);
        ArrayList<Item> tempItems = new ArrayList<Item>();
        tempItems.add(i1);
        tempItems.add(i2);
        this.s1 = new Shop("Test Shop", "test", tempTags, admin, tempItems);
        this.s1.setId(420);

    }

    @Test
    @WithMockUser(value="ADMIN")
    public void testGet() throws Exception {
        when(shopRepository.findById(420)).thenReturn(s1);
        MvcResult res = mockMvc.perform(get("/api/public/shop").param("shopId", String.valueOf(420))  )
                .andReturn();

        String content = res.getResponse().getContentAsString();

        Shop resShop = mapper.readValue(content, Shop.class);

        assert(resShop.getId() == s1.getId());
    }
    @Test
    @WithMockUser(value="ADMIN")
    public void testCreate() throws Exception {
        when(ownerRepository.findById((long) 69)).thenReturn(admin);
        when(shopRepository.save(s1)).thenReturn(s1);
        MvcResult res = mockMvc.perform(post("/api/shop").contentType("application/json").content("{" +
                "\"name\" : \"Test Shop\"," +
                "\"desc\" : \"test\"," +
                "\"tags\" : [\"tag1\", \"tag2\"]," +
                "\"userId\" : 0," +
                "\"products\": []" + "}"))
                .andReturn();

        MockHttpServletResponse content = res.getResponse();

//        Shop resShop = mapper.readValue(content, Shop.class);

//        assert(resShop.getId() == s1.getId());
    }
}
