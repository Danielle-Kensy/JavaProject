package DTO;

public class PlayerDTO extends CharacterDTO {
    private int lifePoints;
    private int idCharacter;
    private int idWeapon;
    private WeaponDTO weaponDTO;

    public PlayerDTO(){
        
    }

    public int getIdCharacter() {
        return idCharacter;
    }

    public int getIdWeapon() {
        return idWeapon;
    }

    public void setIdWeapon(int idWeapon) {
        this.idWeapon = idWeapon;
    }

    public void setIdCharacter(int id) {
        this.idCharacter = id;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public WeaponDTO getWeapon() {
        return weaponDTO;
    }

    public void setWeapon(WeaponDTO weaponDTO) {
        this.weaponDTO = weaponDTO;
    }

}
