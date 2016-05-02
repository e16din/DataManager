# DataManager
Simple way to save and load your data

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
DataManager.getInstance().save(KEY_TOKEN, accessToken);

//load
DataManager.getInstance().loadString(KEY_TOKEN);
//or load
DataManager.getInstance().load(KEY_USER, User.class);
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
    compile 'com.github.e16din:DataManager:0.1.0'
}
