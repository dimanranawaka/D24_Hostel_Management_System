package lk.ijse.D24_H_M_S.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    @Id
    @Column(name = "employee_Id")
    String eId;
    String name;
    String address;
    String contact;
    String role;

}
