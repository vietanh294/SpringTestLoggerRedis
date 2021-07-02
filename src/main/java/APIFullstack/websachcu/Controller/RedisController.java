package APIFullstack.websachcu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping(value = "/add-key/{id}")
    public String addkey(@PathVariable(name = "id") String id){
        String tokem = UUID.randomUUID().toString();
        String value = "userId" +id;
        redisTemplate.opsForValue().set(tokem,value);
        return tokem;
    }
    @GetMapping(value = "/get-key/{token}")
    public String getKey(@PathVariable(name = "token") String token){
        String value = (String) redisTemplate.opsForValue().get(token);
        return value;
    }
}
