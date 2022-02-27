package io.github.dmitrycmc.model;

import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Column(nullable = false, unique = true)
    @Getter
    private String name;

    @Column(nullable = false)
    @Getter
    private boolean active;

    @OneToMany(mappedBy = "device")
    @Getter
    private Set<ScenarioDevice> scenarioDeviceSet;

    @ManyToMany(mappedBy = "devices")
    @Getter
    private Set<Role> roles;

    @Getter
    @OneToMany(mappedBy = "device",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Picture> pictures;
}
