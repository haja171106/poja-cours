package com.school.haja.repository;

import com.school.haja.repository.model.JSubscription;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JSubscriptionRepository extends JpaRepository<JSubscription, UUID> {}
