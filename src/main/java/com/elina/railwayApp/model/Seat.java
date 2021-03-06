package com.elina.railwayApp.model;

import com.elina.railwayApp.configuration.common.Tables;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = Tables.SEAT_TABLE)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "carriage")
    private int carriage;

    @Column(name = "seat")
    private int seat;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;
}
