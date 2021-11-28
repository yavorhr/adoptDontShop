package softuni.adoptdontshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.adoptdontshop.Model.Entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
