package lv.nixx.poc.exh.handler;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class NotificationMarker {

    private NotificationMarker() {
    }

    public static Marker notification() {
        return MarkerFactory.getMarker("NOTIFICATION");
    }

}
