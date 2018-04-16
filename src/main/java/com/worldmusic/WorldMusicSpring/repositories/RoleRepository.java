package com.worldmusic.WorldMusicSpring.repositories;

import com.worldmusic.WorldMusicSpring.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
