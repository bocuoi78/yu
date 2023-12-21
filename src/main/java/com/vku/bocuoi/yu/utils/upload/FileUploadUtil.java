package com.vku.bocuoi.yu.utils.upload;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, MultipartFile file) {
        File uploadPath = new File(uploadDir);

        if (!uploadPath.exists()) {
            uploadPath.mkdir();
        }

        try {
            FileCopyUtils.copy(file.getBytes(), new File(uploadDir + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}