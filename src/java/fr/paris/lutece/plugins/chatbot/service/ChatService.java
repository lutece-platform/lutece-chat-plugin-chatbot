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

package fr.paris.lutece.plugins.chatbot.service;

import fr.paris.lutece.plugins.chatbot.business.BotPost;
import fr.paris.lutece.plugins.chatbot.service.bot.ChatBot;
import fr.paris.lutece.plugins.chatbot.business.ChatData;
import fr.paris.lutece.plugins.chatbot.business.Post;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * ChatService
 */
public final class ChatService
{
    private static final String RESET = "reset";

    private static Map<String, ChatData> _mapConversations = new HashMap<>( );
    private static PostRenderer _renderer = SpringContextService.getBean( "chatbot.postRenderer" );

    /**
     * Private constructor
     */
    private ChatService( )
    {
    }

    /**
     * Process message
     * 
     * @param request
     *            The request
     * @param strConversationId
     *            The conversation ID
     * @param strMessage
     *            The message
     * @param strBotKey
     *            The bot Key
     * @param locale
     *            The locale
     */
    public static void processMessage( HttpServletRequest request, String strConversationId, String strMessage, String strBotKey, Locale locale )
    {
        ChatBot bot = BotService.getBot( strBotKey );
        String strUserMessage = extractUserMessage( strMessage );   // Message displayed in the conversation (part before '|')
        String strUserCodifiedMessage= extractUserCodifiedMessage( strMessage);  // Message send to the bot as user message (part after '|')

        boolean bResetConversation = RESET.equalsIgnoreCase( strUserCodifiedMessage );
        if ( bResetConversation )
        {
            _mapConversations.remove( strConversationId );
            bot.reset( strConversationId );
        }
        ChatData data = _mapConversations.get( strConversationId );
        if ( data == null )
        {
            data = new ChatData( bot.getWelcomeMessage() );
            _mapConversations.put( strConversationId, data );
        }
        if ( !bResetConversation )
        {
            data.addUserPost( strUserMessage );
            List<BotPost> listMessages = bot.processUserMessage( strUserCodifiedMessage, strConversationId, locale );
            for ( BotPost post : listMessages )
            {
                data.addBotPost( post );
            }
        }
    }

    /**
     * Get conversation
     * 
     * @param strConversationId
     *            Conversation Id
     * @param bot The bot
     * 
     * @return The list of post
     */
    public static List<Post> getConversation( String strConversationId, ChatBot bot )
    {
        ChatData data = _mapConversations.get( strConversationId );
        if ( data == null )
        {
            data = new ChatData( bot.getWelcomeMessage() );
        }
        return _renderer.render( data.getPosts( ) );
    }

    /**
     * Extract user message : all text before the pipe character
     * 
     * @param strMessage
     *            The input message
     * @return The output message
     */
    private static String extractUserMessage( String strMessage )
    {
        int nPos = strMessage.indexOf( '|' );
        if ( nPos > 0 )
        {
            return strMessage.substring( 0, nPos );
        }
        return strMessage;
    }

    
    /**
     * Extract user message : all text before the pipe character
     * 
     * @param strMessage
     *            The input message
     * @return The output message
     */
    private static String extractUserCodifiedMessage( String strMessage )
    {
        int nPos = strMessage.indexOf( '|' );
        if ( ( nPos > 0 ) && ( nPos < strMessage.length() ))
        {
            return strMessage.substring( nPos + 1 );
        }
        return strMessage;
    }
    
}
