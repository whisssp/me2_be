package com.me2.service.impl;

import com.cloudinary.Cloudinary;
import com.me2.exception.ErrorHandler;
import com.me2.global.enums.EnumError;
import com.me2.service.CloudinaryService;
import com.me2.util.CloudUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public Map upload(MultipartFile file) {
        try{
            return this.cloudinary
                    .uploader()
                    .upload(file.getBytes(),
                    CloudUtil.toMapOption("folder", "public"));
        }catch (IOException io){
            throw new ErrorHandler(EnumError.UPLOAD_MEDIA_FAILED);
        }
    }
}