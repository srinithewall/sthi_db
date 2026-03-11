package com.sthi.service;

import java.util.List;

import com.sthi.model.Users;

import com.sthi.repo.UsersRepository;

public interface UserService {
    List<UsersRepository.UserSummary> getAllUsers();

    Users getUserById(int id);

    Users createUser(Users user);

    Users updateUser(int id, Users userDetails);

    void deleteUser(int id);
}
