package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.ChapterPurchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterPurchaseRepository extends CrudRepository<ChapterPurchase, Integer> {
}
