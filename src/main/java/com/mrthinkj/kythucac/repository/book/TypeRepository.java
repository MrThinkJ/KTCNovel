package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends CrudRepository<Type, Integer> {
    @Query(value = "select type_name from type", nativeQuery = true)
    List<String> findNameOfType();
}
