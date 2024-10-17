package br.com.cpsb.service;

import java.util.List;
import java.util.Optional;

public interface ServiceDto<Identifier, Entity> {
    List<Entity> getAll();
    Optional<Entity> getById(Identifier id);
    void post(Entity entity);
    void put(Identifier id, Entity entity);
    void delete(Identifier id);
}
