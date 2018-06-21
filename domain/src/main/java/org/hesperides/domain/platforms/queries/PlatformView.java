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
package org.hesperides.domain.platforms.queries;

import lombok.Value;

import java.util.List;

@Value
public class PlatformView {

    String platformName;
    String applicationName;
    String version;
    boolean isProductionPlatform;
    List<ModuleView> modules;
    Long versionId;

    @Value
    public static class ModuleView {

        Long id;
        String name;
        String version;
        boolean isWorkingCopy;
        String propertiesPath;
        String path;
        List<InstanceView> instances;

        @Value
        public static class InstanceView {

            String name;
            List<KeyValueView> keyValues; //Pas sur

            @Value
            public static class KeyValueView {
                String name;
                String value;
            }
        }
    }
}