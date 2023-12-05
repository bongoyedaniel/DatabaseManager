## DatabaseManager

This project :
- Auto `mysql` query generation .
- Starts and stops `xampp`'s processes.


 ## Depenedencies

 - mysql-connector-j-8.2.0.jar

 ## Usage

- Database part

``` java

package bongoye;

import bongoye.Database.DatabaseManager;

public class Main {
    static DatabaseManager mydb;

    public static void main(String[] args) throws Exception {
        String username = "...";
        String passString = "...";
        String[] databaseNames = { "myDatabase" };
        String[] myTables = { "table1" };
       
        mydb = new DatabaseManager(databaseNames[0], username, passString);
        mydb.query("SELECT * from " + myTables[0]);

        //insert
        String[] fieldNames = {"field1"};
        String[] fieldValues = {"value1"};
        mydb.insert(myTables[0],fieldNames,fieldValues);
        
        //select
        mydb.select(myTables[0]);
        mydb.select(myTables[0],fieldNames);

        //delete

        //
    }
}

```

- xampp

``` java
package bongoye;

import bongoye.xampp.xamppManager;
import bongoye.xampp.processes;

public class Main {
    static DatabaseManager mydb;
    static xamppManager xampmanager;

    public static void main(String[] args) throws Exception {
        xampmanager = new xamppManager();
        xampmanager.start(new processes.process[] { processes.process.mysql, processes.process.apache });
    }
}

```