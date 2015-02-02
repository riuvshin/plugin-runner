/*******************************************************************************
 * Copyright (c) 2012-2015 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.codenvy.ide.ext.runner.client.properties.common;

import javax.annotation.Nonnull;

/**
 * The enum represents a list of available states of shutdowning process of a runner.
 *
 * @author Andrey Plotnikov
 */
public enum Shutdown {
    BY_TIMEOUT("By Timeout"), ALWAYS_ON("Always On");

    private final String name;

    Shutdown(@Nonnull String name) {
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return name;
    }
}