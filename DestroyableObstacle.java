public abstract class DestroyableObstacle extends Enemy {

    public DestroyableObstacle(String img) {
        super(0, 1, img);
    }

    @Override
    public String getHitSound() {
        return "breaking_stone.wav";
    }

    public static class Rock extends DestroyableObstacle {

        public Rock() {
            super("img_utilities/rock.png");
        }
    }

    public static class Slime extends DestroyableObstacle {

        public Slime() {
            super("img_utilities/slime.png");
        }
    }

    public static class Barrel extends DestroyableObstacle {

        public Barrel() {
            super("img_utilities/barrel.png");
        }

        @Override
        public String getHitSound() {
            return "breaking_wood.wav";
        }
    }

}
