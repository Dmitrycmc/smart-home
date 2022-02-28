package io.github.dmitrycmc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@ToString
@Table(name = "scenario_device")
public class ScenarioDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @ManyToOne
    @JoinColumn(name = "scenario_id")
    @Getter
    @Setter
    private Scenario scenario;

    @ManyToOne
    @JoinColumn(name = "device_id")
    @Getter
    @Setter
    private Device device;

    @Column(nullable = false)
    @Getter
    @Setter
    private float time;

    @Column(nullable = false)
    @Getter
    @Setter
    private boolean active;
}
