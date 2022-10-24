package com.example.jubging.Controller;

import com.example.jubging.Model.User;
import com.example.jubging.DTO.Response.UploadFileResponse;
import com.example.jubging.Service.FileStorageService;
import com.example.jubging.Service.UserService;
import com.example.jubging.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class FileController {
    private final FileStorageService fileStorageService;
    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        log.info("업로드 파일");

        String fileName = fileStorageService.storeFile(file);
        log.info(fileName);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/image/downloadFile/")
                .path(fileName)
                .toUriString();
        log.info(fileDownloadUri);

        User user = userService.getUser(request);
//        User user = jwtTokenProvider.getUser(request);
        log.info(user.getUserId());
        fileStorageService.storeImageInDB(fileName, fileDownloadUri, user);

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(request, file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request) {
        log.info("[파일 호출] 컨트롤");
        String fileName = fileStorageService.getFileName(request);

        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
