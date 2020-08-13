package rychlik.dylan.website;

import java.sql.*;

//Makes enity abstract 
public interface Enity {
    public void add(String[] values);
    public void update(String[] values); 

    //abstract methods with a resultset return type. 
    public ResultSet select();
    public ResultSet selectWhere(String ID);
}
