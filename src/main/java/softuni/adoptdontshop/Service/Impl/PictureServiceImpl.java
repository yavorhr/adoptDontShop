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
    private final DogRepository dogRepository;

    public PictureServiceImpl(PictureRepository pictureRepository, DogRepository dogRepository) {
        this.pictureRepository = pictureRepository;
        this.dogRepository = dogRepository;
    }

    @Override
    public void deletePicture(String publicId, Long id) {

        Dog dog = dogRepository.findById(id).orElseThrow();

        List<Picture> collect = dog.getPictures();
        collect.forEach(picture -> {
            if (picture.getPublicId().equals(publicId)) {
               picture.setDog(null);
            }
        });

        dog.setPictures(collect);



        dogRepository.save(dog);
        pictureRepository.deleteAllByPublicId(publicId);
    }
}
