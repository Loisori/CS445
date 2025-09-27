//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive;

import com.example.demo.Entity.Comments;
import com.example.demo.Repository.CommentsRepository;
import com.example.demo.Sercive.impl.CommentsServiceimpl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsService implements CommentsServiceimpl {

    private final CommentsRepository commentsRepository;


    public List<Comments> getCommentsByProjectId(Long projectId) {
        return this.commentsRepository.findByProjectId(projectId);
    }

    public Comments getCommentById(Long id) {
        return (Comments) this.commentsRepository.findById(id).orElse(null);
    }

    public void UpdateComment(Long userid, String content, Long id_comment) {
        int a = this.commentsRepository.updateComment(userid, content, id_comment);
        if (a > 0) {
            System.out.println("Cập nhật bình luận thành công!");
        } else {
            System.out.println("Không tìm thấy bình luận với ID này.");
        }

    }

    public void deleteAllByProjectId(Long id) {
        this.commentsRepository.deleteByProjectId(id);
    }

    public void DeleteComment(Long id) {
        this.commentsRepository.deleteById(id);
    }

    public void SaveComment(Comments comment) {
        this.commentsRepository.save(comment);
    }

    public List<Comments> getComments() {
        return this.commentsRepository.findAll();
    }
}
