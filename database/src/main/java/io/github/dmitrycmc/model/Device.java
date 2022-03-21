package io.github.dmitrycmc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@ToString
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private Float x, y;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String name;

    @Column(nullable = false)
    @Getter
    @Setter
    private boolean active;

    @OneToMany(mappedBy = "device")
    @Getter
    @Setter
    private List<ScenarioDevice> scenarioDeviceSet;

    @ManyToMany(mappedBy = "devices")
    @Getter
    @Setter
    private List<Role> roles;

    @Getter
    @Setter
    @OneToMany(mappedBy = "device",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Picture> pictures = new LinkedList<>();
}
