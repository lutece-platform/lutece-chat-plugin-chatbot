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

import fr.paris.lutece.plugins.chatbot.service.bot.ChatBot;
import fr.paris.lutece.plugins.chatbot.business.ChatData;
import fr.paris.lutece.plugins.chatbot.business.Post;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * ChatService
 */
public class ChatService
{
    private static Map<String, ChatData> _mapConversations = new HashMap<>( );

    public static void processMessage( HttpServletRequest request, String strConversationId, String strMessage, String strBotKey, Locale locale )
    {
        ChatData data = _mapConversations.get( strConversationId );
        if ( data == null )
        {
            data = createNewConversation( strBotKey );
            _mapConversations.put( strConversationId, data );
        }
        data.addUserPost( strMessage );
        ChatBot bot = BotService.getBot( strBotKey );
        String strBotMessage = bot.processUserMessage( strMessage, strConversationId, locale );
        data.addBotPost( strBotMessage );

    }

    public static List<Post> getConversation( String strConversationId )
    {
        ChatData data = _mapConversations.get( strConversationId );
        if ( data == null )
        {
            data = new ChatData( strConversationId );
        }
        return data.getPosts( );
    }

    private static ChatData createNewConversation( String strBotKey )
    {
        ChatData data = new ChatData( strBotKey );
        return data;
    }
}
