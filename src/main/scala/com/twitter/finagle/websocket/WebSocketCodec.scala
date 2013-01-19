package com.twitter.finagle.websocket

import com.twitter.finagle.{Codec, CodecFactory}
import org.jboss.netty.channel.{ChannelPipelineFactory, Channels}
import org.jboss.netty.handler.codec.http._
import org.jboss.netty.handler.codec.http.websocketx._

class WebSocketCodec(path: String = "/") extends CodecFactory[WebSocket, WebSocket] {
  def server = Function.const {
    new Codec[WebSocket, WebSocket] {
      def pipelineFactory = new ChannelPipelineFactory {
        def getPipeline = {
          val pipeline = Channels.pipeline()
          pipeline.addLast("decoder", new HttpRequestDecoder)
          pipeline.addLast("encoder", new HttpResponseEncoder)
          pipeline.addLast("handler", new WebSocketHandler(path))
          pipeline
        }
      }
    }
  }

  def client = Function.const {
    new Codec[WebSocket, WebSocket] {
      def pipelineFactory = new ChannelPipelineFactory {
        def getPipeline = {
          val pipeline = Channels.pipeline()
          pipeline.addLast("decoder", new HttpResponseDecoder)
          pipeline.addLast("encoder", new HttpRequestEncoder)
          pipeline.addLast("handler", new WebSocketHandler(path))
          pipeline
        }
      }
    }
  }
}
