package ua.com.anton.dao;

import org.springframework.stereotype.Component;
import ua.com.anton.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private final static String URL="jdbc:postgresql://localhost:5432/postgres";
    private final static String USERNAME="postgres";
    private final static String PASSWORD="qwerty";
    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e){e.printStackTrace();}

    }
    public List<Person> index() throws SQLException {
        List<Person> people = new ArrayList<>();
        Statement statement = connection.createStatement();
        String SQL = "SELECT * from Person";
        ResultSet resultSet = statement.executeQuery(SQL);
        while(resultSet.next()){
            Person person= new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));
            people.add(person);

        }
        return people;
    }

    public Person show(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from person WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));
        return person;
    }
    public void save(Person person) throws SQLException {
        //        people.add(person);

        PreparedStatement preparedStatement = connection.prepareStatement("insert into person values(?, ?,?,?)");
        preparedStatement.setInt(1, ++PEOPLE_COUNT);
        preparedStatement.setString(2, person.getName());
        preparedStatement.setInt(3, person.getAge());
        preparedStatement.setString(4, person.getEmail());

        preparedStatement.executeUpdate();
    }
    public void update(int id, Person updatedPerson) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("UPDATE PERSON SET name=?,age=?,email=?WHERE id=?;");
        preparedStatement.setString(1, updatedPerson.getName());
        preparedStatement.setInt(2, updatedPerson.getAge());
        preparedStatement.setString(3, updatedPerson.getEmail());
        preparedStatement.setInt(4,id);

        preparedStatement.executeUpdate();

//        Person personToBeUpdated = show(id);
//
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }
    public void delete(int id) throws SQLException {
        PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE from person WHERE id=?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
        --PEOPLE_COUNT;
//        people.removeIf(p -> p.getId() == id);
//        --PEOPLE_COUNT;
    }
}