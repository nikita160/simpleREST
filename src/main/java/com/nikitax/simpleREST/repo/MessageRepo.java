package com.nikitax.simpleREST.repo;

import com.nikitax.simpleREST.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepo extends JpaRepository<Message, Long> {
}
