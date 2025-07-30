package com.bxp.MaysTech_Spring.repository;

import com.bxp.MaysTech_Spring.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findAllByProduct_Id(Integer productId);
}
