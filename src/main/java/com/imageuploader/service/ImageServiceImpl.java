package com.imageuploader.service;

import com.cloudinary.Cloudinary;
import com.imageuploader.controller.request.ImageRequest;
import com.imageuploader.controller.request.SingleImageRequest;
import com.imageuploader.controller.response.ApiResponse;
import com.imageuploader.exception.ImageObjectNullException;
import com.imageuploader.model.Image;
import com.imageuploader.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    private final Cloudinary cloudinary;
    private final ImageRepository imageRepository;

    public ImageServiceImpl(Cloudinary cloudinary, ImageRepository imageRepository) {
        this.cloudinary = cloudinary;
        this.imageRepository = imageRepository;
    }

    @Override
    public void uploadImageToCloudinary(File file, Map<String, Object> fileProperties) throws IOException {
        cloudinary.uploader().upload(file, fileProperties);
    }

    @Override
    public Map<?, ?> uploadImageToCloudinary(MultipartFile multipartFile, Map<String, Object> fileProperties) throws IOException {
        return cloudinary.uploader().upload(multipartFile.getBytes(), fileProperties);
    }

    @Override
    public ApiResponse uploadImage(ImageRequest imageRequest) throws ImageObjectNullException {
        if (imageRequest == null) throw new ImageObjectNullException("Image cannot be null");

        Image image = new Image();
        if (imageRequest.getFile() != null && imageRequest.getFile().isEmpty()){

            Map<String, Object> params = new HashMap<>();
            params.put("public_id", "/image_uploader".concat(extractFileName(imageRequest.getFile().getName())));
            params.put("overwrite", true);

            Map<?, ?> uploadResult;
            try {
               uploadResult= uploadImageToCloudinary(imageRequest.getFile(), params );

            } catch (IOException e) {
                return new ApiResponse("Failed", e.getMessage());
            }
            image.setImageUrl(String.valueOf(uploadResult.get("url")));
            imageRepository.save(image);
        }
        return new ApiResponse("Successful", "Image successfully uploaded");
    }

    private String extractFileName(String name) {
        log.info("File name ==> {}", name);
        return name.split("\\.")[0];
    }


    public void uploadImage(SingleImageRequest singleImageRequest) {
        Map<String,Object > params = new HashMap<>();
        params.put("public_id", "image_uploader");
        params.put("overwrite", true);

        try {
            uploadImageToCloudinary(singleImageRequest.getFile(), params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
