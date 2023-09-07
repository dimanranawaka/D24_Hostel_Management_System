package lk.ijse.D24_H_M_S.dto;

import lk.ijse.D24_H_M_S.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDTO {
    private String rId;
    private String type;
    private String keyMoney;
    private int qty;
    private List<Reservation> roomDetails = new ArrayList<>();

    public RoomDTO(String rId, String type, String keyMoney, int qty) {
        this.rId = rId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }
}
