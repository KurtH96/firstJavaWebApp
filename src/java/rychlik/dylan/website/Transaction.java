package rychlik.dylan.website;

import java.sql.ResultSet;

//extends the abtract enity class
public class Transaction implements Enity {
    private final String TABLE_NAME = "Transactions";
    
    //Update method that uses the resquest.getparamters string as the method paramters. 
    @Override
    public void update(String[] values) {
        Database.update(
            TABLE_NAME,
            new String[]{"Amount", "Description", "ClientID", "Date", "TransactionID"},
            values
        );
    }

    @Override
    public void add(String[] values) {
        Database.add(
            TABLE_NAME,
            new String[]{"TransactionID", "Amount", "Description", "ClientID", "Date"},
            values
        );  
    }

    @Override
    public ResultSet selectWhere(String id) {
        return Database.selectWhere(TABLE_NAME, new String[]{"TransactionID"}, new String[]{id});
    }

    @Override
    public ResultSet select() {
        return Database.selectAll(TABLE_NAME);
    }

}
