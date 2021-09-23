package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public Base get(Integer id) {
        return memory.get(id);
    }

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (k, v) -> {
            if (v.getVersion() != model.getVersion()) {
                throw new RuntimeException("Versions error");
            }
            Base udpBase = new Base(v.getId(), v.getVersion() + 1);
            udpBase.setName(model.getName());
            return udpBase;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }
}