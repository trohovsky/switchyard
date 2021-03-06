/*
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors.
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
package org.switchyard.rhq.plugin;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rhq.core.domain.configuration.Configuration;
import org.rhq.core.pluginapi.inventory.DiscoveredResourceDetails;
import org.rhq.core.pluginapi.inventory.ResourceDiscoveryComponent;
import org.rhq.core.pluginapi.inventory.ResourceDiscoveryContext;
import org.switchyard.rhq.plugin.model.Service;

/**
 * SwitchYard Service Discovery Component
 */
public class ServiceDiscoveryComponent implements ResourceDiscoveryComponent<ApplicationResourceComponent> {
    /**
     * The logger instance.
     */
    private static Log LOG = LogFactory.getLog(ServiceDiscoveryComponent.class);

    /**
     * Discover existing resources.
     * 
     * @param context
     *            The context for the current discovery component.
     * @return The discovered resources.
     */
    @Override
    public Set<DiscoveredResourceDetails> discoverResources(final ResourceDiscoveryContext<ApplicationResourceComponent> context) {

        final HashSet<DiscoveredResourceDetails> discoveredResources = new HashSet<DiscoveredResourceDetails>();
        
        final ApplicationResourceComponent parent = context.getParentResourceComponent();
        
        final Map<String, Service> services = parent.getServices();

        for (Service service : services.values()) {
            final Configuration pluginConfig = context.getDefaultPluginConfiguration();
            final QName name = service.getName();

            final DiscoveredResourceDetails resource = new DiscoveredResourceDetails(
                    context.getResourceType(), name.toString(),
                    name.getLocalPart(),
                    name.getNamespaceURI(), null,
                    pluginConfig, null);

            discoveredResources.add(resource);
            
            LOG.debug("Discovered SwitchYard Service " + service);
        }

        return discoveredResources;
    }
}
