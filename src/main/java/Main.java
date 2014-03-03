import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.generated.tables.Author;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) throws Exception {
        Connection conn = getConnection();

        DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
        Result<Record> result = create.select().from(Author.AUTHOR).fetch();

        for (Record r : result) {
            Integer id = r.getValue(Author.AUTHOR.ID);
            String name = r.getValue(Author.AUTHOR.NAME);

            System.out.println("ID: " + id + " first name: " + name);
        }
    }

    public static Connection getConnection() throws Exception {
        Connection conn = null;

        String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/mydb";

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        return DriverManager.getConnection(url, userName, password);
    }
}
