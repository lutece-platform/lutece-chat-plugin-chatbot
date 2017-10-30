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

import fr.paris.lutece.plugins.chatbot.business.Post;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author levy
 */
public class DefaultPostRendererTest
{
    private static final String POST_CONTENT_URL  = "Welcome to http://lutece.paris.fr";
    private static final String POST_RENDERED_CONTENT_URL  = "Welcome to <a href=\"http://lutece.paris.fr\">http://lutece.paris.fr</a>";
    
    /**
     * Test of render method, of class DefaultPostRenderer.
     */
    @Test
    public void testRender()
    {
        
        System.out.println( "render" );
        List<Post> listPosts = new ArrayList<>();
        Post post = new Post();
        post.setAuthor( Post.AUTHOR_BOT );
        post.setContent( POST_CONTENT_URL );
        listPosts.add( post );
        DefaultPostRenderer instance = new DefaultPostRenderer();
        List<Post> result = instance.render( listPosts );
        System.out.println( "Rendered URL : " +  result.get( 0 ).getContent() );
        assertEquals( result.get( 0 ).getContent() , POST_RENDERED_CONTENT_URL );
    }
    
}
