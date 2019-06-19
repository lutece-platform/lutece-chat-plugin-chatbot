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
package fr.paris.lutece.plugins.chatbot.service.avatar;

import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.util.ReferenceList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AvatarRendererService
 */
public class AvatarRendererService
{

    private static Map<String, AvatarRenderer> _mapAvatarRenderer;

    /**
     * Returns the avatar renderer for a given key
     *
     * @param strKey Tha avatar renderer key
     * @return The renderer
     */
    public static AvatarRenderer getAvatarRenderer( String strKey )
    {
        return getAvatarRenderersMap().get( strKey );
    }

    /**
     * Return the list off all avatar renderers available
     *
     * @return The list of renderers
     */
    public static ReferenceList getAvatarRenderersList()
    {
        ReferenceList list = new ReferenceList();
        for( AvatarRenderer renderer : getAvatarRenderersMap().values() )
        {
            list.addItem( renderer.getKey(), renderer.getName() );
        }
        return list;
    }

    /**
     * Get the Renderers registry map
     * @return The map
     */
    private static Map<String, AvatarRenderer> getAvatarRenderersMap()
    {
        if( _mapAvatarRenderer == null )
        {
            _mapAvatarRenderer = new HashMap<>();
            List<AvatarRenderer> listRenderers = SpringContextService.getBeansOfType( AvatarRenderer.class );
            for( AvatarRenderer renderer : listRenderers )
            {
                _mapAvatarRenderer.put( renderer.getKey(), renderer );
            }
        }
        return _mapAvatarRenderer;
    }
}
