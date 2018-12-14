public class MoltenHeart extends Potion {

    public MoltenHeart() {
        super("Molten_Heart.png");
    }

    @Override
    public void _drink(Player p) {
        ((Level) getWorld()).finish();
    }
}
