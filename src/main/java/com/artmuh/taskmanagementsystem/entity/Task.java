package com.artmuh.taskmanagementsystem.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=1,max = 32,message = "title must be in the range from 1 to 32 characters")
    private String title;

    @NotBlank
    @Size(min=1,max = 128,message = "description must be in the range from 1 to 128 characters")
    private String description;

    @Enumerated(EnumType.STRING)
    private StatusTask status;

    @Enumerated(EnumType.STRING)
    private PriorityTask priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User authorId;

    @Column(name = "executor_username")
    private String executorName;

    @Builder.Default
    @OneToMany(mappedBy = "task")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Comment>comments=new ArrayList<>();

}
