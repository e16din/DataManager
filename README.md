# DataManager
The simple way to save and load your data. 
Based on [SharedPreferences](https://developer.android.com/reference/android/content/SharedPreferences.html) and [GSON](https://github.com/google/gson).

[![Release](https://jitpack.io/v/e16din/DataManager.svg)](https://jitpack.io/#e16din/DataManager)

## Init
```java
//Application onCreate
@Override
public void onCreate() {
    super.onCreate();

    DataManager.init(this);//set application context
}
```

## Using

```java
//save
DataManager.save(KEY_TOKEN, accessToken);

//load
DataManager.loadString(KEY_TOKEN);
//or load
DataManager.load(KEY_USER, User.class);
```

## Download (Gradle)

```groovy
repositories {
    maven { url "https://jitpack.io" }
}

buildscript {
    repositories {
        maven { url "https://jitpack.io" }
    }
}

dependencies {
    compile 'com.github.e16din:DataManager:0.2.0'
}
