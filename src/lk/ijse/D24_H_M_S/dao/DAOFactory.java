package lk.ijse.D24_H_M_S.dao;

import lk.ijse.D24_H_M_S.dao.custom.impl.*;

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
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case USER:
                return new UserDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            case FORGET:
                return new ForgetPasswordDAOImpl();
            case UNPAID:
                return new QueryDAOImpl();
            default:
                return null;
        }

    }

}
