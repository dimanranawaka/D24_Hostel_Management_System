package lk.ijse.D24_H_M_S.dao.custom.impl;

import lk.ijse.D24_H_M_S.dao.custom.RoomDAO;
import lk.ijse.D24_H_M_S.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    private Session session; // Declare a private instance variable for Hibernate Session

    @Override
    public void setSession(Session session){

        this.session = session; // Set the Hibernate Session when called

    }

    @Override
    public boolean save(Room room){

        if (room != null){ // Check if the provided room object is not null

            session.save(room); // Save the room object using the Hibernate session
            return true; // Return true to indicate successful save

        } else {

            return false; // Return false if the room object is null

        }
    }

    @Override
    public boolean update(Room room){

        if (room != null){ // Check if the provided room object is not null

            session.update(room); // Update the room object using the Hibernate session
            return true; // Return true to indicate successful update

        } else {

            return false; // Return false if the room object is null

        }
    }

    @Override
    public Room get(String id){

        return session.get(Room.class, id); // Retrieve a Room entity by its ID using Hibernate session

    }

    @Override
    public boolean delete(Room room){

        if (room != null){ // Check if the provided room object is not null

            session.delete(room); // Delete the room object using the Hibernate session
            return true; // Return true to indicate successful deletion

        } else {
            return false; // Return false if the room object is null
        }
    }

    @Override
    public List<Room> getAll(){

        String hql = "FROM Room"; // Define an HQL (Hibernate Query Language) query to retrieve all rooms

        Query query = session.createQuery(hql); // Create a Hibernate query object

        List list = query.list(); // Execute the query and store the result in a list

        session.close(); // Close the Hibernate session

        return list; // Return the list of rooms

    }

    @Override
    public List<String> getRoomId(){

        String hql = "SELECT rId FROM Room"; // Define an HQL query to retrieve room IDs

        Query query = session.createQuery(hql); // Create a Hibernate query object

        List<String> list = query.list(); // Execute the query and store the result in a list of strings

        session.close(); // Close the Hibernate session

        return list; // Return the list of room IDs
    }

    @Override
    public List<Room> getRoom1(){

        // Define an HQL query to retrieve a specific room (RM-1324)
        List<Room> list = session.createQuery("FROM Room WHERE rId = 'RM-1324'").list();

        session.close(); // Close the Hibernate session

        return list; // Return the list of rooms
    }

    @Override
    public List<Room> getRoom2(){

        // Define an HQL query to retrieve a specific room (RM-5467)
        List<Room> list = session.createQuery("FROM Room WHERE rId = 'RM-5467'").list();

        session.close(); // Close the Hibernate session

        return list; // Return the list of rooms
    }

    @Override
    public List<Room> getRoom3(){
        // Define an HQL query to retrieve a specific room (RM-7896)

        List<Room> list = session.createQuery("FROM Room WHERE rId = 'RM-7896'").list();

        session.close(); // Close the Hibernate session

        return list; // Return the list of rooms
    }

    @Override
    public List<Room> getRoom4(){

        // Define an HQL query to retrieve a specific room (RM-0093)

        List<Room> list = session.createQuery("FROM Room WHERE rId = 'RM-0093'").list();

        session.close(); // Close the Hibernate session

        return list; // Return the list of rooms
    }
}
