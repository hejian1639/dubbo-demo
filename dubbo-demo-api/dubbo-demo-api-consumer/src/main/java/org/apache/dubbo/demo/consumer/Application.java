/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.dubbo.demo.consumer;

import com.riil.baymax.api.flowsearch.IFlowSearchService;
import com.riil.baymax.api.flowsearch.bean.FlowQueryParam;
import com.riil.baymax.api.flowsearch.bean.PageNpvHttp;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.demo.DemoService;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Application {
    /**
     * In order to make sure multicast registry works, need to specify '-Djava.net.preferIPv4Stack=true' before
     * launch the application
     */
    public static void main(String[] args) {
        ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("dubbo-demo-api-consumer"));
        reference.setUrl("dubbo://127.0.0.1:20880");
        RegistryConfig registry = new RegistryConfig("zookeeper://172.17.162.173:2181");
        registry.setTimeout(300000);
//        reference.setRegistry(registry);
        reference.setInterface(DemoService.class);
        ConsumerConfig consumer = new ConsumerConfig();
        consumer.setTimeout(300000);
        reference.setConsumer(consumer);

        DemoService service = reference.get();
        String message = null;
        message = service.sayHello("dubbo");
        System.out.println(message);
    }
}
