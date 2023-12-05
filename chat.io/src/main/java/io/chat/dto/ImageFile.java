package io.chat.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageFile {
    MultipartFile file;

    public ImageFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
