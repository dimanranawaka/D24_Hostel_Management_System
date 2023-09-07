package lk.ijse.D24_H_M_S.bo;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null ) ? boFactory = new BOFactory() : boFactory;
    }

}
