import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public abstract class Entry extends Actor {

    Menu menu;
    GreenfootImage normal, selected;
    boolean normalFlag = true;

    public Entry(Menu menu, String normalImage, String selectedImage) {
        this.menu = menu;
        normal = new GreenfootImage(normalImage);
        selected = new GreenfootImage(selectedImage);
        setImage(normal);
    }

    @Override
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            onClick();
        }
    }

    public boolean isSelected() {
        return menu.selectedEntry == this;
    }

    public void select() {
        if (!isSelected()) {
            if (menu.selectedEntry != null) {
                menu.selectedEntry.deselect();
            }
            menu.selectedEntry = this;
            SoundUtil.playSound("menu_select.wav");
            setImage(selected);
        }
    }

    public void deselect() {
        setImage(normal);
    }

    public void selectNext() {
        Entry entry = menu.getEntryWithIndex(getIndex() + 1);
        if (entry != null) {
            entry.select();
        }
    }

    public void selectPrevious() {
        Entry entry = menu.getEntryWithIndex(getIndex() - 1);
        if (entry != null) {
            entry.select();
        }
    }

    public final void onClick() {
        SoundUtil.playSound("menu_click.wav");
        _onClick();
    }

    protected abstract void _onClick();

    public abstract int getIndex();
}