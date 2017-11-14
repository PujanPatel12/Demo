/**
 * Created by pujan on 10/8/17.
 */
public class  Weapon {


    private String Weaponname;
    public int WeaponID;



    public  void setWeaponID(int weaponID) {

        WeaponID = weaponID;
    }

    public int getWeaponID() {
        return WeaponID;
    }


    public  void setWeaponname(String weaponname) {

        Weaponname = weaponname;
    }

    public String getWeaponname() {

        return Weaponname;
    }




    public Weapon(){

    }

    @Override
    public String toString() {
        return "Weapon{" +
                "Weaponname='" + Weaponname + '\'' +
                ", WeaponID=" + WeaponID +
                '}';
    }

}
