package softuni.adoptdontshop.Service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.adoptdontshop.Model.Entity.Comment;
import softuni.adoptdontshop.Model.Entity.Dog;
import softuni.adoptdontshop.Model.Model.ServiceModel.CommentServiceModel;
import softuni.adoptdontshop.Model.Model.ViewModel.CommentViewModel;
import softuni.adoptdontshop.Repository.CommentRepository;
import softuni.adoptdontshop.Repository.DogRepository;
import softuni.adoptdontshop.Repository.UserRepository;
import softuni.adoptdontshop.Service.CommentService;
import softuni.adoptdontshop.Web.exception.GlobalNotFoundException;


import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final DogRepository dogRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(DogRepository dogRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.dogRepository = dogRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long id) {
        Optional<Dog> optDog = dogRepository.findById(id);

        if (optDog.isEmpty()) {
            throw new GlobalNotFoundException();
        }

        return optDog
                .get()
                .getComments()
                .stream()
                .map(this::mapToCommentView)
                .collect(Collectors.toList());
    }

    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) {

        var dog = dogRepository.findById(commentServiceModel.getDogId())
                .orElseThrow(GlobalNotFoundException::new);

        var user = userRepository.findByUsername(commentServiceModel.getCreator())
                .orElseThrow(GlobalNotFoundException::new);

        Comment newComment = new Comment();
        newComment.setApproved(false);
        newComment.setTextContent(commentServiceModel.getMessage());
        newComment.setCreated(Instant.now());
        newComment.setDog(dog);
        newComment.setUser(user);

        Comment savedComment = commentRepository.save(newComment);
        return mapToCommentView(savedComment);
    }

    private CommentViewModel mapToCommentView(Comment comment) {
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel
                .setId(comment.getId())
                .setFirstName(comment.getUser().getFirstName())
                .setLastName(comment.getUser().getLastName())
                .setMessage(comment.getTextContent())
                .setCreated(comment.getCreated())
                .setCanApprove(true)
                .setCanDelete(true);

        return commentViewModel;
    }
}
