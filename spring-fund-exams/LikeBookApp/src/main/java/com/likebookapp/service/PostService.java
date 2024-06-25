package com.likebookapp.service;

import com.likebookapp.model.dto.PostAddDTO;
import com.likebookapp.model.dto.PostInfoDTO;
import com.likebookapp.model.dto.UserLoginDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostService {

    void addPost(PostAddDTO postData);

    @Transactional
    List<PostInfoDTO> getUserPosts(Long userId);

    @Transactional
    List<PostInfoDTO> getOthersPosts(Long userId);

    @Transactional
    void likePost(Long userId, Long postId);

    void deletePost(Long userId, Long postId);
}
