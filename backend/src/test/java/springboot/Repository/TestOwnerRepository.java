//package springboot.Repository;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.FixMethodOrder;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import springboot.Application;
//import springboot.Repository.OwnerRepository;
//import springboot.model.Owner;
//
//import java.util.List;
//
////@RunWith(SpringJUnit4ClassRunner.class)
////@SpringBootTest(classes = Application.class)
////@FixMethodOrder(MethodSorters.NAME_ASCENDING)
////@DataJpaTest
//public class TestOwnerRepository {
//
//    @Autowired
//    private OwnerRepository ownerRepository;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    private Owner userJohn, userTom;
//
//    @Before
//    public void setup() {
//        userJohn = new Owner("John", "Doe", "john@doe.com", "password");
//        ownerRepository.save(userJohn);
//
//        userTom = new Owner("Tom", "Doe", "tom@doe.com", "password");
//        ownerRepository.save(userTom);
//    }
//
//    @Test
//    public void testFirstName() {
//
//        List<Owner> results = ownerRepository.findByFirstName(userJohn.getFirstName());
//        Assert.assertEquals(1, results.size());
//    }
//
//    @Test
//    public void givenLastName_getCorrectTest() {
//
//        List<Owner> results = ownerRepository.findByLastName("Doe");
//        Assert.assertEquals(2, results.size());
//    }
//
//    @Test
//    public void givenEmailAndPassword_getCorrect() {
//
//        Owner results = ownerRepository.findByEmailAndPassword(userJohn.getEmail(), userJohn.getPassword());
//        Assert.assertEquals(results, userJohn);
//    }
//}