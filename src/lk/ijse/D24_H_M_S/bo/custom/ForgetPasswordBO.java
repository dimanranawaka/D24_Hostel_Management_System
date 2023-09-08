package lk.ijse.D24_H_M_S.bo.custom;

import lk.ijse.D24_H_M_S.bo.SuperBO;

public interface ForgetPasswordBO extends SuperBO {

    boolean forgetPassword(String id, String name, String password);

}
