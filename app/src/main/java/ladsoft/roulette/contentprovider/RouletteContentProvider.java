package ladsoft.roulette.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

import ladsoft.roulette.database.Database;
import ladsoft.roulette.database.table.PlaceHistoryTable;

/**
 * Created by suematsu on 3/24/15.
 */
public class RouletteContentProvider extends ContentProvider {

    private static final String AUTHORITY = "ladsoft.roulette";
    private static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);
    private static UriMatcher URI_MATCHER;
    private Database database;

    // References of DIR and ID
    private static final int PLACE_DIR = 0;
    private static final int PLACE_ID = 1;

    // Configuration of UriMatcher
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

        URI_MATCHER.addURI(AUTHORITY, PlaceHistoryContent.CONTENT_PATH, PLACE_DIR);
        URI_MATCHER.addURI(AUTHORITY, PlaceHistoryContent.CONTENT_PATH + "/#", PLACE_ID);
    }

    // placeHistoryDescription URIs
    public static final Uri PLACE_HISTORY_CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PlaceHistoryContent.CONTENT_PATH);


    /**
     * Provides the content information of the PlaceHistoryTable
     */
    private static final class PlaceHistoryContent implements BaseColumns {

        /**
         * Specifies the content path of the PostTable for the required Uri.
         */
        public static final String CONTENT_PATH = "place_history";
        /**
         * Specifies the type for the folder and the single item o f the PlaceTable.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.mdsdacp.place";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.mdsdacp.place";
    }

    /**
     * Instantiate the database, when the content provider is created.
     * @return true
     */
    @Override
    public boolean onCreate() {
        database = new Database(getContext());
        return true;
    }

    /**
     * Execute a query on a given uri and return a Cursor with results.
     *
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     *
     * @return Cursor with results
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase dbConnection = database.getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch(URI_MATCHER.match(uri)) {

            case PLACE_DIR:
                queryBuilder.setTables(PlaceHistoryTable.TABLE_NAME);
            break;

            case PLACE_ID:
                queryBuilder.appendWhere(PlaceHistoryTable.ID + "=" + uri.getPathSegments().get(1));
            break;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);

        }

        Cursor cursor = queryBuilder.query(dbConnection, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    /**
     * Provide information whether uri returns an item or an directory
     *
     * @param uri
     * @return content type from type placeHistoryDescription.CONTENT_TYPE or placeHistoryDescription.CONTENT_ITEM_TYPE
     */
    @Override
    public String getType(Uri uri) {
        switch(URI_MATCHER.match(uri)) {
            case PLACE_DIR:
                return PlaceHistoryContent.CONTENT_TYPE;

            case PLACE_ID:
                return PlaceHistoryContent.CONTENT_ITEM_TYPE;

            default:
                    throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    /**
     * Insert given values to given uri.
     *
     * @param uri
     * @param values from type ContentValues
     *
     * @return uri of inserted element from type Uri
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase dbConnection = database.getWritableDatabase();

        try {
            dbConnection.beginTransaction();

            switch(URI_MATCHER.match(uri)) {
                case PLACE_DIR:
                case PLACE_ID:
                    Long postId = dbConnection.insertOrThrow(PlaceHistoryTable.TABLE_NAME, null, values);
                    Uri newPlace = ContentUris.withAppendedId(PLACE_HISTORY_CONTENT_URI, postId);
                    getContext().getContentResolver().notifyChange(newPlace, null);
                    dbConnection.setTransactionSuccessful();
                    return newPlace;

                default:
                    throw new IllegalArgumentException("Unsupported URI: " + uri);
            }

        }
        finally {
            dbConnection.endTransaction();
        }
    }

    /**
     * Delete given elements by their uri and return the number of deleted rows.
     *
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase dbConnection = database.getWritableDatabase();
        int deleteCount = 0;

        try {
            dbConnection.beginTransaction();

            switch(URI_MATCHER.match(uri)) {

                case PLACE_DIR:
                    deleteCount = dbConnection.delete(PlaceHistoryTable.TABLE_NAME, selection, selectionArgs);
                    dbConnection.setTransactionSuccessful();
                    break;

                case PLACE_ID:
                    deleteCount = dbConnection.delete(PlaceHistoryTable.TABLE_NAME, PlaceHistoryTable.WHERE_ID_EQUALS, new String[]{uri.getPathSegments().get(1)});
                    dbConnection.setTransactionSuccessful();
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported URI: " + uri);
            }
        }
        finally {
            dbConnection.endTransaction();
        }

        if(deleteCount > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deleteCount;
    }

    /**
     * Update given values of given uri, returning number of affected rows.
     *
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        SQLiteDatabase dbConnection = database.getWritableDatabase();
        int updateCount = 0;

        try {
            switch(URI_MATCHER.match(uri)) {
                case PLACE_DIR:
                    updateCount = dbConnection.update(PlaceHistoryTable.TABLE_NAME, values, selection, selectionArgs);
                    dbConnection.setTransactionSuccessful();
                    break;

                case PLACE_ID:
                    Long postId = ContentUris.parseId(uri);
                    updateCount = dbConnection.update(PlaceHistoryTable.TABLE_NAME, values,
                            PlaceHistoryTable.ID + "=" + postId + (TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")"),
                            selectionArgs);
                    dbConnection.setTransactionSuccessful();
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported URI: " + uri);
            }
        }
        finally {
            dbConnection.endTransaction();
        }

        if (updateCount > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return updateCount;
    }

}
