# thumbnail4j

Java wrapper for [convert](https://imagemagick.org/script/convert.php).

## Prerequisites
* `convert` in your PATH
* Java 8

## Usage

First, add thumbnail4j to your dependencies using the [jitpack repository](https://jitpack.io/#wowselim/thumbnail4j).

### Example
```java
CompletableFuture<byte[]> thumbnailFuture = ThumbnailCreator.createThumbnail(1024, urlToFile);
byte[] thumbnailBytes = thumbnailFuture.exceptionally(throwable -> new byte[0]).get();
```
