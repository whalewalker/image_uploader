package com.imageuploader.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(schema = "image")
public class Image {
    @Id
    private Long id;

    private String imageUrl;
}
