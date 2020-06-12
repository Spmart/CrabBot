# Crabbot

A bot that returns you A and RDNS records. Like that:

```
test.com 
8.8.8.8: 69.172.200.235: 69.172.200.235
ns1.reg.ru: 188.93.212.200: 188.93.212.200
ns2.reg.ru: 188.93.212.200: 188.93.212.200
ns1.hosting.reg.ru: 31.31.196.199: wpl24.hosting.reg.ru
ns2.hosting.reg.ru: 31.31.196.199: wpl24.hosting.reg.ru
ns5.hosting.reg.ru: 37.140.195.160: 37-140-195-160.ovz.vps.regruhosting.ru
ns6.hosting.reg.ru: 134.0.116.126: rm.server.ru
ns7.hosting.reg.ru: 31.31.196.199: wpl24.hosting.reg.ru
ns8.hosting.reg.ru: 31.31.196.199: wpl24.hosting.reg.ru
```

## Dependencies
* JDK 8 *(I guess... I actualy using AdoptOpenJDK 14)*
* Maven
* [Telgrambots](https://github.com/rubenlagus/TelegramBots) library

## Getting started
1\. Clone repository. If you using an IntelliJ IDEA, you can grab all project directly from GitHub by link.
2\. Add **telegrambots** dependency in your *pom.xml*:
```xml
<dependency>
    <groupId>org.telegram</groupId>
    <artifactId>telegrambots</artifactId>
    <version>4.8.1</version>
</dependency>
```
3\. Define bot username and token in BotIdentifier class (use Telegram's *@BotFather* to get it).

```java
private static final String USERNAME = "botname";
private static final String API_TOKEN = "JgLM5Jyhq8fFeVUur5442UXbdZR39YkMf4sN7kT4qNKyab";
```
It's not right way to store token. Best way — store it **outside** your code or pass it in args. That's feature not implemented here. Feel free to implement it yourself.

3\. Build it any available method and run. I use an artifact (JAR) build with IntelliJ IDEA.

3a\. **[Optional]** If you want to use a proxy for your bot, start Tor or [Tor Browser](https://www.torproject.org/) and uncomment this lines in Main class:

```java
System.getProperties().put("proxySet", true);
System.getProperties().put("socksProxyHost", "127.0.0.1");
System.getProperties().put("socksProxyPort", "9150");
```

3b\. **[Optional]** If you deploy bot to VPS, you probably want to run it in the background. You can use [nohup](https://linux.die.net/man/1/nohup) for that, but better way would be a **Systemd** service.

1. Create in *\*.service* file in */etc/systemd/system/*. For example it would be *crabbot.service*.
2. Your service may look like that:
    ```
    [Unit]
    Description=Manage Java crabbot service
    [Service]
    WorkingDirectory=/var/www/telegrambots/crabbot/
    ExecStart=/bin/java -jar CrabBot.jar
    User=crabbot
    Type=simple
    Restart=on-failure
    RestartSec=10
    [Install]
    WantedBy=multi-user.target
    ```
3. Save crabbot.service file, restart systemd, enable service and start it:
    ```bash
    $ sudo systemctl daemon-reload
    $ sudo systemctl enable crabbot.service
    $ sudo systemctl start crabbot.service
    ```
Now your bot runs in background, automaticly restarts on failure and after server reboot.
