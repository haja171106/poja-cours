package com.school.haja.repository;

import com.school.haja.repository.model.JUser;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JUserRepository extends JpaRepository<JUser, UUID> {}
