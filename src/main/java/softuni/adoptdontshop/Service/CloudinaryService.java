package softuni.adoptdontshop.Service;

import org.springframework.web.multipart.MultipartFile;
import softuni.adoptdontshop.Model.Entity.Picture;

import java.io.IOException;

public interface CloudinaryService {

    Picture upload(MultipartFile file) throws IOException;

    boolean delete(String publicId);

    void savePicture(MultipartFile picture, String title) throws IOException;
}
