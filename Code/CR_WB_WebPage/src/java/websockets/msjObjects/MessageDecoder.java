/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websockets.msjObjects;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author wason
 */
public class MessageDecoder implements Decoder.TextStream<Message>
{

    @Override
    public Message decode(Reader reader) throws DecodeException, IOException
    {
        Message msg = new Message();
        try (JsonReader jreader = Json.createReader(reader))
        {
            JsonObject json = jreader.readObject();
            msg.setName(json.getString("name"));
            msg.setText(json.getString("text"));
            msg.setDate(json.getString("date"));

        } catch (Exception e)
        {

        }
        return msg;
    }

    @Override
    public void init(EndpointConfig config)
    {

    }

    @Override
    public void destroy()
    {

    }

}
