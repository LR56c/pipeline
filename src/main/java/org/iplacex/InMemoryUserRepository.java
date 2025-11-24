package org.iplacex;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, String> users = new HashMap<>();

    @PostConstruct
    public void init() {
        users.put("1", "Juan");
    }

    @Override
    public String findById(String id) {
        return users.get(id);
    }

    public void save(String id, String name) {
        users.put(id, name);
    }
}

