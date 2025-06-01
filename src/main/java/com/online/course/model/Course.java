package com.online.course.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "course_id")
    private Long id;

    @Column(length = 100)
    private String title;
    
    @Column(length = 400)
    private String subtitle;
    
   
    @Column(length = 3000)
    private String short_description;
    
    @Column(length = 80)
    private String created_by; 
    
    @Column(length = 20)
    private String language;
    
    @Column(precision = 5, scale = 2) 
    private BigDecimal rating;
    
    @Column(precision = 5, scale = 2) 
    private int students;
    
    private LocalDate start_date;
    private LocalDate end_date;
    
    //
    private String imageName;
    private String imageType;
    
    @Lob
    private byte[] imageData;
    	
     
}