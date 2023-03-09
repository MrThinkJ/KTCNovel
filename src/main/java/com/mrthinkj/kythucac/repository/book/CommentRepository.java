package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Comment findById(int commentId);
}
