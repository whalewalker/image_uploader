package com.imageuploader.controller;

import com.imageuploader.controller.request.ImageRequest;
import com.imageuploader.controller.response.ApiResponse;
import com.imageuploader.exception.ImageObjectNullException;
import com.imageuploader.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ImageUploadController {

    private final ImageService imageService;

    public ImageUploadController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadImage(@RequestBody ImageRequest imageRequest){
        try {
            return new ResponseEntity<>(imageService.uploadImage(imageRequest), HttpStatus.CREATED);
        } catch (ImageObjectNullException e) {
            return new ResponseEntity<>(new ApiResponse("Failed", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
