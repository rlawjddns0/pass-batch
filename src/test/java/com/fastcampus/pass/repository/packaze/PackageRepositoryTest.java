package com.fastcampus.pass.repository.packaze;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PackageRepositoryTest {

    @Autowired
    private PackageRepository packageRepository;


    @Test
    @Transactional
    public void test_save(){
        // Given
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("body challange pt");
        packageEntity.setPeriod(12);

        // When
        PackageEntity save = packageRepository.save(packageEntity);

        // Then
        Assertions.assertNotNull(packageRepository.findById(save.getId()));
    }

    @Test
    @Transactional
    public void test_findByCreatedAtAfter() {
        // Given
        LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(1);

        PackageEntity packageEntity0 = PackageEntity.builder()
                .packageName("For Student Three Month")
                .period(90)
                .build();
        packageRepository.save(packageEntity0);

        PackageEntity packageEntity1 = PackageEntity.builder()
                .packageName("For Strudent Six Month")
                .period(180)
                .build();
        packageRepository.save(packageEntity1);

        // When
        List<PackageEntity> pakazeEntyties = packageRepository.findByCreatedAtAfter(localDateTime, PageRequest.of(0, 1, Sort.by("id").descending()));

        // Then
        assertEquals(1, pakazeEntyties.size());
        assertEquals(packageEntity1.getId(), pakazeEntyties.get(0).getId());


    }

    @Test
    public void test_updateCountAndPeriod() {
        PackageEntity packageEntity = PackageEntity.builder()
                .packageName("new Name")
                .period(90)
                .build();
        packageRepository.save(packageEntity);

        //when
        int updatCount = packageRepository.updateCountAndPeriod(packageEntity.getId(), "update Name", 100);
        PackageEntity updatedPackageEntity = packageRepository.findById(packageEntity.getId()).get();

        //then
        assertEquals(1, updatCount);
        assertEquals("update Name", updatedPackageEntity.getPackageName());
    }

    @Test
    public void test_delete() {
        // Given
        PackageEntity packageEntity = PackageEntity.builder()
                .packageName("new package")
                .period(12)
                .build();
        PackageEntity save = packageRepository.save(packageEntity);

        // When
        packageRepository.deleteById(save.getId());

        // Then
        assertFalse(packageRepository.existsById(save.getId()));
    }

}