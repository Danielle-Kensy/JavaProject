package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDAO {

    //quando temos um método daquele tipo vc precisa retornar esse tipo.
    //tipo void, tipo string, tipo connetion etc...
    public Connection connectDB() {
        //criando váriavel de conexão
        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost:3306/db_rpg?user=root&password=2208";
            //armazenando dados na váriavel para depois retornar
            //passando todas as informações para criar conexão via url
            conn = DriverManager.getConnection(url);
            System.out.println("Conectado✅");

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

        //retornando a minha conexão do tipo connection
        return conn;
    }
}
