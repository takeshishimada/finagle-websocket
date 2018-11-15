package com.twitter.finagle.websocket

import io.netty.channel._
import io.netty.handler.codec.http.websocketx._

private[finagle] class WebSocketClientHandler
  extends ChannelInboundHandlerAdapter {

  override def channelRead(ctx: ChannelHandlerContext, msg: AnyRef): Unit = {
    msg match {
      case handShaker: WebSocketClientHandshaker =>
        val websocketPipeline = new WebSocketClientProtocolHandler(handShaker)
        ctx.pipeline().replace(classOf[WebSocketClientHandler], "sockets", websocketPipeline)
      case _ =>
        super.channelRead(ctx, msg)
    }
  }
}