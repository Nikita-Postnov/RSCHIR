package com.example.cursov_p.service;

import com.example.cursov_p.entity.IEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public abstract class AbsService<E extends IEntity, R extends JpaRepository<E, Integer>> {
    @NonNull
    protected final R repo;

    public boolean createOrUpdate(@NonNull E entity) {
        repo.save(entity);
        return Objects.equals(repo.findById(entity.getId()).orElse(null), entity);
    }

    public List<E> getAll() { return repo.findAll(); }

    @Nullable
    public E getById(Integer id) { return repo.findById(id).orElse(null); }

    public boolean deleteById(Integer id) {
        repo.deleteById(id);
        return !repo.existsById(id);
    }
}
