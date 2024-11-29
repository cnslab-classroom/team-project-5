package com.springboot.harubi.Domain.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Saying {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saying_id;

    @Column(nullable = false)
    private String saying_text;

    @Column
    private String saying_speaker;
}
