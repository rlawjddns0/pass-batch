package com.fastcampus.pass.repository.packaze;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PackageRepository extends JpaRepository<PackageEntity, Integer> {
    List<PackageEntity> findByCreatedAtAfter(LocalDateTime localDateTime, Pageable pageable);


    @Transactional
    @Modifying
    @Query(value = "UPDATE PackageEntity p " +
            "SET p.packageName = :updateName,"
            +"p.period = :newPeriod " +
            "where p.id = :id"

    )
    int updateCountAndPeriod(@Param("id") Integer id,
                             @Param("updateName") String updateName,
                             @Param("newPeriod") Integer newPeriod);
}
