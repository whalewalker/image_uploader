package com.imageuploader.service;

import com.imageuploader.controller.response.ApiResponse;
import com.imageuploader.controller.request.ImageRequest;
import com.imageuploader.exception.ImageObjectNullException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public interface ImageService {
    void uploadImageToCloudinary(File file, Map<String, Object> fileProperties) throws IOException;
    Map<?, ?> uploadImageToCloudinary(MultipartFile multipartFile, Map<String, Object> fileProperties) throws IOException;
    ApiResponse uploadImage(ImageRequest imageRequest) throws ImageObjectNullException;

}
