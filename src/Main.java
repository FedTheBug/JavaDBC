import Helper.DBHandler;

import java.sql.*;

public class Main {
    static private  DBHandler dbHandler;
    static private Connection connection;
    static private  PreparedStatement preparedStatement;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        dbHandler = new DBHandler();
        connection = dbHandler.getDbConnection();
        writeToDB();
        readFromDB();
        updateDB("New", "user", "newuser", 24, 2);
        deleteDB(2);
    }
    public static void writeToDB() throws SQLException {
        String insert = "INSERT INTO users(firstname, lastname, username, age)"
                +"VALUES(?,?,?,?)";

        preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1,"Fed");
        preparedStatement.setString(2,"TheBug");
        preparedStatement.setString(3,"FedTheBug");
        preparedStatement.setInt(4,25);
        preparedStatement.executeUpdate();

        preparedStatement.setString(1,"Topu");
        preparedStatement.setString(2,"ThePirate");
        preparedStatement.setString(3,"TopuThePirate");
        preparedStatement.setInt(4,18);
        preparedStatement.executeUpdate();
    }
    public static void readFromDB() throws SQLException{
        String query = "SELECT * FROM users";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            System.out.println(" First Name:"+resultSet.getString("firstname")+
                    " Last Name"+resultSet.getString("lastname")+
                    " User Name:"+resultSet.getString("username")+
                    " Age"+resultSet.getString("age"));
        }
    }
    public static void updateDB(String firstname, String lastname, String username, int age, int id) throws SQLException {
        String query = "UPDATE users SET firstname = ?, lastname = ?, username = ?, age = ?" + " WHERE userid = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, firstname);
        preparedStatement.setString(2, lastname);
        preparedStatement.setString(3, username);
        preparedStatement.setInt(4, age);
        preparedStatement.setInt(5, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public static void deleteDB(int id) throws SQLException {
        String query = "DELETE FROM users  WHERE userid = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

}
