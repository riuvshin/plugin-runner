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
package com.codenvy.ide.ext.runner.client.runneractions.impl.launch;

import com.codenvy.ide.api.app.AppContext;
import com.codenvy.ide.api.app.CurrentProject;
import com.codenvy.ide.api.notification.Notification;
import com.codenvy.ide.api.notification.NotificationManager;
import com.codenvy.ide.ext.runner.client.RunnerLocalizationConstant;
import com.codenvy.ide.ext.runner.client.tabs.console.container.ConsoleContainer;
import com.codenvy.ide.ext.runner.client.inject.factories.RunnerActionFactory;
import com.codenvy.ide.ext.runner.client.models.Runner;
import com.codenvy.ide.ext.runner.client.runneractions.AbstractRunnerAction;
import com.codenvy.ide.ext.runner.client.runneractions.RunnerAction;
import com.google.inject.Inject;

import javax.annotation.Nonnull;

import static com.codenvy.ide.api.notification.Notification.Status.PROGRESS;

/**
 * @author Andrey Plotnikov
 * @author Valeriy Svydenko
 */
public class LaunchAction extends AbstractRunnerAction {

    private final NotificationManager        notificationManager;
    private final RunnerLocalizationConstant locale;
    private final AppContext                 appContext;
    private final RunnerActionFactory        runnerActionFactory;
    private final RunnerAction               outputAction;
    private final ConsoleContainer           consoleContainer;

    @Inject
    public LaunchAction(NotificationManager notificationManager,
                        RunnerLocalizationConstant locale,
                        AppContext appContext,
                        ConsoleContainer consoleContainer,
                        RunnerActionFactory runnerActionFactory) {
        this.notificationManager = notificationManager;
        this.locale = locale;
        this.appContext = appContext;
        this.runnerActionFactory = runnerActionFactory;
        this.consoleContainer = consoleContainer;

        outputAction = runnerActionFactory.createOutput();
        addAction(outputAction);
    }

    /** {@inheritDoc} */
    @Override
    public void perform(@Nonnull Runner runner) {
        CurrentProject project = appContext.getCurrentProject();
        if (project == null) {
            return;
        }

        project.setIsRunningEnabled(false);

        String projectName = project.getProjectDescription().getName();
        String message = locale.environmentCooking(projectName);

        Notification notification = new Notification(message, PROGRESS, true);
        notificationManager.showNotification(notification);

        consoleContainer.printInfo(runner, message);

        RunnerAction statusAction = runnerActionFactory.createStatus(notification);
        addAction(statusAction);

        statusAction.perform(runner);
        outputAction.perform(runner);
    }
}