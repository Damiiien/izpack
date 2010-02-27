package com.izforge.izpack.api.data.binding;

import java.util.ArrayList;
import java.util.List;

/**
 * Global model that will contains all xml information
 *
 * @author Anthonin Bonnefoy
 */
public class IzpackProjectInstaller
{
    private List<Listener> listeners = new ArrayList<Listener>();

    public void add(Listener listener)
    {
        this.listeners.add(listener);
    }

    public List<Listener> getListeners()
    {
        return listeners;
    }
}
