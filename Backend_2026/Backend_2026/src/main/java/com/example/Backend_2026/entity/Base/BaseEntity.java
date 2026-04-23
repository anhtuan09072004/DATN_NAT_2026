package com.example.Backend_2026.entity.Base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Column(name = "tao_luc")
    private LocalDateTime taoLuc;
    @LastModifiedDate
    @Column(name = "cap_nhat_luc")
    private LocalDateTime capNhatLuc;
    @CreatedBy
    @Column(name = "tao_boi")
    private String taoBoi;
    @LastModifiedBy
    @Column(name = "cap_nhat_boi")
    private String capNhatBoi;
    @Column(name = "da_xoa")
    private Boolean daXoa;

    @PrePersist
    public void prePersist(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        taoLuc = LocalDateTime.now();
        capNhatLuc = LocalDateTime.now();
        taoBoi = authentication != null ? authentication.getName() : "tuan";
        capNhatBoi = authentication != null ? authentication.getName() : "tuan";
        daXoa = false;

    }
    @PreUpdate
    public void preUpdate(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        capNhatLuc = LocalDateTime.now();
        capNhatBoi = authentication != null ? authentication.getName() : "nam";
    }

}
