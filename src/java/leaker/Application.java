/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leaker;

import Module.IModule;
import UserModule.UserModule;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class Application {
    private static Application instance;
    private ArrayList<IModule> modules;
    
    public static Application Current() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }
    
    private Application() {
        modules = new ArrayList<IModule>();
        findModules();
    }
    
    private void findModules() {
        modules.add(new UserModule());
    }
    
    public void Install() {
        for (IModule module: modules) {
            module.Install();
        }
    }
}
