package APIFullstack.websachcu.TEST;

import APIFullstack.websachcu.Service.Checkmail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JunitTest {

    @Test
    public void testunit(){
        Boolean test = true;
        Assert.assertEquals(true,test);
    }

    @Test
    public  void  testcheckmail(){
        String email = "ngovietanh@gmail.com";
        Boolean result = Checkmail.checkmail(email);
        Assert.assertEquals(true,result);
    }
    @Test
    public  void testBcryptEncode(){
        String rawPass= "123123123";
        String newPass = "$2a$10$Bkz5.eRO9BVMqMybpqAeleFHulISCUaVLaYK2.pRaSHYHVLKw8Sb6";
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        Boolean result = encoder.matches(rawPass,newPass);
        Assert.assertEquals(true,result);
    }
}
