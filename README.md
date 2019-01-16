# thumbnail4j
[![](https://jitpack.io/v/wowselim/thumbnail4j.svg)](https://jitpack.io/#wowselim/thumbnail4j)

Java wrapper for [convert](https://imagemagick.org/script/convert.php).

## Prerequisites
* `convert` in your PATH
* Java 9

## Including thumbnail4j in a project

First, add thumbnail4j to your dependencies using the [jitpack repository](https://jitpack.io/#wowselim/thumbnail4j).

### Usage
```java
URI fileUri = new File("bird.JPG").toURI(); // or Paths.get("bird.JPG").toUri();
byte[] thumbnail = new ThumbnailCreator().createThumbnail(1024, fileUri);
```
