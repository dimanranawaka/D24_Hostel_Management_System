package lk.ijse.D24_H_M_S.dao;

public interface CrudDAO<T, ID> extends SuperDAO{

      /*Saves an object of type T to the data source.
      @param object The object to be saved.
      @return true if the object is successfully saved, false otherwise.*/

    boolean save(T object);

      /*Updates an existing object of type T in the data source.
      @param object The object to be updated.
      @return true if the object is successfully updated, false otherwise.*/


    boolean update(T object);

     /* Retrieves an object of type T from the data source based on its ID.
      @param id The ID of the object to be retrieved.
      @return The retrieved object, or null if not found.*/


    T get(ID  id);


      /*Deletes an object of type T from the data source.

      @param object The object to be deleted.
      @return true if the object is successfully deleted, false otherwise.*/


    boolean delete(T object);

}
