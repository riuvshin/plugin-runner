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
package com.codenvy.ide.ext.runner.client.tabs.properties.panel.common;

import com.codenvy.api.project.gwt.client.ProjectServiceClient;
import com.codenvy.api.project.shared.dto.ItemReference;
import com.codenvy.ide.api.projecttree.TreeNode;
import com.codenvy.ide.api.projecttree.TreeStructure;
import com.codenvy.ide.rest.DtoUnmarshallerFactory;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.web.bindery.event.shared.EventBus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * @author Andrienko Alexander
 */
@RunWith(GwtMockitoTestRunner.class)
public class EnvironmentScriptTest {

    private static final String TEXT = "some text";
    private static final String ENVIRONMENT_NAME = "some environment name";

    @Mock
    private TreeNode<?>   parent;
    @Mock
    private ItemReference data;
    @Mock
    private TreeStructure treeStructure;
    @Mock
    private EventBus               eventBus;
    @Mock
    private ProjectServiceClient   projectServiceClient;
    @Mock
    private DtoUnmarshallerFactory dtoUnmarshallerFactory;

    private EnvironmentScript environmentScript;

    @Before
    public void setUp() {
        environmentScript = new EnvironmentScript(parent,
                                                  data,
                                                  treeStructure,
                                                  eventBus,
                                                  projectServiceClient,
                                                  dtoUnmarshallerFactory,
                                                  ENVIRONMENT_NAME);
        when(data.getName()).thenReturn(TEXT);
    }

    @Test
    public void displayNameShouldBeReturned() {
        String expectedDisplayName = '[' + ENVIRONMENT_NAME + "] " + TEXT;

        assertThat(environmentScript.getDisplayName(), is(expectedDisplayName));
    }
}