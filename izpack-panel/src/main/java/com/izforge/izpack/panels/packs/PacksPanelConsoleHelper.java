package com.izforge.izpack.panels.packs;

import java.util.ArrayList;
import java.util.Properties;

import com.izforge.izpack.api.data.InstallData;
import com.izforge.izpack.api.data.Pack;
import com.izforge.izpack.installer.console.PanelConsole;
import com.izforge.izpack.installer.console.PanelConsoleHelper;
import com.izforge.izpack.util.Console;

public class PacksPanelConsoleHelper extends PanelConsoleHelper implements PanelConsole{

	@Override
	public boolean runConsoleFromProperties(InstallData installData,
			Properties properties) {
		return true;
	}


	@Override
	public boolean runConsole(InstallData installData, Console console) {
		java.util.List<Pack> selectedPacks = new ArrayList<Pack>();
		String installationMode = console.prompt("Do you to execute the defaut installation ? Y for yes, N for no", null);
		if (installationMode != null && installationMode.equals("y") || installationMode.equals("Y")){
			for (Pack p : installData.getAvailablePacks()){
				if(p.isRequired() || p.isPreselected()){
					selectedPacks.add(p);
				}
			}
		}
		else if(installationMode != null && installationMode.equals("n") || installationMode.equals("N")){
			for (Pack p : installData.getAvailablePacks()) {
				if(p.isRequired()){
					selectedPacks.add(p);
				}
				else {
					String	packToInstall = console.prompt("Do you want to install  [" + p.getName() + "] Y for yes, N for no ", null);
					if (packToInstall != null && packToInstall.equals("y") || packToInstall.equals("Y")){
						selectedPacks.add(p);
					}
					else if (packToInstall.equals("N") || packToInstall.equals("n")) {
						console.println("Installation of " + p.getName() + " skipped");
					}

				}
			}

		}
		installData.setSelectedPacks(selectedPacks);
		return promptEndPanel(installData, console);

	}
}
