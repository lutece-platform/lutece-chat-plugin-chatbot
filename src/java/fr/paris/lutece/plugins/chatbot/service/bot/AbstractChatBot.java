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

import fr.paris.lutece.portal.service.i18n.I18nService;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * Bot
 */
public abstract class AbstractChatBot implements ChatBot, Serializable
{
    private static final String URL_DEFAULT_BOT_AVATAR = "images/skin/plugins/chatbot/bot-avatar.png";
    private static final long serialVersionUID = 1L;
    private String _strKey;
    private String _strName;
    private String _strDescription;
    private String _strNameI18nKey;
    private String _strDescriptionI18nKey;
    private String _strBotAvatarUrl;
    private List<String> _listAvailableLanguages;
    private boolean _bStandalone;
    private String _strWelcomeMessage;

    /**
     * Returns the Key
     * 
     * @return The Key
     */
    @Override
    public String getKey( )
    {
        return _strKey;
    }

    /**
     * Sets the Key
     * 
     * @param strKey
     *            The Key
     */
    public void setKey( String strKey )
    {
        _strKey = strKey;
    }

    /**
     * Returns the Name
     * 
     * @param locale
     *            The locale
     * @return The Name
     */
    @Override
    public String getName( Locale locale )
    {
        String strName;

        if ( _strNameI18nKey != null )
        {
            strName = I18nService.getLocalizedString( _strNameI18nKey, locale );
        }
        else
        {
            strName = _strName;
        }

        return strName;
    }

    /**
     * Sets the Name
     * 
     * @param strName
     *            The Name
     */
    public void setName( String strName )
    {
        _strName = strName;
    }

    /**
     * Returns the Description
     * 
     * @param locale
     *            The locale
     * @return The Description
     */
    @Override
    public String getDescription( Locale locale )
    {
        String strDescription;

        if ( _strDescriptionI18nKey != null )
        {
            strDescription = I18nService.getLocalizedString( _strDescriptionI18nKey, locale );
        }
        else
        {
            strDescription = _strDescription;
        }

        return strDescription;
    }

    /**
     * Sets the Description
     * 
     * @param strDescription
     *            The Description
     */
    public void setDescription( String strDescription )
    {
        _strDescription = strDescription;
    }

    /**
     * Returns the NameI18nKey
     * 
     * @return The NameI18nKey
     */
    public String getNameI18nKey( )
    {
        return _strNameI18nKey;
    }

    /**
     * Sets the NameI18nKey
     * 
     * @param strNameI18nKey
     *            The NameI18nKey
     */
    public void setNameI18nKey( String strNameI18nKey )
    {
        _strNameI18nKey = strNameI18nKey;
    }

    /**
     * Returns the DescriptionI18nKey
     * 
     * @return The DescriptionI18nKey
     */
    public String getDescriptionI18nKey( )
    {
        return _strDescriptionI18nKey;
    }

    /**
     * Sets the DescriptionI18nKey
     * 
     * @param strDescriptionI18nKey
     *            The DescriptionI18nKey
     */
    public void setDescriptionI18nKey( String strDescriptionI18nKey )
    {
        _strDescriptionI18nKey = strDescriptionI18nKey;
    }

    /**
     * Returns the BotAvatarUrl
     * 
     * @return The BotAvatarUrl
     */
    @Override
    public String getAvatarUrl( )
    {
        if ( _strBotAvatarUrl == null || _strBotAvatarUrl.equals( "" ) )
        {
            _strBotAvatarUrl = URL_DEFAULT_BOT_AVATAR;
        }

        return _strBotAvatarUrl;
    }

    /**
     * Sets the BotAvatarUrl
     * 
     * @param strBotAvatarUrl
     *            The BotAvatarUrl
     */
    public void setAvatarUrl( String strBotAvatarUrl )
    {
        _strBotAvatarUrl = strBotAvatarUrl;
    }

    /**
     * Set available languages list
     * 
     * @param listAvailableLanguages
     *            available languages list
     */
    public void setListAvailableLanguages( List<String> listAvailableLanguages )
    {
        _listAvailableLanguages = listAvailableLanguages;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<String> getAvailableLanguages( )
    {
        return _listAvailableLanguages;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isStandalone( )
    {
        return _bStandalone;
    }

    /**
     * Sets the Standalone
     *
     * @param bStandalone
     *            The Standalone
     */
    public void setStandalone( boolean bStandalone )
    {
        _bStandalone = bStandalone;
    }

    /**
     * Returns the WelcomeMessage
     * 
     * @return The WelcomeMessage
     */
    @Override
    public String getWelcomeMessage( )
    {
        return _strWelcomeMessage;
    }

    /**
     * Sets the WelcomeMessage
     * 
     * @param strWelcomeMessage
     *            The WelcomeMessage
     */
    public void setWelcomeMessage( String strWelcomeMessage )
    {
        _strWelcomeMessage = strWelcomeMessage;
    }


    /**
     * {@inheritDoc }
     */
    @Override
    public void reset( String strConversationId )
    {
        
    }
}
