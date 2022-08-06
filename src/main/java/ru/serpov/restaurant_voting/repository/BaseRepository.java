package ru.serpov.restaurant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.serpov.restaurant_voting.util.ValidationUtil;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.id=:id")
    int delete(@Param("id") int id);

    default void deleteExisted(int id) {
        ValidationUtil.checkModification(delete(id), id);
    }
}
