public class NoArmor extends Armor {

    public NoArmor() {
        super(null);
    }

    @Override
    public int getDamageReduction() {
        return 0;
    }

    @Override
    public String getImageFolder() {
        return "soldier_blank";
    }

}
