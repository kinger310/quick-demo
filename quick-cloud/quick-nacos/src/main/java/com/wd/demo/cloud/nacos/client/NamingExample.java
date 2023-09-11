package com.wd.demo.cloud.nacos.client;

/* Refer to document: https://github.com/alibaba/nacos/blob/master/example/src/main/java/com/alibaba/nacos/example
*  pom.xml
    <dependency>
        <groupId>com.alibaba.nacos</groupId>
        <artifactId>nacos-client</artifactId>
        <version>${latest.version}</version>
    </dependency>
*/

import java.util.Properties;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;

/**
 * @author nkorange
 */
public class NamingExample {

    public static void main(String[] args) throws NacosException {

        Properties properties = new Properties();
        // properties.setProperty("serverAddr", System.getProperty("serverAddr"));
        // properties.setProperty("namespace", System.getProperty("namespace"));
        properties.setProperty("serverAddr", "hadoop100:8848");
        properties.setProperty("namespace", "public");


        NamingService naming = NamingFactory.createNamingService(properties);

        naming.registerInstance("nacos-payment-provider", "11.11.11.11", 8888, "TEST1");

        naming.registerInstance("nacos-payment-provider", "2.2.2.2", 9999, "DEFAULT");

        System.out.println(naming.getAllInstances("nacos-payment-provider"));

        naming.deregisterInstance("nacos-payment-provider", "2.2.2.2", 9999, "DEFAULT");

        System.out.println(naming.getAllInstances("nacos-payment-provider"));

        naming.subscribe("nacos-payment-provider", new EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println(((NamingEvent)event).getServiceName());
                System.out.println(((NamingEvent)event).getInstances());
            }
        });
    }
}