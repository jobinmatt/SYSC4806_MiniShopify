import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springboot.Application;
import springboot.filters.JWTAuthenticationFilter;
import springboot.filters.JWTAuthorizationFilter;
import springboot.model.Item;
import springboot.model.Owner;
import springboot.model.Shop;
import springboot.repository.ItemRepository;
import springboot.repository.OwnerRepository;
import springboot.repository.ShopRepository;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static springboot.constants.JWTConstants.HEADER_STRING;
import static springboot.constants.JWTConstants.SIGN_UP_URL;

@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
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
    @MockBean
    private AuthenticationManager authenticationManager;

    private Shop s1;
    private Shop s2;
    private Owner userJohn;
    private Item i1;
    private Item i2;
    private Owner userTom;
    private ObjectMapper mapper = new ObjectMapper();
    private String token="";
    @BeforeEach
    public void init() {
//        mockMvc = webAppContextSetup((WebApplicationContext) webSecurity).build();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(new JWTAuthenticationFilter(authenticationManager, ownerRepository)).addFilter(new JWTAuthorizationFilter(authenticationManager)).build();
        try{
            Owner admin = new Owner("ADMIN", "ADMIN", "ADMIN@gmail.com", "PASS$ADMIN");
            admin.setId(420);
            when(ownerRepository.save(admin)).thenReturn(admin);
            MvcResult signUpRes = mockMvc.perform((post("/api/owner").contentType("application/json").content("{" +
                    "\"firstName\" : \"ADMIN\"," +
                    "\"lastName\" : \"ADMIN\"," +
                    "\"email\" : \"ADMIN@gmail.com\"," +
                    "\"password\" : \"PASS4ADMIN\"" +
                    "}")))
                    .andReturn();
            when(ownerRepository.findByEmail(admin.getEmail())).thenReturn(admin);
            MvcResult loginRes = mockMvc.perform((post("/api/login").contentType("application/json").content("{\"email\" : \"ADMIN@gmail.com\",\"password\" : \"PASS4ADMIN\"}")))
                    .andReturn();
            this.token = (String) loginRes.getResponse().getHeaderValue(HEADER_STRING);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testGet() throws Exception {
        MvcResult res = mockMvc.perform(get("/api/public/shop").param("shopId", String.valueOf(1))  )
                .andReturn();

        String content = res.getResponse().getContentAsString();

//        Shop resShop = mapper.readValue(content, Shop.class);

//        assert(resShop.getId() == s1.getId());
    }
    @Test
    public void testCreate() throws Exception {
//        doReturn(Optional.of(s1)).when(shopRepository).findById(Mockito.anyLong());
//        doReturn(Optional.of(s1)).when(shopRepository).findById(1);
        MvcResult res = mockMvc.perform(post("/api/shop").header(HEADER_STRING,this.token))
                .andReturn();

        MockHttpServletResponse content = res.getResponse();

//        Shop resShop = mapper.readValue(content, Shop.class);

//        assert(resShop.getId() == s1.getId());
    }
}
