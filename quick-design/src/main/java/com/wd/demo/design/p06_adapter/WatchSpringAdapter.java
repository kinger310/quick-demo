package com.wd.demo.design.p06_adapter;

/**
 * @Author: wangd
 * @Date: 2023/4/28 10:35
 */
public class WatchSpringAdapter {


    // 适配器在SpringMVC框架应用

    //   DispatcherServlet#doDispatch
    //   // Determine handler adapter for the current request.
    //   HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());
    //
    //   DispatcherService#getHandlerAdapter
    // 			for (HandlerAdapter adapter : this.handlerAdapters) {
    // 				if (adapter.supports(handler)) {
    // 					return adapter;
    // 				}
    // 			}
    //
    //				// Actually invoke the handler.
    // 				mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
    //

}
