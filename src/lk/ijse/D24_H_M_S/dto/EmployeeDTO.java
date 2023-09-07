package lk.ijse.D24_H_M_S.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {
    String eID;
    String name;
    String address;
    String contact;
    String role;
}
