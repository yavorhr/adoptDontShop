package softuni.adoptdontshop.Service;

import softuni.adoptdontshop.Model.Model.ServiceModel.CommentServiceModel;
import softuni.adoptdontshop.Model.Model.ViewModel.CommentViewModel;

import java.util.List;

public interface CommentService {

    List<CommentViewModel> getComments(Long id );

    CommentViewModel createComment(CommentServiceModel commentServiceModel);

}
