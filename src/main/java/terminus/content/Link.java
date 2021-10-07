package terminus.content;

import java.time.LocalTime;

public class Link extends Content {

    private String day;
    private LocalTime startTime;
    private String link;
    public static final String TYPE = "Z";

    private static final String DISPLAY_LINK_MESSAGE = "%s (%s, %s): %s";

    public Link(String name, String day, LocalTime startTime, String link) {
        super(name);
        this.day = day;
        this.startTime = startTime;
        this.link = link;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String getDisplayInfo() {
        return String.format(DISPLAY_LINK_MESSAGE, this.name, day, startTime, link);
    }

    @Override
    public String getViewDescription() {
        return getDisplayInfo();
    }
}
