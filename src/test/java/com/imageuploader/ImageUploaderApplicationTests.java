package com.imageuploader;

import com.cloudinary.utils.ObjectUtils;
import com.imageuploader.controller.request.ImageRequest;
import com.imageuploader.controller.request.SingleImageRequest;
import com.imageuploader.exception.ImageObjectNullException;
import com.imageuploader.service.ImageService;
import com.imageuploader.service.ImageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
class ImageUploaderApplicationTests {

    @Autowired
    private ImageService imageService;

//    @Test
//    void uploadImageMultiPartFile(){
//
//        File file = new File("/Users/ismailabdulahi/Documents/image-uploader/src/main/resources/static/image/Screenshot 2022-02-03 at 16.34.31.png");
//        assertThat(file.exists()).isTrue();
//
//        SingleImageRequest singleImageRequest = new SingleImageRequest();
//        singleImageRequest.setFile(file);
//
//        imageService.uploadImage(singleImageRequest);
//    }

    @Test
    @DisplayName("Upload multiple image test")
    void uploadImageTest() throws IOException, ImageObjectNullException {

        Path path = Paths.get("/Users/ismailabdulahi/Documents/image-uploader/src/main/resources/static/image/Screenshot 2022-02-03 at 16.34.31.png");
        MultipartFile multipartFile = new MockMultipartFile("2022-02-03 at 16.34.31.png", "Screenshot 2022-02-03 at 16.34.31.png", "img/png", Files.readAllBytes(path));

        log.info("Multipart Object created --> {}", multipartFile);
        assertThat(multipartFile).isNotNull();

        ImageRequest imageRequest = new ImageRequest();
        imageRequest.setFile(multipartFile);

        log.info("File name --> {}", imageRequest.getFile().getOriginalFilename());
        imageService.uploadImage(imageRequest);
        assertThat(imageRequest.getFile().getName()).isEqualTo("2022-02-03 at 16.34.31.png");
    }


}
