package org.example.dao;
import org.example.connection.ConnectionFactory;
import org.example.model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The AbstractDAO class is an abstract base class for DAOs (Data Access Objects) that provide common functionality
 * for working with database tables.
 *
 * @param <T> The type of the entity to be managed by the DAO.
 */
public abstract class AbstractDAO <T>{
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * Constructs an AbstractDAO object.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    /**
     * Creates the SELECT query string for retrieving records by a specific field.
     *
     * @param field The field to filter the records.
     * @return The SELECT query string.
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Retrieves a record by its ID.
     *
     * @param id The ID of the record to retrieve.
     * @return The entity representing the record, or null if not found.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * Creates a list of entity objects based on the ResultSet.
     *
     * @param resultSet The ResultSet containing the retrieved records.
     * @return A list of entities representing the records.
     */

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;}
        try {while (resultSet.next()) {
            ctor.setAccessible(true);
            T instance = (T)ctor.newInstance();
            for (Field field : type.getDeclaredFields()) {
                String fieldName = field.getName();
                Object value = resultSet.getObject(fieldName);
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                Method method = propertyDescriptor.getWriteMethod();
                method.invoke(instance, value);
            }list.add(instance);
        }
        } catch (InstantiationException e) {e.printStackTrace();
        } catch (IllegalAccessException e) {e.printStackTrace();
        } catch (SecurityException e) {e.printStackTrace();
        } catch (IllegalArgumentException e) {e.printStackTrace();
        } catch (InvocationTargetException e) {e.printStackTrace();
        } catch (SQLException e) {e.printStackTrace();
        } catch (IntrospectionException e) {e.printStackTrace();
        }
        return list;
    }
    /**
     * Inserts a new record into the table.
     *
     * @param t The entity representing the record to be inserted.
     * @throws IllegalAccessException If access to a field is denied.
     */

    public void insert(T t) throws IllegalAccessException {
        String tableName=t.getClass().getSimpleName();
        StringBuilder query=new StringBuilder();
        query.append("insert into ").append(tableName).append(" (");
        Field[] fields =t.getClass().getDeclaredFields();
        int i=0;
        for(Field field:fields){
            field.setAccessible(true);
            query.append(field.getName());
            if(i<fields.length-1)
                query.append(",");
            i++;
        }
        query.append(")").append(" values (");
        for (int j=0;j<fields.length;j++){
            fields[j].setAccessible(true);
            Object val=fields[j].get(t);
            query.append("'").append(val).append("'");
            if(j<fields.length-1)
                query.append(",");
        }
        query.append(")");
        System.out.println(query.toString());
        try{Connection connection= ConnectionFactory.getConnection();
            PreparedStatement statement=null;
            statement=connection.prepareStatement(query.toString());
            statement.executeUpdate(query.toString());
        }catch (Exception e){e.printStackTrace();}
    }

    /**
     * Updates an existing record in the table.
     *
     * @param t The entity representing the record to be updated.
     * @throws IllegalAccessException If access to a field is denied.
     */
    public void update(T t) throws IllegalAccessException {
        String tableName=t.getClass().getSimpleName();
        StringBuilder query=new StringBuilder();
        query.append("update ").append(tableName).append(" set ");
        Field[] fields =t.getClass().getDeclaredFields();
        int i=0;
        for(Field field:fields){
            field.setAccessible(true);
            query.append(field.getName()).append(" ='");
            Object value=fields[i].get(t);
            query.append(value).append("'");
            if(i<fields.length-1)
                query.append(",");
            i++;
        }
        query.append(" where id=");
        Object value=fields[0].get(t);
        query.append(value).append(";");

        try{Connection connection= ConnectionFactory.getConnection();
            PreparedStatement statement=null;
            statement=connection.prepareStatement(query.toString());
            System.out.println(query);
            statement.executeUpdate();
        }catch (Exception e){e.printStackTrace();}

    }
    /**
     * Deletes a record from the table.
     *
     * @param t The entity representing the record to be deleted.
     * @throws IllegalAccessException If access to a field is denied.
     */
    public void delete(T t) throws IllegalAccessException {
        String tableName=t.getClass().getSimpleName();
        StringBuilder query=new StringBuilder();
        query.append("delete from ").append(tableName).append(" where id= ?");
        Field[] fields =t.getClass().getDeclaredFields();
        fields[0].setAccessible(true);
        Object val=fields[0].get(t);

        try{Connection connection= ConnectionFactory.getConnection();
            PreparedStatement statement=null;
            statement=connection.prepareStatement(query.toString());
            statement.setObject(1,val);
            statement.executeUpdate();
        }catch (Exception e){e.printStackTrace();}
    }

    public String[] getHeader(Object object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String[] header = new String[fields.length];
        int index = 0;
        for (Field field : fields) {
            header[index] = field.getName();
            index++;
        }
        return header;
    }

    public Object[] getRowData(Object object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Object[] rowData = new Object[fields.length];
        int index = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                rowData[index] = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            index++;
        }
        return rowData;
    }


}