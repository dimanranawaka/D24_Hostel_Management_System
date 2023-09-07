package lk.ijse.D24_H_M_S.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
    @Id
    private String resId;
    private LocalDate date;
    private String status;
    private Student student;
    private Room room;

}
