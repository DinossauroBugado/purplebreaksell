package me.platinumdev.breaksell.model.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

    private Connection connection;
    private JavaPlugin plugin;

    private boolean debug;
    private boolean useMySQL;
    private String host;
    private String user;
    private String password;
    private String database;
    private int port;

    public DatabaseUtils(String host, int port, String user, String password, String database, boolean enable, boolean debug, JavaPlugin pl){
        this.useMySQL = enable;
        this.host = host;
        this.port = port;
        this.password = password;
        this.user = user;
        this.database = database;
        this.plugin = pl;
        this.debug = debug;
        if(debug)
            plugin.getLogger().info("Iniciando conexao a database.");
        loadDatabase();
    }

    public void openConnectionMySQL(){
        try {
            if ((connection != null) && (!connection.isClosed()))
                return;
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", user, password);
            if(debug)
                plugin.getLogger().info("O plugin se conectou ao MySQL.");
        }catch (Exception e){
            plugin.getLogger().severe("Ocorreu um erro ao se conectar ao MySQL, mudando armazenamento para SQLite.");
            openConnectionSQLite();
        }
    }

    public void openConnectionSQLite(){
        try{
            File file = new File(plugin.getDataFolder(), "storage.db");
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+ file);
            if(debug)
                plugin.getLogger().info("O plugin se conectou ao SQLite.");
        } catch (Exception e){
            plugin.getLogger().severe("Ocorreu um erro ao se conectar ao SQLite, desativando plugin.");
            Bukkit.getPluginManager().disablePlugin(plugin);
            e.printStackTrace();
        }
    }

    public void loadDatabase(){
        if(useMySQL){
            openConnectionMySQL();
        }else{
            openConnectionSQLite();
        }
    }

    public void createTable(String table, String query){
        try {
            if((!connection.isClosed()) && connection != null){
                Statement stm = connection.createStatement();
                stm.execute("CREATE TABLE IF NOT EXISTS " + table + " (" + query + ")");
                if(debug)
                    plugin.getLogger().info("Foi criada/carregada a tabela "+table+" na database");
            }
        } catch (SQLException e) {
            plugin.getLogger().severe("Ocorreu um erro ao criar a tabela `"+table+"` na database");
            e.printStackTrace();
        }
    }

    public boolean isConnect(){
        try {
            return ((!connection.isClosed()) && connection != null);
        } catch (SQLException e) {
            return false;
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public boolean isMysql(){
        return useMySQL;
    }

    public boolean isDebug(){
        return debug;
    }

    public void closeConnection(){
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}