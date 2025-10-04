//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Comments;
import java.util.List;

public interface CommentsServiceimpl {
    void deleteAllByProjectId(Long projectId);

    List<Comments> getComments();

    void SaveComment(Comments comments);

    void DeleteComment(Long id);

    void UpdateComment(Long userid, String content, Long id_comment);

    Comments getCommentById(Long id);

    List<Comments> getCommentsByProjectId(Long projectId);
}
