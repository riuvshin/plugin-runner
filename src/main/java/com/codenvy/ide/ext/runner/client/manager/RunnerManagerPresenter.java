/*******************************************************************************
 * Copyright (c) 2012-2014 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.codenvy.ide.ext.runner.client.manager;

import com.codenvy.api.runner.dto.RunOptions;
import com.codenvy.ide.api.parts.AbstractPartPresenter;
import com.codenvy.ide.ext.runner.client.models.Runner;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * The class provides much business logic:
 * 1. Provides possibility to launch/start a new runner. It means execute request on the server (communication with server part) and change
 * UI part.
 * 2. Manage runners (stop runner, get different information about runner and etc).
 *
 * @author Andrey Plotnikov
 * @author Dmitry Shnurenko
 */
@Singleton
public class RunnerManagerPresenter extends AbstractPartPresenter implements RunnerManager, RunnerManagerView.ActionDelegate {

    private final RunnerManagerView view;

    private Runner selectedRunner;

    @Inject
    public RunnerManagerPresenter(RunnerManagerView view) {
        this.view = view;
        this.view.setDelegate(this);
    }

    /** @return the GWT widget that is controlled by the presenter */
    public RunnerManagerView getView() {
        return view;
    }

    /** {@inheritDoc} */
    @Override
    public void onRunnerSelected(@Nonnull Runner runner) {
        this.selectedRunner = runner;
    }

    /** {@inheritDoc} */
    @Override
    public void onRunButtonClicked() {

    }

    /** {@inheritDoc} */
    @Override
    public void onStopButtonClicked() {

    }

    /** {@inheritDoc} */
    @Override
    public void onCleanConsoleButtonClicked() {

    }

    /** {@inheritDoc} */
    @Override
    public void onReceiptButtonClicked() {

    }

    /** {@inheritDoc} */
    @Override
    public void onConsoleButtonClicked() {
        view.activateConsole(selectedRunner);
    }

    /** {@inheritDoc} */
    @Override
    public void onTerminalButtonClicked() {
        view.activateTerminal(selectedRunner);
    }

    /** {@inheritDoc} */
    @Override
    public void launchRunner() {

    }

    /** {@inheritDoc} */
    @Override
    public void launchRunner(@Nonnull RunOptions runOptions) {

    }

    /** {@inheritDoc} */
    @Override
    public void go(@Nonnull AcceptsOneWidget container) {
        container.setWidget(view);
    }

    /** {@inheritDoc} */
    @Nonnull
    @Override
    public String getTitle() {
        return "Runner 2";
    }

    /** {@inheritDoc} */
    @Nullable
    @Override
    public ImageResource getTitleImage() {
        return null;
    }

    /** {@inheritDoc} */
    @Nullable
    @Override
    public String getTitleToolTip() {
        return null;
    }

}