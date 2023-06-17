import DAO.PlayerDAO;
import DTO.PlayerDTO;
import DTO.WeaponDTO;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import java.util.Random;
public class PlayerView {

    PlayerDTO PDTO = new PlayerDTO();
    PlayerDAO PDAO = new PlayerDAO();
    WeaponDTO WDTO = new WeaponDTO();

    private JPanel Main;
    private JTextField fieldWeapon;
    private JTable playerTable;
    private JButton saveButton;
    private JButton attackButton;
    private JTextField fieldSearch;
    private JButton searchButton;
    private JTextField namePlayer;
    private JSlider fieldLife;
    private JSlider fieldDamage;
    private JSlider fieldHit;
    private JButton refresh;

    public JPanel getMain() {
        return Main;
    }

    public void tableLoad() {
        playerTable.setModel(DbUtils.resultSetToTableModel(PDAO.readPlayer()));
    }

    public PlayerView() {

        try {
            tableLoad();
            ResultSet result = PDAO.getLastCharacter();
            if(result.next()) {
                namePlayer.setText(result.getString(2));
                fieldHit.setValue(result.getInt(3));
            }
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "PlayerView load " + err );
        }

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    ResultSet result = PDAO.getLastCharacter();
                    ResultSet resultWeapon = PDAO.getWeapons();

                    if(result.next() && resultWeapon.next()) {

                        //selecionar todas arma + 1 pra pegar id da arma

                        PDTO.setIdCharacter(result.getInt(1));
                        PDTO.setLifePoints(fieldLife.getValue());
                        PDTO.setIdWeapon(resultWeapon.getInt(1) + 1);

                        WDTO.setName(fieldWeapon.getText());
                        WDTO.setDamage(fieldDamage.getValue());

                        PDTO.setWeapon(WDTO);

                        PDAO.createPlayer(PDTO);
                        tableLoad();
                    } else {
                        JOptionPane.showMessageDialog(null, "Player not found❌");
                    }

                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "PlayerView Search " + err );
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet result = PDAO.searchPlayer(fieldSearch.getText());

                    if(result.next()) {
                        namePlayer.setText(result.getString(2));
                        fieldLife.setValue(result.getInt(3));
                        fieldHit.setValue(result.getInt(4));
                        fieldWeapon.setText(result.getString(5));
                        fieldDamage.setValue(result.getInt(6));
                    } else {
                        JOptionPane.showMessageDialog(null, "Player not found❌");
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "Player Search " + err );
                }
            }
        });

        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //gera um número aleatorio de 0 a 20
                Random random = new Random();
                int randomNumber = random.nextInt(21);

                JOptionPane.showMessageDialog(null, "The dice is rolling...🎲" +
                        "\nYour luck has been trowed the dice says ✨" + randomNumber + "✨");
            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tableLoad();
                    ResultSet result = PDAO.getLastCharacter();
                    if(result.next()) {
                        namePlayer.setText(result.getString(2));
                        fieldHit.setValue(result.getInt(3));
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "PlayerView loadBTN " + err );
                }
            }
        });

    }
}
