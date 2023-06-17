import DAO.MonsterDAO;
import DTO.MonsterDTO;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class MonsterView {

    MonsterDTO MDTO = new MonsterDTO();
    MonsterDAO MDAO = new MonsterDAO();

    private JPanel Main;
    private JLabel magicPoints;
    private JTable monsterTable;
    private JButton deleteButton;
    private JButton saveButton;
    private JTextField fieldSearch;
    private JButton searchButton;
    private JSlider fieldMagic;
    private JTextField nameMonster;
    private JButton refresh;
    private JSlider fieldHit;

    public void tableLoad() {
        monsterTable.setModel(DbUtils.resultSetToTableModel(MDAO.readMonster()));
    }

    public JPanel getMain() {
        return Main;
    }

    public MonsterView() {

        try {
            tableLoad();
            ResultSet result = MDAO.getLastCharacter();
            if(result.next()) {
                nameMonster.setText(result.getString(2));
                fieldHit.setValue(result.getInt(3));
            }
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "MonsterView load " + err );
        }

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    //pega o último personagem registrado
                    ResultSet result = MDAO.getLastCharacter();

                    if(result.next()) {
                        MDTO.setMagicPoints(fieldMagic.getValue());
                        MDTO.setIdCharacter(result.getInt(1));

                        MDAO.createMonster(MDTO);
                        tableLoad();
                    } else {
                        //retornar personagem não encontrado
                        JOptionPane.showMessageDialog(null, "Monster not found❌");
                    }

                } catch (Exception err) {
                    //joptionpane para abrir uma caixa de diálogo
                    JOptionPane.showMessageDialog(null, "MonsterView Search " + err );
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                        ResultSet result = MDAO.searchMonster(fieldSearch.getText());

                    if(result.next()) {
                        nameMonster.setText(result.getString(2));
                        fieldMagic.setValue(result.getInt(3));
                    } else {
                        JOptionPane.showMessageDialog(null, "Monster not found❌");
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "MonsterView Search " + err );
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet result = MDAO.searchMonster(fieldSearch.getText());
                    if(result.next()) {
                        MDAO.deleteMonster(result.getInt(1));
                        tableLoad();
                        nameMonster.setText("");
                        fieldMagic.setValue(15);
                    } else {
                        JOptionPane.showMessageDialog(null, "The delete failed❌");
                    }

                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "MonsterView Delete " + err );
                }
            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tableLoad();
                    ResultSet result = MDAO.getLastCharacter();
                    if(result.next()) {
                        nameMonster.setText(result.getString(2));
                        fieldHit.setValue(result.getInt(3));
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "MonsterView load " + err );
                }
            }
        });
    }
}
