package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends CrudRepository<Rate, Integer> {
}
