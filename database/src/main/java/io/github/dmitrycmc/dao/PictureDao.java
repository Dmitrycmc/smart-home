package io.github.dmitrycmc.dao;

import io.github.dmitrycmc.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureDao extends JpaRepository<Picture, Long> {
}
