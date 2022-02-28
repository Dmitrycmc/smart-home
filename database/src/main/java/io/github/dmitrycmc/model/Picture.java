package io.github.dmitrycmc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(length = 1024, nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "content_type", nullable = false)
    @Getter
    @Setter
    private String contentType;

    @Column(name = "storage_file_name", length = 256, nullable = false, unique = true)
    @Getter
    @Setter
    private String storageFileName;

    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Device device;

    public Picture() {
    }

    public Picture(Long id, String name, String contentType, String storageFileName, Device device) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.storageFileName = storageFileName;
        this.device = device;
    }
}
