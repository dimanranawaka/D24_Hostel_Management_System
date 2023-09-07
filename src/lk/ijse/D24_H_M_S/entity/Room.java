package lk.ijse.D24_H_M_S.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Room {
    @Id
    @Column(name = "room_type_id")
    private String rId;
    private String type;
    @Column(name = "key_money")
    private String keMoney;

    private int qty;

    @OneToMany(mappedBy = "room" , fetch = FetchType.EAGER)
    private List <Room> roomDetails = new ArrayList<>();

    public Room(String rId, String type, String keMoney, int qty) {
        this.rId = rId;
        this.type = type;
        this.keMoney = keMoney;
        this.qty = qty;
    }
}
