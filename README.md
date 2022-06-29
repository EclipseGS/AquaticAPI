# AquaticAPI | Copyright © 2022

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/7caf1d679e0f441283b44964cdb2fde2)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=InitDev06/AquaticAPI&amp;utm_campaign=Badge_Grade)

API provided for members of Aquatic Studios for the plugins development, versions supported: **1.8x - 1.19** (Spigot/Paper).

## Installation

How to import the API to your project with Maven:
```xml
<repositories>
    <repository>
        <id>jitpack.id</id>
	<url>https://jitpack.io</url>
    </repository>
</repositories>
```
```xml
<dependencies>
   <dependency>
       <groupId>com.github.InitDev06</groupId>
       <artifactId>AquaticAPI</artifactId>
       <version>1.0.1</version>
   </dependency>
</dependencies>
```

How to import the API to your project with Gradle:
```groovy
repositories {
    mavenCentral()
    maven { url = 'https://jitpack.io' }
}
```

```groovy
dependencies { implementation 'com.github.InitDev06:AquaticAPI:1.0.0' }
```

## Usage

To use the API, you must be create an instance of this.
```java
@Getter
private AquaticAPI aquaticAPI;

@Override
public void onEnable() {
  // Startup Logic.
  aquaticAPI = new AquaticAPI();
 
  getLogger().info("AquaticAPI Loaded Successful!");
}
```
Now you can use the features of the API, as by example, the ActionHandler.
But also we will get another class, the 'Utils' class.
```java
private final ActionHandler actionHandler;
private final Utils utils;

public TestClass(@NotNull AquaticAPI aquaticApi) { 
  this.actionHandler = aquaticApi.getActionHandler();
  this.utils = aquaticApi.getUtils();
}
```

Replay a sound:
```java
@EventHandler (priority = EventPriority.HIGH, ignoreCancelled = true)
public void onSound(PlayerJoinEvent event) {
  // Getting the player.
  final Player player = event.getPlayer();
  
  // Create a string for the action, as example, for replay a sound.
  // Here i using sounds id's provided by XSound (XSeries).
  // Format Example: "sound:volume:pitch".
  String soundFormat = "{#Sound} ENTITY_EXPERIENCE_ORB_PICKUP:10:1"
  
  // Execute the action.
  actionHandler.sound(soundFormat, player);
}
```
Or send a Title:
```java
@EventHandler (priority = EventPriority.HIGH, ignoreCancelled = true)
public void onTitle(PlayerChangedWorldEvent event) {
  // Getting the player.
  final Player player = event.getPlayer();
  
  // Create a string for the action.
  // Format Example: "title-message:subtitle-message:fadeIn-time:stay-time:fadeOut-time".
  String titleFormat = "{#Title} &cTitle:&aThis is a example!:20:60:20"
  
  // Execute the action.
  actionHandler.title(soundFormat, player);
}
```

Now we see the 'Utils' class.
This class provides methods for send titles, action-bars, or also disconnect to a player from server.

Also can colorize the strings with normal colors, or hex colors,
this with the class 'Text'.
```java
@EventHandler (priority = EventPriority.HIGH)
public void onChat(AsyncPlayerChatEvent event) {
  // Getting the player.
  final Player player = event.getPlayer();
  
  // Getting the chat message.
  String message = event.getMessage();
  
  // What action execute?
  if (message.equals("bitch") {
    actionHandler.title(player, 20, 60, 20, "&aTitle", "&cDon't say that!");
    return;
  }
  
  if (message.contains("https") {
    utils.actionBar(player, "&eActionBar Example!", 100L);
    return;
  }
  
  if (message.equals("/stop") {
    actionHandler.disconnect(player, "&cYou has been disconnected from server!");
    return;
  }
  
  // Colorize the message.
  message = Text.color(message);
  
  // Set the message.
  event.setMessage(message);
}
```
