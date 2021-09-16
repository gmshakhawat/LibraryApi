package com.softrear.libraryapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softrear.libraryapi.model.AuditModel;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class BookHistory extends AuditModel {

    @Id
    @SequenceGenerator(name = "VENDOR_SEQ", sequenceName = "VENDOR_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "VENDOR_SEQ")
    private Long id;
//    @Column(name = "book_id")
//    private Long bookId;

    private boolean isReturned;
    @Column(name = "copy_after")
    private Long copyAfter;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;


//    @Column(name = "returned_at")
//    private LocalDateTime returnedAt;
//    @Column(name = "borrowed_at")
//    @CreationTimestamp
//    private LocalDateTime borrowedAt;
}
