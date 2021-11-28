package softuni.adoptdontshop.Service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.adoptdontshop.Model.Entity.Comment;
import softuni.adoptdontshop.Model.Entity.Dog;
import softuni.adoptdontshop.Model.Model.ServiceModel.CommentServiceModel;
import softuni.adoptdontshop.Model.Model.ViewModel.CommentViewModel;
import softuni.adoptdontshop.Repository.DogRepository;
import softuni.adoptdontshop.Service.CommentService;
import softuni.adoptdontshop.Web.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final DogRepository dogRepository;

    public CommentServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long id) {
        Optional<Dog> optDog = dogRepository.findById(id);

        if (optDog.isEmpty()) {
            throw new ObjectNotFoundException("Dog with id " + id + " was not found!");
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
        throw new UnsupportedOperationException("Not yet!");
    //TODO
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
