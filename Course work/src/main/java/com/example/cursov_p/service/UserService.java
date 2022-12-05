package com.example.cursov_p.service;

import com.example.cursov_p.entity.User;
import com.example.cursov_p.repo.UserRepo;
import lombok.NonNull;
import lombok.val;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Service
public class UserService extends AbsService<User, UserRepo> implements UserDetailsService {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(@NonNull UserRepo repo) { super(repo); }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        val user = repo.getByName(name);
        if (user == null) throw new UsernameNotFoundException(null);
        return user;
    }

    public boolean registerUser(@NonNull User user) {
        if (user.getName().isEmpty() || user.getPassword().isEmpty())
            return false;
        return createOrUpdate(user.setPasswordCloning(passwordEncoder.encode(user.getPassword())));
    }
}
