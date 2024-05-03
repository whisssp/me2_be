package com.me2.service;

import com.me2.entity.Media;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CloudinaryService {

    List<Media> upload(List<MultipartFile> file);


}