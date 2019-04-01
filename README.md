![](https://dev.lutece.paris.fr/jenkins/buildStatus/icon?job=chat-plugin-chatbot-deploy)
# Plugin ChatBot

![](https://dev.lutece.paris.fr/plugins/plugin-chatbot/images/chatbot.jpg)

## Introduction

This plugin provides a chat UI that can run bots.

Bots can be made by simply configure rules into a separate Lutece module : `module-chatbot-mybot` .

See the [online demo](http://dev.lutece.paris.fr/incubator/jsp/site/Portal.jsp?page=chatbot) .

# Usage

## As a web page

For Front office web bots :


```

  http://domain.com/context/jsp/site/Portal.jsp?page=chatbot
                
```


## As a popup available on all pages 

To display a popup available on all pages of a site using a page include : add a bookmark **${chat_popup_include}** into the page_frameset.html file.

# Configuring conversation logging

In the Lutece **config.properties** file add a specific logger as below :

```

                
log4j.logger.lutece.chatbot=INFO, ChatBot
                                
# File "chatbot.log"
log4j.appender.ChatBot=org.apache.log4j.RollingFileAppender
log4j.appender.ChatBot.File=***** your logs path *****/chatbot.log
log4j.appender.ChatBot.Append=true
log4j.appender.ChatBot.layout=org.apache.log4j.PatternLayout
log4j.appender.ChatBot.layout.ConversionPattern=%d{"dd/MM/yy", "HH:mm:ss"} ,%m%n
log4j.appender.ChatBot.MaxFileSize=1000KB
log4j.appender.ChatBot.MaxBackupIndex=5
                
            
```


[Maven documentation and reports](https://dev.lutece.paris.fr/plugins/plugin-chatbot/)



 *generated by [xdoc2md](https://github.com/lutece-platform/tools-maven-xdoc2md-plugin) - do not edit directly.*