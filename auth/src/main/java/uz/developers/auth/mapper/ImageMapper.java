package uz.developers.auth.mapper;

import org.springframework.stereotype.Component;
import uz.developers.auth.dto.ImageDto;
import uz.developers.auth.entity.Image;


@Component
public class ImageMapper {
    public Image toEntity(ImageDto imageDto) {
        Image image = new Image();
        image.setId(imageDto.getId());
        image.setName(imageDto.getName());
        image.setType(imageDto.getType());
        image.setImageData(imageDto.getImageData());
        return image;
    }

    public ImageDto toDto(Image image) {
        ImageDto imageDto = new ImageDto();

        imageDto.setId(image.getId());
        imageDto.setName(image.getName());
        imageDto.setType(imageDto.getType());
        imageDto.setImageData(imageDto.getImageData());

        return imageDto;
    }
}
