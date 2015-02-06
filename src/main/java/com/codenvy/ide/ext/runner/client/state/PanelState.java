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
package com.codenvy.ide.ext.runner.client.state;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Plotnikov
 */
@Singleton
public class PanelState {

    private final List<StateChangeListener> listeners;
    private       State                     state;

    @Inject
    public PanelState() {
        listeners = new ArrayList<>();
        state = State.HISTORY;
    }

    @Nonnull
    public State getState() {
        return state;
    }

    public void setState(@Nonnull State state) {
        this.state = state;
        notifyListeners();
    }

    public void addListener(@Nonnull StateChangeListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners() {
        for (StateChangeListener listener : listeners) {
            listener.onStateChanged();
        }
    }

    public interface StateChangeListener {
        void onStateChanged();
    }

}