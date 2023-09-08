package lk.ijse.D24_H_M_S.bo.custom;

import lk.ijse.D24_H_M_S.bo.SuperBO;

public interface DashBoardBO extends SuperBO {
    int getRoomCount1();
    int getRoomCount2();
    int getRoomCount3();
    int getRoomCount4();

    int getStudentCount();

    int getEmployeeCount();
}
