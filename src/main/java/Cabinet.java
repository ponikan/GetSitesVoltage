public abstract class Cabinet<T> {
    private T point1;
    private T point2;

    public Cabinet(T point1, T point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Cabinet(T point1) {
        this.point1 = point1;
    }

    public T getPoint1() {
        return point1;
    }

    public void setPoint1(T point1) {
        this.point1 = point1;
    }

    public T getPoint2() {
        return point2;
    }

    public void setPoint2(T point2) {
        this.point2 = point2;
    }

    @Override
    public String toString() {
        return "Cabinet{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                '}';
    }
}
