package com.vku.bocuoi.yu.service.impl;

import com.vku.bocuoi.yu.model.dto.response.UploadResponseDto;
import com.vku.bocuoi.yu.service.FileService;
import com.vku.bocuoi.yu.utils.DateUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {
    @Value("${file.upload-dir}")
    private String uploadDir;
    private final String imageDir = "src/main/resources/static/images/";

    @Override
    public Resource uploadImage(HttpServletRequest request, MultipartFile file, String fileName) {
        saveUserProfileImage(fileName, file);
        Path filePath = Paths.get(imageDir).resolve(fileName).normalize();
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not load image");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Malformed URL: " + filePath.toString());
        }
    }

    @Override
    public Resource getImage(String fileName) {
//        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
//        String fileName = student.getImage();
        Path filePath = Paths.get(imageDir).resolve(fileName);
        try {
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not load the image: " + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not load the image: " + fileName, e);
        }
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            return saveFile(file);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public Resource getFile(UploadResponseDto uploadResponseDto) {
        String filePath = uploadResponseDto.getFilePath();
        File file = new File(uploadDir + filePath);
        if (file.exists()) {
            return new FileSystemResource(file);
        }
        return null;
    }

    private String saveFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String encodedFileName = encodeFileName(fileName);
        String yearMonthDayPath = DateUtils.getCurrentYearMonthDayPath();
        createDirectoriesIfNotExists(uploadDir + yearMonthDayPath);
        try {
            FileCopyUtils.copy(file.getBytes(), Paths.get(uploadDir + yearMonthDayPath).resolve(encodedFileName).toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Paths.get(yearMonthDayPath, encodedFileName).toString();
    }

    private void saveUserProfileImage(String encodedFileName, MultipartFile file) {
        // Xử lý lưu trữ file
//        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), Paths.get(imageDir).resolve(encodedFileName).toFile());
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý lỗi khi lưu trữ file
        }
//        return fileName;
    }

    private void createDirectoriesIfNotExists(String... paths) {
        for (String path : paths) {
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }
        }
    }

    private String encodeFileName(String originalFileName) {
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uniqueString = originalFileName + System.nanoTime();
        String md5Hash = DigestUtils.md5Hex(uniqueString);
        return md5Hash + fileExtension;
    }
}