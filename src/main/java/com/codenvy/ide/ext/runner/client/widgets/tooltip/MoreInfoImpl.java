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
package com.codenvy.ide.ext.runner.client.widgets.tooltip;

import com.codenvy.ide.ext.runner.client.RunnerLocalizationConstant;
import com.codenvy.ide.ext.runner.client.RunnerResources;
import com.codenvy.ide.ext.runner.client.models.Runner;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.annotation.Nonnull;
import java.util.Date;

/**
 * Class provides view representation of panel which contains additional information about runner.
 *
 * @author Dmitry Shnurenko
 */
public class MoreInfoImpl extends Composite implements MoreInfo {

    @Singleton
    interface MoreInfoPopupImplUiBinder extends UiBinder<Widget, MoreInfoImpl> {
    }

    @UiField
    Label started;
    @UiField
    Label finished;
    @UiField
    Label timeout;
    @UiField
    Label activeTime;
    @UiField
    Label ram;

    @UiField(provided = true)
    final RunnerResources            resources;
    @UiField(provided = true)
    final RunnerLocalizationConstant locale;

    private final DateTimeFormat startDateFormatter;

    @Inject
    public MoreInfoImpl(MoreInfoPopupImplUiBinder uiBinder, RunnerResources resources, RunnerLocalizationConstant locale) {
        this.resources = resources;
        this.locale = locale;

        initWidget(uiBinder.createAndBindUi(this));

        this.startDateFormatter = DateTimeFormat.getFormat("dd-MM-yy HH:mm");
    }

    /** {@inheritDoc} */
    @Override
    public void update(@Nonnull Runner runner) {
        started.setText(startDateFormatter.format(new Date(runner.getCreationTime())));

        //TODO need understand what time value to set
        finished.setText("--/--/--");
        timeout.setText("08m 25s");
        activeTime.setText("01m 35s");
        //TODO need set memory size from runner options
        ram.setText("256MB");
    }
}