package com.techprimers.cache.resource;

import com.techprimers.cache.cache.UsersCache;
import com.techprimers.cache.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/users")
public class UsersResource {

    @Autowired
    UsersCache usersCache;

    @GetMapping(value = "/{name}")
    public Users getUser(@PathVariable final String name) {
        return usersCache.getUser(name);
    }

    @DeleteMapping(value = "/{name}")
    public void deleteUser(@PathVariable final String name) {
        usersCache.deleteUser(name);
    }

    @PostMapping(value = "/{name}")
    public Users addUser(@PathVariable final String name) {
        return usersCache.addUser(name);
    }

    @PutMapping(value = "/{name}/{teamName}")
    public Users updateUser(@PathVariable final String name, @PathVariable final String teamName) {
        return usersCache.updateUser(name, teamName);
    }

}

