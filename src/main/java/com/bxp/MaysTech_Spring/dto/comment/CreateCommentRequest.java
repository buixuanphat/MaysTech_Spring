package com.bxp.MaysTech_Spring.dto.comment;

import com.bxp.MaysTech_Spring.entity.Product;
import com.bxp.MaysTech_Spring.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

public class CreateCommentRequest {

    private int userId;
    private int productId;
    private String content;

    public CreateCommentRequest() {
    }

    public CreateCommentRequest(int userId, int productId, String content) {
        this.userId = userId;
        this.productId = productId;
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
