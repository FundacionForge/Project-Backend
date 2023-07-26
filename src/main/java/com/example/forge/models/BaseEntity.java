package com.example.forge.models;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(updatable=false, name = "created_at")
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private Date createdAt;
    
  @Column(name = "updated_at")
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private Date updatedAt;
    
  @PrePersist
  protected void onCreate(){
	this.createdAt = new Date();
  }
    
  @PreUpdate
  protected void onUpdate(){
	this.updatedAt = new Date();
  }
}
