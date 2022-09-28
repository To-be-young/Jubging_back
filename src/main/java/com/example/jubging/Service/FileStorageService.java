package com.example.jubging.Service;

import com.example.jubging.Exception.FileStorageException;
import com.example.jubging.Exception.MyFileNotFoundException;
import com.example.jubging.Model.ImageInfo;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.ImageRepository;
import com.example.jubging.config.FileStorageProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class FileStorageService {
    private final FileStorageProperties fileStorageProperties;
//    private final Path fileStorageLocation;
    private final ImageRepository imageRepository;
    private final UserService userService;

//    public FileStorageService(FileStorageProperties fileStorageProperties, ImageRepository imageRepository, UserService userService) {
//        fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
//                .toAbsolutePath().normalize();
//        this.imageRepository = imageRepository;
//        this.userService = userService;
//
//        try {
//            Files.createDirectories(fileStorageLocation);
//        } catch (Exception ex) {
//            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
//        }
//    }

    private Path getFileStorageLocation() {
        return Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
//            Path targetLocation = fileStorageLocation.resolve(fileName);
            Path targetLocation = getFileStorageLocation().resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);


            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        log.info("[파일] 호출");
        try {
//            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Path filePath = getFileStorageLocation().resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    public void storeImageInDB(String fileName, String fileUri, User user){
        Optional<ImageInfo> optionalImage = imageRepository.findByUser(user);
        ImageInfo imageInfo;
        if (optionalImage.isPresent()) {
            imageInfo = optionalImage.get();
            imageInfo.setFileName(fileName);
            imageInfo.setFileUrl(fileUri);
        } else {
            imageInfo = ImageInfo.builder()
                    .fileName(fileName)
                    .fileUrl(fileUri)
                    .user(user)
                    .build();
        }

        imageRepository.save(imageInfo);
    }

    public String getFileName(HttpServletRequest request){

        User user = userService.getUser(request);
        ImageInfo imageInfo = imageRepository.findByUser(user).get();
        String fileName = imageInfo.getFileName();

        log.info(fileName);
        return fileName;
    }

}
