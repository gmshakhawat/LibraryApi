package com.softrear.libraryapi.entity;

import com.softrear.libraryapi.model.AuditModel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User extends AuditModel {

    @Id
    @SequenceGenerator(name = "VENDOR_SEQ", sequenceName = "VENDOR_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "VENDOR_SEQ")
    private Long id;
    private String name;

}
