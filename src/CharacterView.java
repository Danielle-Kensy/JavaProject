import DAO.CharacterDAO;
import DTO.CharacterDTO;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CharacterView extends JFrame{

    CharacterDTO CDTO = new CharacterDTO();
    CharacterDAO CDAO = new CharacterDAO();

    //váriaveis de interface
    private JPanel Main;
    private JButton saveButton;
    private JTextField fieldName;
    private JTextField fieldSearch;
    private JSlider fieldhitPoints;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton buttonSearch;
    private JTable characterTable;
    private JButton monsterButton;
    private JButton playerButton;



    public void tableLoad() {
        characterTable.setModel(DbUtils.resultSetToTableModel(CDAO.readCharacters()));
    }

    public JPanel getMain() {
        return Main;
    }

    public CharacterView() {
        setTitle("Navegação entre JFrames");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tableLoad();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CDTO.setName(fieldName.getText());
                CDTO.setHitPoints(fieldhitPoints.getValue());

                //o método de criação que vem da classe DAO espera receber um objeto
                //DTO, então o passamos na função como parametro
                CDAO.createCharacter(CDTO);
                //ao final da operação carregar novamente a tabela
                tableLoad();
                fieldName.setText("");
                fieldhitPoints.setValue(15);
            }
        });

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet result = CDAO.searchCharacter(fieldSearch.getText());
                    //next() é usado para avançar para a próxima linha de um conjunto de resultados (result set) de uma consulta SQL.
                    //Ele retorna true se houver uma próxima linha e false caso contrário.
                    if(result.next()) {
                        //seta nos campos o personagem retornado
                        fieldName.setText(result.getString(2));
                        fieldhitPoints.setValue(result.getInt(3));
                    } else {
                        //retornar personagem não encontrado
                        JOptionPane.showMessageDialog(null, "Character not found❌");
                    }

                } catch (Exception err) {
                    //joptionpane para abrir uma caixa de diálogo
                    JOptionPane.showMessageDialog(null, "CharacterView Search " + err );
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setando os valores no meu dto
                CDTO.setName(fieldName.getText());
                CDTO.setHitPoints(fieldhitPoints.getValue());

                try {
                    ResultSet result = CDAO.searchCharacter(fieldSearch.getText());
                    if (result.next()) {
                        System.out.println(CDTO.getName());
                        System.out.println(result);
                        //passando para o update os parâmetros necessários
                        CDAO.updateCharacter(CDTO, result.getInt(1));
                        tableLoad();
                        fieldName.setText("");
                        fieldhitPoints.setValue(15);
                    } else {
                        JOptionPane.showMessageDialog(null, "The update failed❌");
                    }

                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "CharacterView Update " + err );
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet result = CDAO.searchCharacter(fieldSearch.getText());
                    if(result.next()) {
                        CDAO.deleteCharacter(result.getInt(1));
                        tableLoad();
                        fieldName.setText("");
                        fieldhitPoints.setValue(15);
                    } else {
                        JOptionPane.showMessageDialog(null, "The delete failed❌");
                    }

                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "CharacterView Delete " + err );
                }
            }
        });

        monsterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MonsterView monster = new MonsterView();
                JFrame frameM = new JFrame("Monster");
                frameM.setContentPane(monster.getMain());
                frameM.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameM.pack();
                frameM.setVisible(true);
                monster.tableLoad();
            }
        });

        playerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerView player = new PlayerView();
                JFrame frameP = new JFrame("PlayerView");
                frameP.setContentPane(player.getMain());
                frameP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameP.pack();
                frameP.setVisible(true);
                player.tableLoad();
            }
        });
    }
}
