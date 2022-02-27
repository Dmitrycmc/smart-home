package io.github.dmitrycmc.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "device")
    private Set<ScenarioDevice> scenarioDeviceSet;

    @ManyToMany(mappedBy = "devices")
    private Set<Role> roles;

    @OneToMany(mappedBy = "device",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Picture> pictures;
}
