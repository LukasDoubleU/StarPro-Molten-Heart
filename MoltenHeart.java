public class MoltenHeart extends Potion {

    public MoltenHeart() {
        super("Molten_Heart.png");
    }

    @Override
    public void drink(Player p) {
        ((Level) getWorld()).finish();
    }
}
