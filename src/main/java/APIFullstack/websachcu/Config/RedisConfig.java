package APIFullstack.websachcu.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
        @Bean
        public LettuceConnectionFactory redisConnectionFactory(){
            return new LettuceConnectionFactory(new RedisStandaloneConfiguration("127.0.0.1", 6379));
        }

        @Bean
        public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
            RedisTemplate<Object, Object> template = new RedisTemplate<>();
            RedisSerializer<String> serializer = new StringRedisSerializer();
            template.setDefaultSerializer(serializer);
            template.setConnectionFactory(redisConnectionFactory);
            return template;
        }

}
