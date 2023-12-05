package bongoye.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

class expression {
    static enum sign {
        equal, lessThan, greaterThan, greaterThanOrEqual, lessThanOrEqual, notEqual, percent, multiply
    };

    sign s;
}

public class DatabaseManager {
    Statement statement;

    public DatabaseManager(String databaseName, String username, String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName, username, password);
            statement = conn.createStatement();
            System.out.print("Database Connected");
        } catch (SQLException e) {
            System.out.print("Database Not Connected : " + e);
        }
    }

    public void outputResult(ResultSet result) {
        int i = 1;
        try {
            while (result.next()) {
                result.getRow();
                System.out.println("Value " + result.getString(i));
                i++;
            }
        } catch (Exception e) {
        }
    }

    public void query(String myQuery) {
        try {
            System.out.print("Ok");
            outputResult(statement.executeQuery(myQuery));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insert(String tableName, String[] fieldNames, String[] fieldValues) {
        try {
            if (fieldNames.length != fieldValues.length)
                throw new SQLException("fieldsName.length should be equal to fieldsValues.length");

            String query = "INSERT INTO `" + tableName + "`";
            String fields = "", values = "";

            for (int i = 0; i < fieldNames.length; i++) {
                if (i == fieldNames.length - 1) {
                    fields = fields + "`" + fieldNames[i] + "`";
                    values = values + "'" + fieldValues[i] + "'";
                } else {
                    fields = fields + "`" + fieldNames[i] + "`,";
                    values = values + "'" + fieldValues[i] + "',";
                }
            }
            query = query + " (" + fields + ") " + "VALUES" + " (" + values + ") ";
            statement.executeUpdate(query);
            System.out.print("Inserted");
        } catch (SQLException e) {
            System.out.print("Not Inserted");
        }
    }

    public void insert(String tableName, String[] fieldNames, int[] fieldValues) {
        try {
            if (fieldNames.length != fieldValues.length)
                throw new SQLException("fieldsName.length should be equal to fieldsValues.length");

            String query = "INSERT INTO `" + tableName + "`";
            String fields = "", values = "";

            for (int i = 0; i < fieldNames.length; i++) {
                if (i == fieldNames.length - 1) {
                    fields = fields + "`" + fieldNames[i] + "`";
                    values = values + fieldValues[i];
                } else {
                    fields = fields + "`" + fieldNames[i] + "`,";
                    values = values + fieldValues[i] + ",";
                }
            }
            query = query + " (" + fields + ") " + "VALUES" + " (" + values + ") ";
            statement.executeUpdate(query);
            System.out.print("Inserted");
        } catch (SQLException e) {
            System.out.print("Not Inserted");
        }
    }

    public void select(String tableName) {
        try {
            String query = "select  emails from `" + tableName + "`";
            outputResult(statement.executeQuery(query));
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void select(String tableName,String[] fieldToSelect) {
        try {
            String query = "select ";// * from `" + tableName + "` where " + field + "'" + fieldValue;
            String from = "";

            for (int i = 0; i < fieldToSelect.length; i++) {
                if (i == fieldToSelect.length - 1) {
                    from = from + fieldToSelect[i];
                } else {
                    from = from + fieldToSelect[i] + ",";
                }
            }
            query = query + from + " `" + tableName + "`";

            outputResult(statement.executeQuery(query));

        } catch (SQLException e) {
        }
    }

    public void select( String tableName,String[] fieldToSelect, String whereField, expression.sign e, String fieldValue) {
        try {
            String query = "select ";// * from `" + tableName + "` where " + field + "'" + fieldValue;
            String from = "";

            for (int i = 0; i < fieldToSelect.length; i++) {
                if (i == fieldToSelect.length - 1) {
                    from = from + fieldToSelect[i];
                } else {
                    from = from + fieldToSelect[i] + ",";
                }
            }
            query = query + from + "`" + tableName + "` where " + whereField + " " + signToString(e) + " " + fieldValue;

            outputResult(statement.executeQuery(query));
        } catch (SQLException ex) {
            System.out.println("Problem To Show Data");
        }
    }

    String signToString(expression.sign e) {
        return (e == expression.sign.notEqual) ? "!="
                : (e == expression.sign.equal) ? "="
                        : (e == expression.sign.greaterThan) ? ">"
                                : (e == expression.sign.greaterThanOrEqual) ? ">="
                                        : (e == expression.sign.lessThan) ? "<"
                                                : (e == expression.sign.lessThanOrEqual) ? "<="
                                                        : (e == expression.sign.percent) ? "%"
                                                                : (e == expression.sign.multiply) ? "*" : "=";
    }

    public void update() {
        /*try {
            //update
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
    }

    public void delete() {
       /*  try {
            //delete
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
    }
}