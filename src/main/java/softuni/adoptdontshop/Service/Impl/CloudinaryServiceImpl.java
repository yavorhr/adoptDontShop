package softuni.adoptdontshop.Service.Impl;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import softuni.adoptdontshop.Model.Entity.Picture;
import softuni.adoptdontshop.Repository.PictureRepository;
import softuni.adoptdontshop.Service.CloudinaryService;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private CloudinaryService cloudinaryService;

    private final PictureRepository pictureRepository;

    public CloudinaryServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

//    public CloudinaryServiceImpl(Cloudinary cloudinary, CloudinaryService cloudinaryService, PictureRepository pictureRepository) {
//        this.cloudinary = cloudinary;
//        this.cloudinaryService = cloudinaryService;
//        this.pictureRepository = pictureRepository;
//    }

    @Override
    public Picture upload(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);

        try {
            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = cloudinary.
                    uploader().
                    upload(tempFile, Map.of());

            String url = uploadResult.getOrDefault(URL,
                    "https://i.pinimg.com/originals/c5/21/64/c52164749f7460c1ededf8992cd9a6ec.jpg");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

            return new Picture().
                    setPublicId(publicId).
                    setUrl(url);
        } finally {
            tempFile.delete();
        }
    }

    @Override
    public boolean delete(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId, Map.of());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public void savePicture(MultipartFile picture, String title) throws IOException {
        Picture pictureEntity = createPictureEntity(picture, title);
        pictureRepository.save(pictureEntity);
    }

    private Picture createPictureEntity(MultipartFile file, String title) throws IOException {
        final Picture uploaded = this.cloudinaryService.upload(file);

        return new Picture().
                setPublicId(uploaded.getPublicId()).
                setTitle(title).
                setUrl(uploaded.getUrl());
    }


}
