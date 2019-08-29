/*
 * Copyright (c) 2002-2019, Mairie de Paris
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
package fr.paris.lutece.plugins.chatbot.business;

import java.io.Serializable;

/**
 * Post
 */
public class Post implements Serializable
{
    public static final int AUTHOR_BOT = 0;
    public static final int AUTHOR_USER = 1;
    public static final String CONTENT_TYPE_TEXT = "text";
    public static final String CONTENT_TYPE_IMAGE = "image";
    public static final String CONTENT_TYPE_CARD = "card";
    public static final String CONTENT_TYPE_QUICK_REPLIES = "quick replies";
    private static final long serialVersionUID = 1L;

    // Variables declarations
    private String _strContent;
    private String _strContentType = CONTENT_TYPE_TEXT;
    private int _nAuthor;
    private String _strAvatarUrl;

    /**
     * Constructor
     */
    public Post( )
    {
    }

    /**
     * Constructor
     * 
     * @param strContent
     *            The content
     * 
     *            NB : Default content type is 'text'
     */
    public Post( String strContent )
    {
        _strContent = strContent;
    }

    /**
     * Constructor
     * 
     * @param strContent
     *            The content
     * @param nAuthor
     *            The author
     * 
     *            NB : Default content type is 'text'
     */
    public Post( String strContent, int nAuthor )
    {
        _strContent = strContent;
        _nAuthor = nAuthor;
    }

    /**
     * Constructor
     * 
     * @param strContent
     *            The content
     * @param strContentType
     * @param nAuthor
     */
    public Post( String strContent, String strContentType, int nAuthor )
    {
        _strContent = strContent;
        _strContentType = strContentType;
        _nAuthor = nAuthor;
    }

    /**
     * Returns the Content
     * 
     * @return The Content
     */
    public String getContent( )
    {
        return _strContent;
    }

    /**
     * Sets the Content
     * 
     * @param strContent
     *            The Content
     */
    public void setContent( String strContent )
    {
        _strContent = strContent;
    }

    /**
     * Returns the ContentType
     * 
     * @return The ContentType
     */
    public String getContentType( )
    {
        return _strContentType;
    }

    /**
     * Sets the ContentType
     * 
     * @param strContentType
     *            The ContentType
     */
    public void setContentType( String strContentType )
    {
        _strContentType = strContentType;
    }

    /**
     * Returns the Author
     * 
     * @return The Author
     */
    public int getAuthor( )
    {
        return _nAuthor;
    }

    /**
     * Sets the Author
     * 
     * @param nAuthor
     *            The Author
     */
    public void setAuthor( int nAuthor )
    {
        _nAuthor = nAuthor;
    }

    /**
     * Returns the Avatar Url
     * 
     * @return The Avatar Url
     */
    public String getAvatarUrl( )
    {
        return _strAvatarUrl;
    }

    /**
     * Sets the AvatarUrl
     * 
     * @param strAvatarUrl
     *            The Avatar Url
     */
    public void setAvatarUrl( String strAvatarUrl )
    {
        _strAvatarUrl = strAvatarUrl;
    }

}
