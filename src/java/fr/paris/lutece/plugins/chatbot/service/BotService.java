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
import fr.paris.lutece.portal.service.spring.SpringContextService;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * BotService
 */
public final class BotService
{
    private static Map<String, ChatBot> _mapBots = new HashMap<>( );

    private static boolean _bInitFromContextFiles;

    /** Private constructor */
    private BotService( )
    {
    }

    /**
     * Get all bots
     * 
     * @return The bots list
     */
    public static List<ChatBot> getBots( )
    {
        if ( !_bInitFromContextFiles )
        {
            initFromContextFiles( );
            _bInitFromContextFiles = true;
        }
        return new ArrayList<ChatBot>( _mapBots.values( ) );
    }

    /**
     * Register a bot from an other module
     * 
     * @param chatbot
     *            The chatbot to register
     */
    public static void register( ChatBot chatbot )
    {
        _mapBots.put( chatbot.getKey( ), chatbot );
    }

    /**
     * Unregister a bot
     * 
     * @param strChatBotKey
     *            The chatbot key
     */
    public static void unregister( String strChatBotKey )
    {
        _mapBots.remove( strChatBotKey );
    }

    /**
     * Get a bot by its key
     * 
     * @param strBotKey
     *            The bot key
     * @return The bot
     */
    public static ChatBot getBot( String strBotKey )
    {
        if ( !_bInitFromContextFiles )
        {
            initFromContextFiles( );
            _bInitFromContextFiles = true;
        }
        return _mapBots.get( strBotKey );
    }

    /**
     * Init chatbots list from chatbot defined in context files
     */
    private static void initFromContextFiles( )
    {
        for ( ChatBot chatbot : SpringContextService.getBeansOfType( ChatBot.class ) )
        {
            register( chatbot );
        }
    }
}
