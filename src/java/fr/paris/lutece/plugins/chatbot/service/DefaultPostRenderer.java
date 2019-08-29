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

package fr.paris.lutece.plugins.chatbot.service;

import fr.paris.lutece.plugins.chatbot.business.Post;
import java.util.ArrayList;
import java.util.List;

/**
 * DefaultPostRenderer
 */
public class DefaultPostRenderer implements PostRenderer
{

    /**
     * {@inheritDoc }
     * 
     * @param listPosts
     * @return
     */
    @Override
    public List<Post> render( List<Post> listPosts )
    {
        List<Post> list = new ArrayList<>( );
        for ( Post post : listPosts )
        {
            list.add( renderPost( post ) );
        }
        return list;
    }

    /**
     * Render a post
     * 
     * @param post
     *            The post
     * @return The rendered Post
     * 
     */
    private Post renderPost( Post post )
    {
        Post rendered = new Post( );
        rendered.setAuthor( post.getAuthor( ) );
        rendered.setContentType( post.getContentType( ) );
        String strContent = post.getContent( );
        renderContent( rendered, strContent );
        rendered.setAvatarUrl( post.getAvatarUrl( ) );
        return rendered;
    }

    /**
     * Render content
     * 
     * @param postToRender
     *            The rendered post
     * @param strContent
     *            The content
     */
    private void renderContent( Post postToRender, String strContent )
    {

        if ( strContent != null )
        {
            String strRendered = strContent;

            strRendered = strRendered.replace( '\n', ' ' );
            strRendered = strRendered.replace( '\r', ' ' );
            postToRender.setContent( strRendered );
        }
    }

}
