/*
 *
 * This file is part of the Hesperides distribution.
 * (https://github.com/voyages-sncf-technologies/hesperides)
 * Copyright (c) 2016 VSCT.
 *
 * Hesperides is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, version 3.
 *
 * Hesperides is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */
package org.hesperides.infrastructure.mongo.platforms.documents;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hesperides.domain.platforms.entities.Instance;
import org.hesperides.domain.platforms.queries.views.InstanceView;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Document
@NoArgsConstructor
public class InstanceDocument {

    private String name;
    List<ValuedPropertyDocument> valuedProperties;

    public InstanceDocument(Instance instance) {
        this.name = instance.getName();
        this.valuedProperties = ValuedPropertyDocument.fromDomainInstances(instance.getValuedProperties());
    }

    public InstanceView toInstanceView() {
        return new InstanceView(
                name,
                ValuedPropertyDocument.toValuedPropertyViews(valuedProperties)
        );
    }

    public static List<InstanceDocument> fromDomainInstances(List<Instance> instances) {
        List<InstanceDocument> instanceDocuments = null;
        if (instances != null) {
            instanceDocuments = instances.stream().map(InstanceDocument::new).collect(Collectors.toList());
        }
        return instanceDocuments;
    }

    public static List<InstanceView> toInstanceViews(List<InstanceDocument> instances) {
        List<InstanceView> instanceViews = null;
        if (instances != null) {
            instanceViews = instances.stream().map(InstanceDocument::toInstanceView).collect(Collectors.toList());
        }
        return instanceViews;
    }
}
