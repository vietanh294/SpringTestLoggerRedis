package APIFullstack.websachcu.Service;

import APIFullstack.websachcu.Entity.UserEntity;
import APIFullstack.websachcu.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findAllByUserPhoneOrUserEmail(username,username);
        if (userEntity == null){
            throw new RuntimeException("User " + username + " not found!");
        }
        List<GrantedAuthority> grandList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getRole());
        grandList.add(authority);
        UserDetails userDetails = new User(userEntity.getUserPhone(),userEntity.getUserPassword(),grandList);
        return userDetails;
    }
}
