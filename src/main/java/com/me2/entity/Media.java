package com.me2.entity;

import lombok.Data;

@Data
public class Media {

    private String signature;

    private String format;

    private String resourceType;

    private String assetId;

    private String versionId;

    private String secureUrl;

    private String type;

    private String version;

    private String url;

    private String publicId;

    private String[] tags;

    private String folder;

    private String originalFilename;

    private String apiKey;

    private Long bytes;

    private Integer width;

    private String etag;

    private Boolean placeholder;

    private Integer height;
}