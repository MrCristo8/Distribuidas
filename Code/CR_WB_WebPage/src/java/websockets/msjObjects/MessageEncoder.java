/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websockets.msjObjects;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author wason
 */
public class MessageEncoder implements Encoder.TextStream<Message>
{

    @Override
    public void encode(Message object, Writer writer) throws EncodeException, IOException
    {
        JsonObject json = Json.createObjectBuilder()
                .add("name", object.getName())
                .add("text", object.getText())
                .add("date", object.getDate())
                .build();
        try (JsonWriter jsonwriter = Json.createWriter(writer))
        {
            jsonwriter.writeObject(json);
        } catch (Exception e)
        {
        }
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
