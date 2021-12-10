package softuni.adoptdontshop.Service.Impl;

import org.springframework.stereotype.Service;
import softuni.adoptdontshop.Model.Entity.Dog;
import softuni.adoptdontshop.Model.Entity.Picture;
import softuni.adoptdontshop.Repository.DogRepository;
import softuni.adoptdontshop.Repository.PictureRepository;
import softuni.adoptdontshop.Service.PictureService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;

    }

    @Override
    public void deletePicture(String publicId) {

    pictureRepository.deleteAllByPublicId(publicId);



    }
}
