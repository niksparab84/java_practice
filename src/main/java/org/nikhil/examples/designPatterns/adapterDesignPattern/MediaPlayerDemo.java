package org.nikhil.examples.designPatterns.adapterDesignPattern;

//Target interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

//Adaptee
class AdvancedMediaPlayer {
    void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}

//Adapter
class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(AdvancedMediaPlayer advancedMusicPlayer) {
        this.advancedMusicPlayer = advancedMusicPlayer;
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}

public class MediaPlayerDemo {
    public static void main(String[] args) {
        MediaPlayer mediaPlayer = new MediaAdapter(new AdvancedMediaPlayer());

        mediaPlayer.play("vlc", "example.vlc");
        mediaPlayer.play("mp4", "example.mp4");
    }
}

// The code above demonstrates the Adapter Design Pattern. The Adapter Design Pattern allows incompatible interfaces
// to work together.
// In this example, the `MediaPlayer` interface is the target interface and the `AdvancedMediaPlayer` class is the adaptee.
// The `MediaAdapter` class acts as an adapter that allows the `MediaPlayer` interface to work with the `AdvancedMediaPlayer` class.
// This way, we can play different audio formats (like VLC and MP4) using a common interface (`MediaPlayer`).
// Real-life examples of the Adapter Design Pattern can be found in various scenarios, such as:
// 1. Power adapters: When you travel to a different country, you may need a power adapter to plug your device
// into a different type of electrical outlet.
// 2. Legacy systems: When integrating new software with legacy systems, adapters can be used to convert data formats
// or interfaces so that the new software can communicate with the old system.
// 3. Payment gateways: When integrating multiple payment gateways into an e-commerce platform, adapters can be used to
// standardize the interface for processing payments, allowing the platform to work with different payment providers seamlessly.

// trick to remember adapter design pattern is "Think of a power adapter that allows you to plug in your device into a different type of electrical outlet."
// The adapter design pattern allows incompatible interfaces to work together, just like a power adapter allows you to use your device with different types of electrical outlets.
// trick to remember code implementation of adapter design pattern is "Think of the MediaAdapter class as a power adapter that allows the MediaPlayer interface to work with the AdvancedMediaPlayer class."
// The MediaAdapter class acts as an adapter that converts the interface of the AdvancedMediaPlayer class into the interface expected by the MediaPlayer interface, allowing them to work together seamlessly.
// The adapter design pattern is particularly useful in scenarios where you have existing code that you want to reuse, but the interfaces are incompatible. By using an adapter, you can create a bridge between the two interfaces, allowing them to work together without modifying the existing code.
// The adapter design pattern promotes code reusability and flexibility, making it easier to integrate new functionality into existing systems without breaking existing code.
// The adapter design pattern is also known as the wrapper pattern, as it wraps an existing class with a new interface, allowing it to be used in a different context.

