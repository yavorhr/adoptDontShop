package softuni.adoptdontshop.Service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import softuni.adoptdontshop.Model.Entity.Picture;
import softuni.adoptdontshop.Model.Entity.PictureUser;

import java.io.IOException;

public interface CloudinaryService {

    PictureUser upload(MultipartFile file) throws IOException;

    boolean delete(String publicId);

    void savePicture(MultipartFile picture, String title,String username) throws IOException;


    void saveDogPicture(MultipartFile picture, String title, Long id) throws IOException;
}
