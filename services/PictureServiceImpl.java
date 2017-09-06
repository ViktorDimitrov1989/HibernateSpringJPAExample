package app.services;

import app.dto.add.AddPictureDto;
import app.models.Picture;
import app.repositories.PictureRepository;
import app.utils.DtoMappingUtil;
import app.utils.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PictureServiceImpl implements PictureService {

    private PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void save(AddPictureDto picture) {
    }

    @Override
    public void save(Iterable<AddPictureDto> pictures) {

        for (AddPictureDto picture : pictures) {
            Picture pictureToAdd = DtoMappingUtil.convert(picture, Picture.class);

            if(ValidationUtil.isValid(pictureToAdd)){
                this.pictureRepository.saveAndFlush(pictureToAdd);
                System.out.println(String.format("Successfully imported Picture %s.", pictureToAdd.getPath()));
            }else{
                System.out.println("Error: Invalid data.");
            }
        }
    }

}
