package lk.ijse.D24_H_M_S.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentTDM {
    private String sId;
    private String name;
    private String address;
    private String contactNo;
    private LocalDate date;
    private String gender;
}
