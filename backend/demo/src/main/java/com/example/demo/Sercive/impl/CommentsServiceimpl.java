package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Comments;

import java.util.List;

public interface CommentsServiceimpl {
    public void SaveComment(Comments comment);
    public void DeleteComment(Long id);
    public List<Comments> getComments();
    public List<Comments> getcomentsIdUser(Long id);

}
