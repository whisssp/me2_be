package com.me2.service;

import com.me2.entity.Media;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;


public interface CloudinaryService {

List<Object> upload(List<MultipartFile> file);


}