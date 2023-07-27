package com.vandejr.springjava.repositories;

import com.vandejr.springjava.entities.Anime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimesRepository extends CrudRepository<Anime, Long> {

    @Query(value = "select a.id, a.englishTitle, a.japTitle, a.meanScore from animes a")
    List<Object[]> getAll();

    Anime save(Anime anime);

    Optional<Anime> findById(Long aLong);

    void deleteById(Long aLong);
}
