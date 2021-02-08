package com.example.SpringAss3APJ2.config;

import com.example.SpringAss3APJ2.model.Authority;
import com.example.SpringAss3APJ2.model.Role;
import com.example.SpringAss3APJ2.repo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Service("userDetailsService")
public class UserDitails implements UserDetailsService {
    @Autowired
    UserService userService;

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
    {
        System.out.println("loadUserByUsername:"+username);
        com.example.SpringAss3APJ2.model.User user = userService.findByUserName(username);
        System.out.println("role   : "+user.getRole().getName());
        Set<Authority> authoritySet = user.getRole().getAuthorities();
        System.out.println("email  : "+user.getEmail());
        Role role = user.getRole();
        Set<Authority> authorities = role.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Authority authority : authorities)
        {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add( grantedAuthority );
        }

        GrantedAuthority roleAuthority = new SimpleGrantedAuthority( role.getName() );
        grantedAuthorities.add( roleAuthority );

        return buildUserForAuthentication(user, grantedAuthorities);
    }


    private org.springframework.security.core.userdetails.User buildUserForAuthentication(com.example.SpringAss3APJ2.model.User user, List<GrantedAuthority> authorities)
    {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),true, true, true, true, authorities);
    }
}
