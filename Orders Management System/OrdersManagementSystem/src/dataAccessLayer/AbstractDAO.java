package dataAccessLayer;

import connection.ConnectionFactory;

import java.lang.reflect.Field;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * A method that create a query.
     *
     * @return a string representing the query for selecting all from a table
     */
    public String createSelectAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * A method that creates a table.
     *
     * @param objects - represent the objects that we are going to insert into the table
     * @return - the table that we want to create
     */
    public JTable createTable(ArrayList<?> objects) {
        Object o = objects.get(0);
        Object[] column = new Object[o.getClass().getDeclaredFields().length];
        int i = 0;
        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                column[i] = field.getName();
                i++;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        for (Object obj : objects) {
            Object[] row = new Object[o.getClass().getDeclaredFields().length];
            int j = 0;
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    row[j] = field.get(obj);
                    j++;
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(row);
        }
        JTable table = new JTable(model);
        return table;
    }

    /**
     * A method that inserts an object into a table.
     *
     * @param object - the object that we are going to insert into the table
     */
    public void insert(Object object) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(type.getSimpleName()).append("(");
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            sb.append(field.getName()).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(") VALUES (");
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value;
                value = field.get(object);
                if (field.getType().getSimpleName().equals("String")) {
                    sb.append("'").append(value).append("'").append(", ");
                } else {
                    sb.append(value).append(", ");
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(")");
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sb.toString());
            statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        }
    }

    /**
     * A method that deletes an object from the table.
     *
     * @param object - the object that we are going to delete from the table
     */
    public void delete(Object object) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ").append(type.getSimpleName()).append(" WHERE ");
        Field[] fields = object.getClass().getDeclaredFields();
        fields[0].setAccessible(true);
        sb.append(fields[0].getName()).append(" = ");
        try {
            Object value = fields[0].get(object);
            sb.append(value);
        } catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sb.toString());
            statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        }
    }

    /**
     * A method that updates an object in a table.
     *
     * @param object - the object that we are going to update in a table
     */
    public void update(Object object) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(object.getClass().getSimpleName()).append(" SET ");
        for (Field fields : object.getClass().getDeclaredFields()) {
            fields.setAccessible(true);
            sb.append(fields.getName()).append(" = ");
            try {
                Object value = fields.get(object);
                if (fields.getType().getSimpleName().equals("String")) {
                    sb.append("'").append(value).append("'").append(", ");
                } else {
                    sb.append(value).append(", ");
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(" WHERE ");
        Field[] field = object.getClass().getDeclaredFields();
        field[0].setAccessible(true);
        sb.append(field[0].getName()).append(" = ");
        try {
            Object sValue = field[0].get(object);
            sb.append(sValue);
        } catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sb.toString());
            statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        }
    }
}
