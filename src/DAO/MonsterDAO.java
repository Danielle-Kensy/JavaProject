package DAO;

import DTO.MonsterDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MonsterDAO extends CharacterDAO{
    Connection conn;
    PreparedStatement pstm;

    public void createMonster(MonsterDTO objMonster) {
        String sql = "insert into monster(magic_points, id_character) values(?,?)";

        conn = new ConnectionDAO().connectDB();

        try {

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, objMonster.getMagicPoints());

            pstm.setInt(2, objMonster.getIdCharacter());

            pstm.execute();

            JOptionPane.showMessageDialog(null, "your monster has summoned🧟");

            //fecha conexão
            pstm.close();

        } catch (Exception err) {
            //joptionpane para abrir uma caixa de diálogo
            JOptionPane.showMessageDialog(null, "MonsterDAO Create " + err );
        }
    }

    public ResultSet readMonster() {

        String sql = "select name_character as monster_name, hit_points, magic_points from character_rpg as C " +
                "inner join monster as M on M.id_character=C.id_character;";
        conn = new ConnectionDAO().connectDB();
        try {

            pstm = conn.prepareStatement(sql);

            return pstm.executeQuery();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "MonsterDAO Read " + err );
        }
        return null;
    }

    public void deleteMonster(int id) {
        String sql = "delete from monster where id_monster = ?";
        conn = new ConnectionDAO().connectDB();
        try {

            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Your monster is deed 💀");
            conn.close();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "MonsterDAO Delete " + err );
        }
    }

    public ResultSet searchMonster(String name) {
        String sql = "select id_monster, name_character, magic_points from monster as M " +
                "inner join character_rpg as C on C.id_character = M.id_character " +
                "where C.name_character = ?";
        conn = new ConnectionDAO().connectDB();
        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, name);
            return pstm.executeQuery();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "MonsterDAO Search " + err );
        }
        return null;
    }
}
