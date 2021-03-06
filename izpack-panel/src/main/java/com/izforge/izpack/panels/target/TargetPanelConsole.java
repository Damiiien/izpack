/*
 * IzPack - Copyright 2001-2008 Julien Ponge, All Rights Reserved.
 *
 * http://izpack.org/
 * http://izpack.codehaus.org/
 *
 * Copyright 2002 Jan Blok
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

package com.izforge.izpack.panels.target;

import java.io.File;
import java.io.PrintWriter;
import java.util.Properties;

import com.izforge.izpack.api.data.InstallData;
import com.izforge.izpack.installer.console.AbstractPanelConsole;
import com.izforge.izpack.installer.console.PanelConsole;
import com.izforge.izpack.util.Console;

/**
 * Console implementation of the {@link TargetPanel}.
 *
 * @author Mounir El Hajj
 */
public class TargetPanelConsole extends AbstractPanelConsole implements PanelConsole
{

    /**
     * Constructs a <tt>TargetPanelConsoleHelper</tt>.
     */
    public TargetPanelConsole()
    {
        super();
    }

    public boolean runGeneratePropertiesFile(InstallData installData, PrintWriter printWriter)
    {
        printWriter.println(InstallData.INSTALL_PATH + "=");
        return true;
    }

    public boolean runConsoleFromProperties(InstallData installData, Properties properties)
    {
        String strTargetPath = properties.getProperty(InstallData.INSTALL_PATH);
        if (strTargetPath == null || "".equals(strTargetPath.trim()))
        {
            System.err.println("Missing mandatory target path!");
            return false;
        }
        else
        {
            strTargetPath = installData.getVariables().replace(strTargetPath);
            installData.setInstallPath(strTargetPath);
            return true;
        }
    }

    /**
     * Runs the panel using the specified console.
     *
     * @param installData the installation data
     * @param console     the console
     * @return <tt>true</tt> if the panel ran successfully, otherwise <tt>false</tt>
     */
    @Override
    public boolean runConsole(InstallData installData, Console console)
    {
        String strDefaultPath = TargetPanelHelper.getPath(installData);

        String strTargetPath = console.prompt("Select target path [" + strDefaultPath + "] ", null);
        if (strTargetPath != null)
        {
            strTargetPath = strTargetPath.trim();
            if (strTargetPath.isEmpty())
            {
                strTargetPath = strDefaultPath;
            }
            strTargetPath = installData.getVariables().replace(strTargetPath);

            installData.setInstallPath(strTargetPath);
            if (!strTargetPath.isEmpty())
            {
                File selectedDir = new File(strTargetPath);
                if (selectedDir.exists() && selectedDir.isDirectory() && selectedDir.list().length > 0)
                {
                    console.println("The directory already exists and is not empty. Install here and overwrite "
                                            + "existing files?");
                }
            }
            return promptEndPanel(installData, console);
        }
        else
        {
            return false;
        }
    }

}
