package com.bxp.MaysTech_Spring.service;

import com.bxp.MaysTech_Spring.dto.comment.CommentResponse;
import com.bxp.MaysTech_Spring.dto.comment.CreateCommentRequest;
import com.bxp.MaysTech_Spring.entity.Comment;
import com.bxp.MaysTech_Spring.entity.Product;
import com.bxp.MaysTech_Spring.entity.User;
import com.bxp.MaysTech_Spring.repository.CommentRepository;
import com.bxp.MaysTech_Spring.repository.ProductRepository;
import com.bxp.MaysTech_Spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public CommentResponse createComment(CreateCommentRequest request) {
        Comment comment = new Comment();

        User user = userRepository.getById(request.getUserId());
        Product product = productRepository.getById(request.getProductId());

        comment.setUser(user);
        comment.setProduct(product);
        comment.setContent(request.getContent());

        return convertEntityToDTO(commentRepository.save(comment));
    }

    public List<CommentResponse> getCommentsOfProduct(int prodId)
    {
        return commentRepository.findAllByProduct_Id(prodId).stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    CommentResponse convertEntityToDTO(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setProductId(comment.getProduct().getId());
        response.setContent(comment.getContent());
        response.setUsername(comment.getUser().getUsername());
        response.setUserAvatar(comment.getUser().getAvatar());
        return response;
    }
}
