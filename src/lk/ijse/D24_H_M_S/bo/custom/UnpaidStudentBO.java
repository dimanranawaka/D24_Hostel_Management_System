package lk.ijse.D24_H_M_S.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.D24_H_M_S.bo.SuperBO;
import lk.ijse.D24_H_M_S.view.tdm.CustomTDM;

public interface UnpaidStudentBO extends SuperBO {
    ObservableList<CustomTDM> getUnpaidStudents();

    boolean updateStatus(String id , String status);
}
