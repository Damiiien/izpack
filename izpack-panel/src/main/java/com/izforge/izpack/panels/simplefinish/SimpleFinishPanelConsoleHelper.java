package com.izforge.izpack.panels.simplefinish;

import java.util.Properties;

import com.izforge.izpack.api.data.InstallData;
import com.izforge.izpack.installer.console.PanelConsoleHelper;
import com.izforge.izpack.util.Console;

public class SimpleFinishPanelConsoleHelper extends PanelConsoleHelper {

	@Override
	public boolean runConsoleFromProperties(InstallData installData,
			Properties properties) {
		return true;
	}

	@Override
	public boolean runConsole(InstallData installData, Console console) {

		return true;
	}

}
