package de.bitbrain.yolo.net;

import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import de.bitbrain.yolo.core.GameState;

import java.io.IOException;

/**
 * @author ksidpen
 */
public class YoloServer extends Listener implements Disposable{

    private final Server server;
    private final GameState game;


    public YoloServer() throws IOException {
        this.game = new GameState();

        server = new Server();
        KryoConfig.config(server.getKryo());
        server.addListener(this);
        server.start();
        server.bind(KryoConfig.TCPPort, KryoConfig.UDPPort);
    }


    @Override
    public void received(Connection connection, Object object){
        if (object instanceof Events.Move) {
            Events.Move response = (Events.Move)object;
            System.out.println(response);
        }else
            if(object instanceof Events.Join){
                System.out.println("#YOLOOOOOOO");
            }
    }

    @Override
    public void dispose() {
        server.removeListener(this);
        server.close();
    }
}
