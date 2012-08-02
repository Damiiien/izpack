package com.izforge.izpack.panels.packs;

import java.util.ArrayList;
import java.util.Properties;

import com.izforge.izpack.api.data.InstallData;
import com.izforge.izpack.api.data.Pack;
import com.izforge.izpack.installer.console.PanelConsole;
import com.izforge.izpack.installer.console.PanelConsoleHelper;
import com.izforge.izpack.util.Console;
/**
 * A implementation of PacksPanel for a console installation
 * TODO use the OSVersion to disable some packs
 * @author drichard
 *
 */
public class PacksPanelConsoleHelper extends PanelConsoleHelper implements PanelConsole{

	@Override
	public boolean runConsoleFromProperties(InstallData installData,
			Properties properties) {
		return true;
	}


	@Override
	public boolean runConsole(InstallData installData, Console console) {
		java.util.List<Pack> selectedPacks = new ArrayList<Pack>();
		String installationMode = null;

		do {
			installationMode = console.prompt("Do you to execute the defaut installation ? Y for yes, N for no", null);
		}

		while (!isGoodValue(installationMode)); 
		if (isYes(installationMode)){
			for (Pack p : installData.getAvailablePacks()){
				if(p.isRequired() || p.isPreselected()){
					selectedPacks.add(p);
				}
			}
		}
		else if(isNo(installationMode)){
			for (Pack p : installData.getAvailablePacks()) {

				if(p.isRequired()){
					selectedPacks.add(p);
				}
				else {
					String packToInstall = null;

					do{
						packToInstall = console.prompt("Do you want to install  [" + p.getName() + "] Y for yes, N for no ", null);
					}

					while(!isGoodValue(packToInstall));

					if (isYes(packToInstall)){
						selectedPacks.add(p);
					}
					else if (isNo(packToInstall)) {
						console.println("Installation of " + p.getName() + " skipped");
					}

				}
			}

		}
		installData.setSelectedPacks(selectedPacks);
		return promptEndPanel(installData, console);
	}


	private boolean isNo(String packToInstall) {
		return packToInstall.equals("N") || packToInstall.equals("n");
	}


	private boolean isYes(String answer) {
		if (answer != null && answer.equals("y") || answer.equals("Y")){
			return true;
		}
		else return false;
	}

	private boolean isGoodValue (String answer){
		if (answer.equals("y") || answer.equals("Y") || answer.equals("n") || answer.equals("N")){
			return true;
		}
		else return false;
	}
}
