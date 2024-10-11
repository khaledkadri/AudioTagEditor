# Audio Tags Editor
![Application Screenshot](https://www.codeurjava.com/assets/github/repositories/AudioTagEditor/screenshot.png)

## Description
Audio Tags Editor is an application created in Java with a very simple and intuitive interface that allows you to edit the information
of an MP3 audio file, such as the title, album, year, author, composer, and the album image.

## Requirements
- Java 8
- Download and install VLC
Libraries
- [mp3agic](https://github.com/mpatric/mp3agic)
- [vlcj](https://github.com/caprica/vlcj)
- [flatlaf](https://www.formdev.com/flatlaf/)

Change the path in Media.java class to point to the VLC installation.

```java
NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), "/Directory path/to/VLC installation");
```

## License
This project is licensed under the GPL-3.0 license. For more details, please see the LICENSE file.
