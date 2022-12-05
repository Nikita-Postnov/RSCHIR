package com.example.cursov_p.repo;

import com.example.cursov_p.entity.User;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "select * from users where name = ?1 limit 1", nativeQuery = true)
    @Nullable
    User getByName(@NonNull String name);
}
