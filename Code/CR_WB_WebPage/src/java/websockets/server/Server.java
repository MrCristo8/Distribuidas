/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websockets.server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import websockets.msjObjects.*;

/**
 *
 * @author wason
 */
@ServerEndpoint(value = "/chat", encoders =
{
    MessageEncoder.class
}, decoders =
{
    MessageDecoder.class
})

public class Server
{

    static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session)
    {

        peers.add(session);
    }

    @OnMessage
    public void onMessage(Message message, Session session) throws IOException, EncodeException
    {
        System.out.println(message.getText());
        for (Session peer : peers)
        {
            peer.getBasicRemote().sendObject(message);
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException
    {
        peers.remove(session);
    }

}
