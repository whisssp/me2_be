package com.me2.rest.media;

import com.me2.entity.Media;
import com.me2.service.CloudinaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v0/media")
public class MediaResource {

    private final CloudinaryService cloudinaryService;

    public MediaResource(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping({"/admin/upload/image", "/public/upload/image"})
 public ResponseEntity<?> uploadImageForAdmin(@RequestParam("images") List<MultipartFile> file) {
        List<Media> data = this.cloudinaryService.upload(file);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}