package com.e16din.datamanager;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

public class DataManager {

    private Context mContext;
    private Gson mGson = new Gson();
    private boolean mNeedCommit = true;


    private static class Holder {
        public static final DataManager HOLDER_INSTANCE = new DataManager();
    }

    public static DataManager instance() {
        return Holder.HOLDER_INSTANCE;
    }

    public static void init(Context context) {
        instance().setContext(context);
    }


    public void setContext(Context context) {
        mContext = context;
    }

    public SharedPreferences getDefaultSharedPreferences() {
        try {
            return PreferenceManager.getDefaultSharedPreferences(mContext);
        } catch (NullPointerException e) {
            throw new NullPointerException("Please initialize DataManager. DataManager.init(sContext))");
        }
    }

    public boolean contains(final String key) {
        return getDefaultSharedPreferences().contains(key);
    }

    public <T> void save(final String key, final T data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putString(key, mGson.toJson(data));
        apply(editor);
    }

    public void saveAll(final Set<Map.Entry<String, Object>> data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();

        for (Map.Entry<String, Object> object : data) {
            final Object value = object.getValue();
            final String key = object.getKey();

            if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value instanceof String) {
                editor.putString(key, (String) value);
            } else if (value instanceof Long) {
                editor.putLong(key, (Long) value);
            } else if (value instanceof Float) {
                editor.putFloat(key, (Float) value);
            } else {
                editor.putString(key, mGson.toJson(data));
            }
        }

        apply(editor);
    }

    public void saveAll(final Map<String, Object> data) {
        saveAll(data.entrySet());
    }

    public void save(final String key, final boolean data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putBoolean(key, data);
        apply(editor);
    }

    public void save(final String key, final String data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putString(key, data);
        apply(editor);
    }

    public void save(final String key, final int data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putInt(key, data);
        apply(editor);
    }

    public void save(final String key, final long data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putLong(key, data);
        apply(editor);
    }

    public void save(final String key, final float data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putFloat(key, data);
        apply(editor);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void save(final String key, final Set<String> data) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.putStringSet(key, data);
        apply(editor);
    }

    private void apply(SharedPreferences.Editor editor) {
        if (mNeedCommit) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    public void remove(String key) {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.remove(key);
        apply(editor);
    }

    public void clear() {
        final SharedPreferences.Editor editor = getDefaultSharedPreferences().edit();
        editor.clear();
        apply(editor);
    }

    public <T> T load(final String key, final Type type) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null;
        }

        final String string = getDefaultSharedPreferences().getString(key, null);
        if (string == null) {
            return null;
        }

        return mGson.fromJson(string, type);
    }

    public String loadString(final String key) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null;
        }

        return getDefaultSharedPreferences().getString(key, null);
    }

    public boolean loadBool(final String key) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return false;
        }

        return getDefaultSharedPreferences().getBoolean(key, false);
    }

    public int loadInt(final String key) {
        return loadInt(key, -1);
    }

    public int loadInt(final String key, int defaultValue) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return defaultValue;
        }

        return getDefaultSharedPreferences().getInt(key, defaultValue);
    }

    public long loadLong(final String key) {
        return loadLong(key, -1L);
    }

    public long loadLong(final String key, long defaultValue) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return defaultValue;
        }

        return getDefaultSharedPreferences().getLong(key, defaultValue);
    }

    public float loadFloat(final String key) {
        return loadFloat(key, -1f);
    }

    public float loadFloat(final String key, float defaultValue) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return defaultValue;
        }

        return getDefaultSharedPreferences().getFloat(key, defaultValue);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> loadStringSet(final String key) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null;
        }

        return getDefaultSharedPreferences().getStringSet(key, null);
    }


    public boolean needCommit() {
        return mNeedCommit;
    }

    public void setNeedCommit(boolean needCommit) {
        this.mNeedCommit = needCommit;
    }

    public Gson getGson() {
        return mGson;
    }

    public void setGson(Gson gson) {
        this.mGson = gson;
    }
}
