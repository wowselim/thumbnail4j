# thumbnail4j
[![](https://jitpack.io/v/wowselim/thumbnail4j.svg)](https://jitpack.io/#wowselim/thumbnail4j)

Java wrapper for [convert](https://imagemagick.org/script/convert.php).

## Prerequisites
* `convert` in your PATH
* Java 8

## Including thumbnail4j in a project

First, add thumbnail4j to your dependencies using the [jitpack repository](https://jitpack.io/#wowselim/thumbnail4j).

### Usage
**Java**
```java
byte[] thumbnailBytes;
try(inputStream) {
    thumbnailBytes = ThumbnailCreator.createThumbnail(1024, inputStream);
}
```

**Kotlin**
```Kotlin
val thumbnailBytes = inputStream.use { it.createThumbnail(1024) }
```
