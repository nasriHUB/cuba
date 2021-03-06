/*
 * Copyright (c) 2008-2017 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.cuba.web.gui.components.renderers;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.DataGrid;
import com.haulmont.cuba.web.widgets.renderers.CubaImageRenderer;
import com.vaadin.ui.renderers.Renderer;

import java.util.function.Consumer;

/**
 * A renderer for presenting images. The value of the corresponding property
 * is used as the image location. Location can be a theme resource or URL.
 */
public class WebImageRenderer<T extends Entity>
        extends WebAbstractClickableRenderer<T, String>
        implements DataGrid.ImageRenderer<T> {

    public WebImageRenderer() {
    }

    public WebImageRenderer(Consumer<DataGrid.RendererClickEvent<T>> listener) {
        super(listener);
    }

    @Override
    protected Renderer<String> createImplementation() {
        if (listener != null) {
            return new CubaImageRenderer<>(createClickListenerWrapper(listener));
        } else {
            return new CubaImageRenderer<>();
        }
    }

    @Override
    protected void copy(DataGrid.Renderer existingRenderer) {
        if (existingRenderer instanceof WebImageRenderer) {
            setRendererClickListener(((WebImageRenderer) existingRenderer).listener);
            setNullRepresentation(((WebImageRenderer) existingRenderer).getNullRepresentation());
        }
    }
}
