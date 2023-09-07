package lk.ijse.D24_H_M_S.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomDTO {
    // For Student
    private String sId;
    private String name;
    private String address;
    private String contactNo;
    private LocalDate dob;
    private String gender;

    // For reservation

    private String resId;
    private LocalDate date;
    private String status;

    // For Room

    private String rId;
}
