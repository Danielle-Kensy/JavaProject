package DTO;

public class MonsterDTO extends CharacterDTO {
    private int magicPoints;
    private int idCharacter;

    public MonsterDTO() {
    }

    public int getIdCharacter() {
        return idCharacter;
    }

    public void setIdCharacter(int id) {
        this.idCharacter = id;
    }

    public int getMagicPoints() {
        return magicPoints;
    }

    public void setMagicPoints(int magicPoints) {
        this.magicPoints = magicPoints;
    }
}
