public class OnePowerCabinet<T> extends Cabinet {

    public OnePowerCabinet(Object point1) {
        super(point1);
    }

    @Override
    public String toString() {
        return ""+getPoint1();
    }
}
