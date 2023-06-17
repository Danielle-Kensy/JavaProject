package DAO;

import DTO.CharacterDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CharacterDAO {
    //declarando variáveis comuns á todos os métodos
    Connection conn;
    PreparedStatement pstm;

    public void createCharacter(CharacterDTO objCharacter) {
        //váriavel sql
        //values com interrogações pq ainda não sabemos seus valores
        String sql = "insert into character_rpg(name_character, hit_points) values(?,?)";

        //seta nossa váriavel de conexão executando o método.
        //todos os métodos de banco precisam se conectar ao bd
        conn = new ConnectionDAO().connectDB();

        try {

            //prepara a conexão passando o sql necessário
            pstm = conn.prepareStatement(sql);
            //passa o primeiro parametro o nome
            pstm.setString(1, objCharacter.getName());
            //passa o segundo parametro o força
            pstm.setInt(2, objCharacter.getHitPoints());

            //executa as ações
            pstm.execute();

            JOptionPane.showMessageDialog(null, "your character has born❤️");

            //fecha conexão
            pstm.close();

        } catch (Exception err) {
            //joptionpane para abrir uma caixa de diálogo
            JOptionPane.showMessageDialog(null, "CharacterDAO Create " + err );
        }
    }

    //retorno da função é um conjunto de resultados (ResultSet)
    public ResultSet readCharacters() {
        //declarando a query
        String sql = "select * from character_rpg";
        conn = new ConnectionDAO().connectDB();
        try {

            pstm = conn.prepareStatement(sql);

            //executa a query
            return pstm.executeQuery();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "CharacterDAO Read " + err );
        }
        return null;
    }

    public void updateCharacter(CharacterDTO objCharacter, int id) {
        String sql = "update character_rpg set name_character = ?, hit_points = ? where id_character = ?";
        conn = new ConnectionDAO().connectDB();
        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objCharacter.getName());
            pstm.setInt(2, objCharacter.getHitPoints());
            pstm.setInt(3, id);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Your character has changed ✨");
            conn.close();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "CharacterDAO Search " + err );
        }
    }

    public void deleteCharacter(int id) {
        String sql = "delete from character_rpg where id_character = ?";
        conn = new ConnectionDAO().connectDB();
        try {

            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Your character is deed 💀");
            conn.close();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "CharacterDAO Search " + err );
        }
    }

    public ResultSet searchCharacter(String name) {
        String sql = "select * from character_rpg where name_character = ?";
        conn = new ConnectionDAO().connectDB();
        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, name);
            return pstm.executeQuery();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "CharacterDAO Search " + err );
        }
        return null;
    }

    public ResultSet getLastCharacter() {

        String sql = "select * from character_rpg ORDER BY id_character DESC LIMIT 1;";
        conn = new ConnectionDAO().connectDB();
        try {

            pstm = conn.prepareStatement(sql);

            return pstm.executeQuery();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "CharacterDAO Search " + err );
        }
        return null;
    }
}
