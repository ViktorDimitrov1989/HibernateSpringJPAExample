package app.services;
import app.dto.add.AddPictureDto;

public interface PictureService {

    void save(AddPictureDto picture);

   void save(Iterable<AddPictureDto> pictures);

}
