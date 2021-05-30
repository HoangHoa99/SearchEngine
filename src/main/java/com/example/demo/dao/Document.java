package com.example.demo.dao;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import lombok.Data;

@Entity
@Table(name = "documents")
@Data
@Indexed
public class Document {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field
    @Column(name = "document_title")
    private String documentTitle;

    @Field
    @Column(name = "document_des")
    private String documentDes;

    @Field
    @Column(name = "document_source")
    private String documentSource;

    @Field
    @Column(name = "date_create")
    private LocalDate dateCreate;

    @Field
    @Column(name = "review")
    private Integer review;

    @Field
    @Column(name = "downloaded")
    private Integer downloaded;
    
    @Field
    @Column(name = "click_count")
    private Integer clickCount;
}
