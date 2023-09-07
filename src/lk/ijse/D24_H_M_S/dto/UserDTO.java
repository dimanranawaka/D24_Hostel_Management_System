package lk.ijse.D24_H_M_S.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private String uId;
    private String name;
    private String email;
    private String password;
    private String role;
}
