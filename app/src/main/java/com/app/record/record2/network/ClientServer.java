package com.app.record.record2.network;

import android.content.pm.ResolveInfo;
import android.util.Log;

import com.app.record.record2.network.Codes.Encode.gpstoByteEncoder;
import com.app.record.record2.network.Handler.gps.gpsInfoHandler;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Created by a4423 on 2017/10/15.
 */

public class ClientServer {
    private final String host = "118.178.193.32";
    private final int port = 8089;

    public void connect() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                     .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(
                                    new IdleStateHandler(3,4,5),
                                    new gpstoByteEncoder(),
                                    new ConnectedSuccessHandler(),
                                    new gpsInfoHandler());
                        }
                    });
            /*bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
                     .option(ChannelOption.TCP_NODELAY,true);*/
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.option(ChannelOption.SO_REUSEADDR,true);
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

}
