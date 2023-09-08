package lk.ijse.D24_H_M_S.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.D24_H_M_S.view.tdm.CustomTDM;

public interface UnpaidStudentBO {
    ObservableList<CustomTDM> getUnpaidStudents();

    boolean updateStatus(String id ,String name, String status);
}
