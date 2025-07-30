package com.bxp.MaysTech_Spring.controller;

import com.bxp.MaysTech_Spring.dto.ApiResponse;
import com.bxp.MaysTech_Spring.dto.comment.CommentResponse;
import com.bxp.MaysTech_Spring.dto.comment.CreateCommentRequest;
import com.bxp.MaysTech_Spring.exception.MyApiResponse;
import com.bxp.MaysTech_Spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/comments")
    public ApiResponse<CommentResponse> createComment(@RequestBody CreateCommentRequest request) {
        ApiResponse<CommentResponse> response = new ApiResponse<>();
        response.setStatusCode(MyApiResponse.CREATED.getCode());
        response.setMessage(MyApiResponse.CREATED.getMessage());
        response.setData(commentService.createComment(request));
        return response;
    }

    @GetMapping("/comments/{prodId}")
    public ApiResponse<List<CommentResponse>> getCommentOfProduct(@PathVariable("prodId") int prodId)
    {
        ApiResponse<List<CommentResponse>> response = new ApiResponse<>();
        response.setStatusCode(MyApiResponse.OK.getCode());
        response.setMessage(MyApiResponse.OK.getMessage());
        response.setData(commentService.getCommentsOfProduct(prodId));
        return response;
    }

}
