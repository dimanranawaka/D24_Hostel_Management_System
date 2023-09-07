package lk.ijse.D24_H_M_S.dto;

import lk.ijse.D24_H_M_S.entity.Room;
import lk.ijse.D24_H_M_S.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDTO {
    private String resId;
    private LocalDate date;
    private String status;
    private Student student;
    private Room room;
}
