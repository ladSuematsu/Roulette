package ladsoft.roulette.manager;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.support.v4.content.CursorLoader;
import android.net.Uri;

import ladsoft.roulette.contentprovider.RouletteContentProvider;

/**
 * Created by suematsu on 3/24/15.
 */
public class DatabaseManager {
    private Context mContext;

    public DatabaseManager(Context context) {
        mContext = context;
    }

    public long insert(final Uri uri, final ContentValues contentValues) {
        ContentResolver contentResolver = mContext.getContentResolver();

        Uri resultUri = contentResolver.insert(RouletteContentProvider.PLACE_HISTORY_CONTENT_URI
                        , contentValues);

        return ContentUris.parseId(resultUri);
    }

    public CursorLoader query(final Uri uri
                                , final String[] projection
                                , final String selection
                                , final String[] selectionArgs
                                , final String sortOrder) {
        CursorLoader cursorLoader = new CursorLoader(mContext
                ,RouletteContentProvider.PLACE_HISTORY_CONTENT_URI
                , projection
                , selection
                , selectionArgs
                , sortOrder);

        return cursorLoader;
    }
}
