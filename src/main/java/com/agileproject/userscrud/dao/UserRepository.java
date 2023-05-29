package com.agileproject.userscrud.dao;

import com.agileproject.userscrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> { //provides all crud operations, manages User objects

}
