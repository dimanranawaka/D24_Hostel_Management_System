package lk.ijse.D24_H_M_S.bo;

import lk.ijse.D24_H_M_S.bo.custom.impl.LoginBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null ) ? boFactory = new BOFactory() : boFactory;
    }
    public enum Types{
        STUDENT,ROOM,RESERVATION,EMPLOYEE,USER,LOGIN,FORGET,UNPAID,DASHBOARD
    }
    public SuperBO getBO(Types types){
        switch (types){
            case LOGIN:
                return new LoginBOImpl();
            default:
                return null;
        }
    }
}