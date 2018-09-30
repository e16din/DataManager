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

    DataManager.initDefaultDataBox(this)
}
```

## Using

### Kotlin

```kotlin
const val KEY_TOKEN = "KEY_TOKEN"
const val KEY_USER = "KEY_USER"

//save
KEY_TOKEN.put(accessToken)
KEY_USER.put(user)

//load
val token = KEY_TOKEN.get<String>()
val user = KEY_USER.get<User>()
```

### Java

```java
//save
DataManager.getBox().put(KEY_TOKEN, accessToken);
DataManager.getBox().put(KEY_USER, user);

//load
DataBox dataBox = DataManager.getBox()

String token = dataBox.get(KEY_TOKEN);
User user = dataBox.get(KEY_USER, User.class);
```

## Download (Gradle)

```groovy
repositories {
    maven { url "http://dl.bintray.com/e16din/maven" }
}

dependencies {
    compile 'com.github.e16din:DataManager:0.5.6'
}
```
