package com.elina.railwayApp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TicketDTO implements Serializable {
    Long schedule;

    String userFirstName;

    String userLastName;

    String userLogin;

    String userBirthDay;

    String userSex;

    SeatDTO seatDTO;
}
