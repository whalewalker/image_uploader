package com.imageuploader.controller.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class ImageRequest {
    private MultipartFile file;
}
