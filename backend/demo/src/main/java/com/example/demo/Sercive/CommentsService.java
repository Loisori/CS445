package com.example.demo.Sercive;

import com.example.demo.Entity.Comments;
import com.example.demo.Repository.CommentsRepository;
import com.example.demo.Sercive.impl.CommentsServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentsService implements CommentsServiceimpl {
    @Autowired
    private CommentsRepository commentsRepository;
    @Override
    public void DeleteComment(Long id) {
commentsRepository.deleteById(id);
    }

    @Override
    public void SaveComment(Comments comment) {
commentsRepository.save(comment);
    }

    @Override
    public List<com.example.demo.Entity.Comments> getComments() {
      return commentsRepository.findAll();
    }

    @Override
    public List<Comments> getcomentsIdUser(Long id) {
    return commentsRepository.findByuserId(id);
    }
}
