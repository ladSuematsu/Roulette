package ladsoft.roulette.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by suematsu on 2/3/15.
 */
public class PlaceHistory implements Serializable {
    public Date date;
    public String place;

    public Date getDate() { return this.date; }
    public void setDate(Date date) { this.date = date; }

    public String getPlace() { return this.place; }
    public void setPlace(String place) { this.place = place; }
}
