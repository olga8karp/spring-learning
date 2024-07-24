package org.basicsecurity.repository;

import org.basicsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

    User findUserByUsername(String username);

}
