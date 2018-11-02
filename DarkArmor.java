public class DarkArmor extends Armor {

    public DarkArmor() {
        super(""); // TODO
    }

    @Override
    public int getDamageReduction() {
        return 2;
    }

    @Override
    public String getImageFolder() {
        return "soldier_dark";
    }

}
