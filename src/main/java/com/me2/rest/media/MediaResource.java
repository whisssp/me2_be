package com.me2.rest.media;

import com.me2.entity.Media;
import com.me2.service.CloudinaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        return new ResponseEntity<>(cloudinaryService.upload(file), HttpStatus.OK);
    }
}