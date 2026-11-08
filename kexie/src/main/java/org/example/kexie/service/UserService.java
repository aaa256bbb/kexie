package org.example.kexie.service;

import org.example.kexie.entity.User;

import java.util.List;

public interface UserService {
    boolean add(User user);

    boolean remove(Long id);

    boolean update(User user);

    User getById(Long id);

    List<User> list();
}
