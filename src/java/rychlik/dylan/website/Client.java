package rychlik.dylan.website;

import java.sql.ResultSet;

//extends of abtract enity class
public class Client implements Enity {
    //Instance variables
    private final String TABLE_NAME = "Clients";

    @Override
    public void add(String[] values) {
        Database.add(
            TABLE_NAME,
            new String[]{"ClientID", "Clientname"},
            values
        );
    }
    
    //Update method that uses the resquest.getparamters string as the emthod paramters. 
    @Override
    public void update(String[] values) {
        Database.update(
            TABLE_NAME,
            new String[]{"Clientname", "ClientID"},
            values
        );
    }

    @Override
    public ResultSet select() {
        return Database.selectAll(TABLE_NAME);
    }

    @Override
    public ResultSet selectWhere(String id) {
        return Database.selectWhere(TABLE_NAME, new String[]{"ClientID"}, new String[]{id});
    }
}
