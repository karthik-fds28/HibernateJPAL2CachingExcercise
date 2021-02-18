package com.techprimers.cache.cache;

import com.techprimers.cache.model.Users;
import com.techprimers.cache.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UsersCache {

    @Autowired
    UsersRepository usersRepository;

    //if the output of findByName is null that value will not be cached
    @Cacheable(value = "usersCache", key = "#name", unless = "#result==null")
    //@Cacheable(value = "usersCache", key = "#name")
    public Users getUser(String name) {
        System.out.println("Retrieving from Database for name: " + name);
        return usersRepository.findByName(name);
    }

    @CacheEvict(value = "usersCache", key = "#name")
    @Transactional
    public void deleteUser(String name) {
        System.out.println("Deleting from Database for name: " + name);
        usersRepository.deleteByName(name);
    }

    @Transactional
    public Users addUser(String name) {
        Users user = new Users();
        user.setName(name);
        Users save = usersRepository.save(user);
        return save;
    }

    @CachePut(value = "usersCache", key = "#name", unless = "#result==null")
    public Users updateUser(String name, String teamName) {
        Users byName = usersRepository.findByName(name);
        if (byName == null) {
            return null;
        }
        byName.setTeamName(teamName);
        Users save = usersRepository.save(byName);
        return save;
    }

}
