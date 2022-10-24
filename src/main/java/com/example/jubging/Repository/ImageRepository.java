package com.example.jubging.Repository;

import com.example.jubging.Model.ImageInfo;
import com.example.jubging.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageInfo, Long> {
    Optional<ImageInfo> findByUser(User user);
}
