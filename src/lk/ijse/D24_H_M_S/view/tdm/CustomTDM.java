package lk.ijse.D24_H_M_S.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomTDM {
    private String sId;
    private String name;
    private String resId;
    private String status;
    private String rId;
    private String type;
    private LocalDate date;

    // For Validations and Custom Logic


    public CustomTDM(String sId, String name, String resId, String status, String rId, String type) {
        this.sId = sId;
        this.name = name;
        this.resId = resId;
        this.status = status;
        this.rId = rId;
        this.type = type;
    }
}
