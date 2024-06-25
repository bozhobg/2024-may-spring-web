package com.likebookapp.service.impl;

import com.likebookapp.model.dto.PostAddDTO;
import com.likebookapp.model.dto.PostInfoDTO;
import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.service.PostService;
import com.likebookapp.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MoodRepository moodRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    @Autowired
    public PostServiceImpl(
            PostRepository postRepository,
            MoodRepository moodRepository,
            UserRepository userRepository,
            CurrentUser currentUser
    ) {
        this.postRepository = postRepository;
        this.moodRepository = moodRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    @Override
    public void addPost(PostAddDTO postData) {
        Mood mood = this.moodRepository.findByName(postData.getMood()).orElse(null);
        User user = this.userRepository.findById(currentUser.getId()).orElse(null);

        if (mood == null || user == null) return;

        this.postRepository.save(new Post()
                .setContent(postData.getContent())
                .setMood(mood)
                .setUser(user));
    }

    @Override
    public List<PostInfoDTO> getUserPosts(Long userId) {
        List<PostInfoDTO> allByUserId = this.postRepository.findAllByUser_Id(userId)
                .stream()
                .map(this::mapToPostInfo)
                .toList();

        return allByUserId;
    }

    @Override
    public List<PostInfoDTO> getOthersPosts(Long userId) {
        User user = this.userRepository.findById(userId).get();

        List<PostInfoDTO> allByUserIdNot = this.postRepository.findAllByUser_IdNot(userId)
                .stream()
                .map(e -> {
                    PostInfoDTO dto = this.mapToPostInfo(e);
                    if (e.getUserLikes().contains(user)) dto.setLikedByUser(true);

                    return dto;
                })
                .toList();

        return allByUserIdNot;
    }

    @Override
    public void likePost(Long userId, Long postId) {
        Post post = this.postRepository.findById(postId).orElse(null);
        User user = this.userRepository.findById(userId).orElse(null);

        if (post == null || user == null) return;

        post.getUserLikes().add(user);

        this.postRepository.save(post);
    }

    @Override
    public void deletePost(Long userId, Long postId) {
        Post post = this.postRepository.findById(postId).orElse(null);
        User user = this.userRepository.findById(userId).orElse(null);

        if (post == null || user == null) return;

        this.postRepository.delete(post);
    }


    private PostInfoDTO mapToPostInfo(Post post) {

        return new PostInfoDTO()
                .setContent(post.getContent())
                .setMood(post.getMood().getName().name())
                .setLikes(post.getUserLikes().size())
                .setId(post.getId())
                .setAuthor(post.getUser().getUsername());
    }
}
