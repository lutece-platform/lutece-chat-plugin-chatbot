/*
 * Copyright (c) 2002-2017, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.chatbot.web;

import fr.paris.lutece.plugins.chatbot.business.BotDescription;
import fr.paris.lutece.plugins.chatbot.service.bot.ChatBot;
import fr.paris.lutece.plugins.chatbot.business.Post;
import fr.paris.lutece.plugins.chatbot.service.BotService;
import static fr.paris.lutece.plugins.chatbot.service.BotService.getBots;
import fr.paris.lutece.plugins.chatbot.service.ChatService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.xpage.MVCApplication;
import fr.paris.lutece.portal.util.mvc.xpage.annotations.Controller;
import fr.paris.lutece.portal.web.l10n.LocaleService;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.util.url.UrlItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * This class provides a simple implementation of an XPage
 */
@Controller( xpageName = "chatbot", pageTitleI18nKey = "chatbot.xpage.bot.pageTitle", pagePathI18nKey = "chatbot.xpage.bot.pagePathLabel" )
public class ChatBotApp extends MVCApplication
{
    private static final String TEMPLATE_BOT = "/skin/plugins/chatbot/bot.html";
    private static final String TEMPLATE_BOT_STANDALONE = "/skin/plugins/chatbot/bot_standalone.html";
    private static final String TEMPLATE_BOTS_LIST = "/skin/plugins/chatbot/bots_list.html";
    private static final String MARK_BOTS_LIST = "bots_list";
    private static final String MARK_POSTS_LIST = "posts_list";
    private static final String MARK_BOT_AVATAR = "bot_avatar";
    private static final String MARK_BASE_URL = "base_url";
    private static final String PARAMETER_BOT = "bot";
    private static final String PARAMETER_RESPONSE = "response";
    private static final String PARAMETER_LANGUAGE = "lang";
    private static final String PARAMETER_STANDALONE = "standalone";
    private static final String VIEW_LIST = "list";
    private static final String VIEW_BOT = "bot";
    private static final String ACTION_RESPONSE = "response";
    private static final String URL_BOT = "jsp/site/Portal.jsp?page=chatbot&view=bot";
    private static final String PROPERTY_STANDALONE = "chatbot.standalone";
    
    private static final long serialVersionUID = 1L;

    private String _strBotKey;
    private Locale _locale;
    private String _strConversationId;
    private ChatBot _bot;
    private boolean _bStandalone;

    /**
     * Returns the content of the list of bots page
     * 
     * @param request
     *            The HTTP request
     * @return The page
     */
    @View( value = VIEW_LIST, defaultView = true )
    public XPage viewList( HttpServletRequest request )
    {
        List<BotDescription> listBots = getBotsDescription( );

        Map<String, Object> model = getModel( );
        model.put( MARK_BOTS_LIST, listBots );

        return getXPage( TEMPLATE_BOTS_LIST, request.getLocale( ), model );
    }

    /**
     * Returns the content of the bot page.
     * 
     * @param request
     *            The HTTP request
     * @return The page
     */
    @View( VIEW_BOT )
    public XPage viewBot( HttpServletRequest request )
    {
        String strBotKey = request.getParameter( PARAMETER_BOT );
        if( strBotKey == null )
        {
            // assuming the bot is the current session bot
            if( _strBotKey == null )
            {
                // session is closed
                return redirectView( request, VIEW_LIST );
            }
        }
        else
        {
            if( ! strBotKey.equals( _strBotKey ) )
            {
                // new bot or bot has changed
                _strBotKey = strBotKey;
                _bot = BotService.getBot( _strBotKey );
                _locale = getBotLocale( request );
                _strConversationId = getNewConversationId( );
                _bStandalone = ( _bot.isStandalone() ) ? true : getStandalone( request );
            }
        }
        
        List<Post> listPosts = ChatService.getConversation( _strConversationId );
        Map<String, Object> model = getModel( );
        model.put( MARK_POSTS_LIST, listPosts );
        model.put( MARK_BOT_AVATAR, _bot.getAvatarUrl( ) );
        model.put( MARK_BASE_URL , AppPathService.getBaseUrl( request ));

        String strTemplate = ( _bStandalone ) ? TEMPLATE_BOT_STANDALONE : TEMPLATE_BOT;
        XPage xpage = getXPage( strTemplate, request.getLocale( ), model );
        xpage.setTitle( _bot.getName( _locale ) );
        xpage.setPathLabel( _bot.getName( _locale ) );
        xpage.setStandalone( _bStandalone );

        return xpage;
    }

    /**
     * Process the response
     * 
     * @param request
     *            The HTTP request
     * @return The redirected page
     */
    @Action( ACTION_RESPONSE )
    public XPage doProcessMessage( HttpServletRequest request )
    {
        String strMessage = request.getParameter( PARAMETER_RESPONSE );

        ChatService.processMessage( request, _strConversationId, strMessage, _strBotKey, _locale );
        Map<String,String> mapParameters = new HashMap<>();
        mapParameters.put( PARAMETER_BOT, _strBotKey );
        mapParameters.put( PARAMETER_LANGUAGE, _locale.getLanguage() );
        mapParameters.put( PARAMETER_STANDALONE, _bStandalone ? "true" : "false" );

        return redirect( request, VIEW_BOT , mapParameters );
    }

    /**
     * Get request information for the bot language
     * 
     * @param request
     *            The request
     * @return The locale
     */
    private Locale getBotLocale( HttpServletRequest request )
    {
        String strLanguage = request.getParameter( PARAMETER_LANGUAGE );

        if ( strLanguage != null )
        {
            return new Locale( strLanguage );
        }

        return LocaleService.getDefault( );
    }

    /**
     * Gets the list of bots
     * 
     * @return The list of bots
     */
    private List<BotDescription> getBotsDescription( )
    {
        List<BotDescription> list = new ArrayList<BotDescription>( );

        for ( ChatBot bot : getBots( ) )
        {
            List<String> listLanguages = bot.getAvailableLanguages( );

            if ( listLanguages != null )
            {
                for ( String strLanguage : listLanguages )
                {
                    BotDescription botDescription = new BotDescription( );
                    Locale locale = new Locale( strLanguage );
                    botDescription.setName( bot.getName( locale ) );
                    botDescription.setDescription( bot.getDescription( locale ) );
                    botDescription.setLanguage( locale.getDisplayLanguage( ) );
                    botDescription.setAvatarUrl( bot.getAvatarUrl( ) );

                    UrlItem url = new UrlItem( URL_BOT );
                    url.addParameter( PARAMETER_BOT, bot.getKey( ) );
                    url.addParameter( PARAMETER_LANGUAGE, strLanguage );
                    botDescription.setUrl( url.getUrl( ) );
                    list.add( botDescription );
                }
            }
            else
            {
                BotDescription botDescription = new BotDescription( );
                Locale locale = LocaleService.getDefault( );
                botDescription.setName( bot.getName( locale ) );
                botDescription.setDescription( bot.getDescription( locale ) );
                botDescription.setLanguage( locale.getDisplayLanguage( ) );
                list.add( botDescription );
            }
        }

        return list;
    }

    /**
     * Generate a Conversation ID
     * 
     * @return The ID
     */
    private String getNewConversationId( )
    {
        return UUID.randomUUID( ).toString( );
    }

    private boolean getStandalone( HttpServletRequest request )
    {
        String strStandalone = request.getParameter( PARAMETER_STANDALONE );
        if( strStandalone != null &&  (strStandalone.equalsIgnoreCase( "true" ) || strStandalone.equalsIgnoreCase( "on" )) )
        {
            return true;
        }
        strStandalone = AppPropertiesService.getProperty(  PROPERTY_STANDALONE );
        if( strStandalone != null && (strStandalone.equalsIgnoreCase( "true" ) || strStandalone.equalsIgnoreCase( "on" )) )
        {
            return true;
        }
        return false;
    }
}
