/**
 * Created by Daniel's Laptop on 5/25/2016.
 */
import java.sql.*;

public class Database
{
    private static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:h2:~/practica5", "user", "admin");
    }

    public static void createIfNotExists()
    {
        Connection con;
        try
        {
            con = getConnection();
            String statement = "CREATE TABLE IF NOT EXISTS estudiantes (\n" +
                    " matricula varchar(8) NOT NULL,\n" +
                    " nombre varchar(100) NOT NULL,\n" +
                    " apellidos varchar(100) NOT NULL,\n" +
                    " telefono varchar(50) NOT NULL,\n" +
                    " PRIMARY KEY (matricula)\n" +
                    ") ";
            con.createStatement().execute(statement);
        }
        catch (Exception e)
        {

        }
    }

    public static ResultSet executeQuery(String statement) throws SQLException
    {
        return getConnection().createStatement().executeQuery(statement);
    }

    public static boolean executeInsert(String... params)
    {
        try
        {
            String statement = "INSERT INTO ESTUDIANTES VALUES(";
            for (int i = 0; i < params.length; i++)
                statement += String.format("'%s'", params[i]) + ",";
            statement = (statement.substring(0, statement.lastIndexOf(",")) + ")");
            return getConnection().createStatement().execute(statement);
        }
        catch (SQLException e)
        {
            return false;
        }
    }

    public static boolean executeUpdate(String[] params, String value)
    {
        try {
            return getConnection().createStatement().execute(
                    String.format("UPDATE ESTUDIANTES SET MATRICULA = '%s', NOMBRE = '%s', APELLIDOS = '%s', TELEFONO = '%s'" +
                                    " WHERE MATRICULA = '%s'",
                            params[0], params[1], params[2], params[3], value
                    ));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean executeDelete(String value)
    {
        try
        {
            return getConnection().createStatement().execute(String.format("DELETE FROM ESTUDIANTES WHERE MATRICULA = '%s'", value));
        }
        catch (SQLException e)
        {
            return false;
        }
    }
}
