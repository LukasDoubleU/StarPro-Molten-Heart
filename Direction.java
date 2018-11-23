public enum Direction {
    Up(180), Down(90), Left(180), Right(0);

    int rotation;

    Direction(int rotation) {
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }
}