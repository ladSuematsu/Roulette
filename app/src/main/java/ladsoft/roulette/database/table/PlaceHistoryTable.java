package ladsoft.roulette.database.table;

/**
 * Created by suematsu on 3/24/15.
 */
public interface PlaceHistoryTable {
    String TABLE_NAME = "place_history";

    static String ID = "_id";
    static String PLACE_NAME = "name";
    static String SORT_DATE = "sort_date";

    static String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", "
            + PLACE_NAME + " TEXT" + ", "
            + SORT_DATE + " DATE" + ")";

    String WHERE_ID_EQUALS = null;
    static String[] ALL_COLUMNS = {ID, PLACE_NAME, SORT_DATE};
}
