package org.hydra.hyperion_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hydra.hyperion_backend.pojo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {
    private User user;

    @Override
    public String getUsername() {
        return user.getTel() + "," + user.getRole();
    }

    @Override
    public String getPassword() {
        return user.getPass();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getState().equals("ACTIVE");
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> res = new ArrayList<>();
        res.add(new SimpleGrantedAuthority(user.getRole()));
        if (user.getRole().equals("ADMIN")) {
            res.add(new SimpleGrantedAuthority("MERCHANT"));
            res.add(new SimpleGrantedAuthority("CONSUMER"));
        }
        System.out.println("getAuthorities@LoginUser res: " + res);
        return res;
    }
}
