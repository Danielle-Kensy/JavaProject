package DAO;

import DTO.PlayerDTO;
import DTO.WeaponDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerDAO extends CharacterDAO{
    Connection conn;
    PreparedStatement pstm;

    public void createPlayer(PlayerDTO objPlayer) {

        String sql1 = "insert into player(life_points, id_character, id_weapon) values(?, ?, ?) ";
        String sql2 ="insert into weapon(name_weapon, damage) values(?,?)";
        conn = new ConnectionDAO().connectDB();

        try {

            pstm = conn.prepareStatement(sql2);
            pstm.setString(1, objPlayer.getWeapon().getName());
            pstm.setInt(2, objPlayer.getWeapon().getDamage());
            pstm.execute();

            pstm = conn.prepareStatement(sql1);
            pstm.setInt(1, objPlayer.getLifePoints());
            pstm.setInt(2, objPlayer.getIdCharacter());
            pstm.setInt(3, objPlayer.getIdWeapon());
            pstm.execute();

            JOptionPane.showMessageDialog(null, "your player has born❤️");

            pstm.close();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "PlayerDAO Create " + err );
        }
    }

    public ResultSet readPlayer() {

        String sql = "select id_player, name_character as player_name, life_points, hit_points, name_weapon, damage from character_rpg as C " +
                "inner join player as P on P.id_character=C.id_character " +
                "inner join weapon as W on W.id_weapon=P.id_weapon;";
        conn = new ConnectionDAO().connectDB();

        try {

            pstm = conn.prepareStatement(sql);

            return pstm.executeQuery();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "PlayerDAO Read " + err );
        }
        return null;
    }

    public ResultSet getWeapons() {

        String sql = "select count(*) from weapon";
        conn = new ConnectionDAO().connectDB();
        try {
            pstm = conn.prepareStatement(sql);
            return pstm.executeQuery();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "PlayerDAO Read Weapon" + err );
        }
        return null;
    }
    public ResultSet searchPlayer(String name) {
        String sql = "select id_player, name_character as player_name, life_points, hit_points, name_weapon, damage from player as P " +
                "inner join character_rpg as C on C.id_character = P.id_character " +
                "inner join weapon as W on W.id_weapon = P.id_weapon " +
                "where C.name_character = ?";
        conn = new ConnectionDAO().connectDB();
        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, name);
            return pstm.executeQuery();

        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "PlayerDAO Search " + err );
        }
        return null;
    }


}
