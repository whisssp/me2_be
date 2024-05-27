package com.me2.service.impl;

import com.cloudinary.Cloudinary;
import com.me2.constants.ApplicationConstants;
import com.me2.constants.CloudOptionPropertyConstants;
import com.me2.exception.CustomException;
import com.me2.global.enums.EnumError;
import com.me2.service.CloudinaryService;
import com.me2.util.CloudUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public List<Object> upload(List<MultipartFile> files) {
        List<Object> medias = new ArrayList<>();
        files.parallelStream().forEach(f -> {
            try {
                medias.add(cloudinary.uploader()
                        .upload(f.getBytes(),
                                CloudUtil.toMapOption(CloudOptionPropertyConstants.OPTION_FOLDER,
                                        ApplicationConstants.PUBLIC))
                );
            } catch (IOException e) {
                throw new CustomException(EnumError.UPLOAD_MEDIA_FAILED);
            }
        });
        return medias;
    }
}