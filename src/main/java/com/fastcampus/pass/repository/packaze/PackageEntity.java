package com.fastcampus.pass.repository.packaze;

import com.fastcampus.pass.repository.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "package")
@AllArgsConstructor
public class PackageEntity extends BaseEntity {
    @Id
    @Column(name = "package_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String packageName;
    private Integer count;
    private Integer period;

    public PackageEntity() {

    }
}
