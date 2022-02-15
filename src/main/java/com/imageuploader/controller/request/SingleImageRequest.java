package com.imageuploader.controller.request;

import lombok.Data;

import java.io.File;

@Data
public class SingleImageRequest {
    private File file;
}
