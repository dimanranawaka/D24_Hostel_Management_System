package lk.ijse.D24_H_M_S.dao;

import lk.ijse.D24_H_M_S.dao.custom.impl.LoginDAOImpl;
import lk.ijse.D24_H_M_S.dao.custom.impl.RoomDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum Types{
        ROOM,RESERVATION,STUDENT,EMPLOYEE,USER,LOGIN,FORGET,UNPAID

    }
    public SuperDAO getDAO(Types types){
        switch (types){
            case LOGIN:
                return new LoginDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            default:
                return null;
        }
    }

}
