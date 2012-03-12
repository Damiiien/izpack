/*
 * IzPack - Copyright 2001-2008 Julien Ponge, All Rights Reserved.
 *
 * http://izpack.org/
 * http://izpack.codehaus.org/
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

package com.izforge.izpack.panels.htmlhello;

import com.izforge.izpack.api.data.ResourceManager;
import com.izforge.izpack.gui.log.Log;
import com.izforge.izpack.installer.base.InstallerFrame;
import com.izforge.izpack.installer.data.GUIInstallData;
import com.izforge.izpack.panels.htmlinfo.HTMLInfoPanel;

/**
 * Class HTMLHelloPanel is a version of the hello panel that displays
 * HTML content.
 */
public class HTMLHelloPanel extends HTMLInfoPanel
{
    private static final long serialVersionUID = -7671648991830935148L;

    /**
     * Constructs an <tt>HTMLHelloPanel</tt>.
     *
     * @param parent              the parent window
     * @param installData         the installation data
     * @param resourceManager     the resource manager
     * @param log                 the log
     */
    public HTMLHelloPanel(InstallerFrame parent, GUIInstallData installData, ResourceManager resourceManager, Log log)
    {
        super(parent, installData, "HTMLHelloPanel", false, resourceManager, log);
    }

    /**
     * Indicates wether the panel has been validated or not.
     *
     * @return Always true.
     */
    public boolean isValidated()
    {
        return true;
    }
}
