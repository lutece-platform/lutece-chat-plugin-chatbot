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

package fr.paris.lutece.plugins.chatbot.service.bot;

import java.util.List;
import java.util.Locale;

/**
 * ChatBot
 */
public interface ChatBot
{
    /**
     * Get the bot ID
     * 
     * @return The ID
     */
    String getKey( );

    /**
     * Gets the bot name
     * 
     * @param locale
     *            The locale
     * @return The name
     */
    String getName( Locale locale );

    /**
     * Gets the bot description
     * 
     * @param locale
     *            The locale
     * @return The description
     */
    String getDescription( Locale locale );

    /**
     * Process the user message
     * 
     * @param strMessage
     *            The user message
     * @param strConversationId
     *            The conversation ID
     * @param locale
     *            The locale
     * @return The list of bot responses
     */
    List<String> processUserMessage( String strMessage, String strConversationId, Locale locale );

    /**
     * Returns available languages
     * 
     * @return available languages
     */
    List<String> getAvailableLanguages( );

    /**
     * The image URL of the bot's avatar
     * 
     * @return The URL
     */
    String getAvatarUrl( );
    
    /**
     * Is standalone (mode for iframe)
     * @return true if standalone
     */
    boolean isStandalone();
   
}
