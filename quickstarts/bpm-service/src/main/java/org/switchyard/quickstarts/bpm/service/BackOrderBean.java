/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.switchyard.quickstarts.bpm.service;

import org.switchyard.component.bean.Service;
import org.switchyard.quickstarts.bpm.service.data.Order;
import org.switchyard.quickstarts.bpm.service.data.OrderAck;

@Service(BackOrder.class)
public class BackOrderBean implements org.switchyard.quickstarts.bpm.service.BackOrder {

    public static final String HOLD_STATUS =
        "Insufficient quantity on hand - order has been placed on hold.";

    @Override
    public OrderAck hold(Order order) {
        OrderAck ack = new OrderAck();
        ack.setAccepted(false);
        ack.setOrderId(order.getOrderId());
        ack.setStatus(HOLD_STATUS);

        return ack;
    }

}
