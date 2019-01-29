/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageBeans;

import java.rmi.RemoteException;
import java.util.Objects;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import model.WB_CR_MESSAGES;

/**
 *
 * @author wason
 */
@MessageDriven(activationConfig =
{
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "destiny_jms")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class DatabaseMessageBean implements MessageListener
{

    public DatabaseMessageBean()
    {
    }

    @Override
    public void onMessage(Message message)
    {
        try
        {
            MapMessage msg = (MapMessage) message;
            Integer id = 1;
            for (WB_CR_MESSAGES item : persistance.MessagePersistance.getInstance().getObjectList())
            {
                if (!Objects.equals(item.getMessage_id(), id))
                {
                    break;
                }
                id++;
            }
            persistance.MessagePersistance.getInstance().getObjectList().add(
                    new WB_CR_MESSAGES(
                            id,
                            msg.getString("message_from"),
                            msg.getString("message_body"),
                            "CREATED")
            );
            persistance.MessagePersistance.getInstance().updateOnDatabase();
        } catch (JMSException | RemoteException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
