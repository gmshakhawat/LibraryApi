package com.softrear.libraryapi.entity;

import com.softrear.libraryapi.model.AuditModel;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Book extends AuditModel {

    @Id
    @SequenceGenerator(name = "VENDOR_SEQ", sequenceName = "VENDOR_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "VENDOR_SEQ")
    private Long id;
    private String title,edition,author;
    private Long available,copy;

}
