package uz.developers.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.developers.auth.dto.ResponseDto;
import uz.developers.auth.entity.Image;
import uz.developers.auth.entity.Users;
import uz.developers.auth.repo.ImageRepo;
import uz.developers.auth.repo.UserRepo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepo imageRepo;
    private final UserRepo userRepo;

    public ResponseEntity<?> save(MultipartFile file, Integer userId) {
        Optional<Users> optionalUser = userRepo.findByIdAndIsActive(userId, true);
        if (optionalUser.isPresent()) {

            Users user = optionalUser.get();
            Image image = null;
            try {
                image = new Image(0, user.getPhoneNumber(), file.getContentType(), compressImage(file.getBytes()));
            } catch (IOException e) {
                return ResponseEntity.ok(ResponseDto.getSuccess(205, "file is invalid"));

            }
            user.setProfilePicture(image);
            image.setId(userRepo.save(user).getProfilePicture().getId());
            imageRepo.save(image);
            return ResponseEntity.ok(new ResponseDto(200, "ok", user.getPhoneNumber()));
        } else {
            return ResponseEntity.ok(ResponseDto.getSuccess(205, "user not found"));
        }
    }

    public ResponseEntity<?> getPhoto(Integer userId) {
        Optional<Users> optionalUsers = userRepo.findByIdAndIsActive(userId, true);
        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            if (users.getProfilePicture() != null) {
                Image image = users.getProfilePicture();
                return ResponseEntity.ok().contentType(MediaType.valueOf(image.getType())).body(decompressImage(image.getImageData()));
            } else {
                return ResponseEntity.ok(ResponseDto.getSuccess(205, "user has no profile picture"));
            }
        } else {
            return ResponseEntity.ok(ResponseDto.getSuccess(205, "user not found"));
        }
    }

    public byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }

    public byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }


}
