public class TwoPowerCabinet <T> extends Cabinet {

    public TwoPowerCabinet(Object point1, Object point2) {
        super(point1, point2);
    }


    @Override
    public String toString() {
        return getPoint1() +
                " : " + getPoint2();
    }
}
