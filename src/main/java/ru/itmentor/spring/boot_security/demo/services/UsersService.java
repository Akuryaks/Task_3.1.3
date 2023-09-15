package ru.itmentor.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repositories.UsersRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class UsersService implements UserDetailsService {
    private final UsersRepository usersRepository;
    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll(){
        return usersRepository.findAll();
    }

    public Optional<User> findOne(long id){
        Optional<User> user = usersRepository.findById(id);
        return user;
    }

    @Transactional
    public void saveUser(User user){
        Optional<User> userFromDB = usersRepository.findByEmail(user.getUsername());

        if (userFromDB.isPresent()){
            return;
        }

        usersRepository.save(user);
    }

    @Transactional
    public void updateUser(long id, User user){
        user.setId(id);
        usersRepository.save(user);
    }

    @Transactional
    public void deleteUser(long id){
        usersRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    }
}
