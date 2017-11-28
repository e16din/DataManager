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

public final class DataManager {

    private static SharedPreferences sPreferences;
    private static Gson sGson = new Gson();
    private static boolean sNeedCommit = true;


    private DataManager() {
    }


    public static void init(Context context) {
        sPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferences getSharedPreferences() {
        try {
            return sPreferences;
        } catch (NullPointerException e) {
            throw new NullPointerException("Please initialize DataManager. DataManager.init(sContext))");
        }
    }

    public static boolean contains(final String key) {
        return getSharedPreferences().contains(key);
    }

    public static void putToEditor(SharedPreferences.Editor editor, String key, Object value) {
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
        } else if (value instanceof Set) {
            editor.putStringSet(key, (Set<String>) value);
        } else {
            editor.putString(key, getGson().toJson(value));
        }
    }

    public static void saveObj(String key, Object value) {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();

        putToEditor(editor, key, value);

        apply(editor);
    }

    public static void saveAll(final Set<Map.Entry<String, Object>> data) {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();

        for (Map.Entry<String, Object> object : data) {
            final Object value = object.getValue();
            final String key = object.getKey();

            putToEditor(editor, key, value);
        }

        apply(editor);
    }

    public static void saveAll(final Map<String, Object> data) {
        saveAll(data.entrySet());
    }

    private static void apply(SharedPreferences.Editor editor) {
        if (needCommit()) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    public static void remove(String key) {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.remove(key);
        apply(editor);
    }

    public static void clear() {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        apply(editor);
    }

    public static <T> T load(final String key, final Type type) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null;
        }

        final String string = getSharedPreferences().getString(key, null);
        if (string == null) {
            return null;
        }

        return sGson.fromJson(string, type);
    }

    public static String loadString(final String key) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null;
        }

        return getSharedPreferences().getString(key, null);
    }

    public static boolean loadBool(final String key) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return false;
        }

        return getSharedPreferences().getBoolean(key, false);
    }

    public static int loadInt(final String key) {
        return loadInt(key, -1);
    }

    public static int loadInt(final String key, int defaultValue) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return defaultValue;
        }

        return getSharedPreferences().getInt(key, defaultValue);
    }

    public static long loadLong(final String key) {
        return loadLong(key, -1L);
    }

    public static long loadLong(final String key, long defaultValue) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return defaultValue;
        }

        return getSharedPreferences().getLong(key, defaultValue);
    }

    public static float loadFloat(final String key) {
        return loadFloat(key, -1f);
    }

    public static float loadFloat(final String key, float defaultValue) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return defaultValue;
        }

        return getSharedPreferences().getFloat(key, defaultValue);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Set<String> loadStringSet(final String key) {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null;
        }

        return getSharedPreferences().getStringSet(key, null);
    }

    public static boolean needCommit() {
        return sNeedCommit;
    }

    public static void setNeedCommit(boolean needCommit) {
        sNeedCommit = needCommit;
    }

    public static Gson getGson() {
        return sGson;
    }

    public static void setGson(Gson gson) {
        sGson = gson;
    }
}
