/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system;

import org.lwjgl.glfw.GLFW;

/**
 *
 * @author vitor
 */
public class KeyListener {
    
    private static KeyListener instance;
    private boolean KeyPressed[] = new boolean[350];
    
    private KeyListener() {
        
    }
    
    public static KeyListener get() {
        if(KeyListener.instance == null) {
            KeyListener.instance = new KeyListener();
        }
        return KeyListener.instance;
    }
    
    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if(action == GLFW.GLFW_PRESS){
            get().KeyPressed[key] = true;
        }else if(action == GLFW.GLFW_RELEASE) {
            get().KeyPressed[key] = false;
        }
    }
    
    public static boolean isKeyPressed(int keyCode) {
        boolean result = false;
        if(keyCode < get().KeyPressed.length) {
            result = get().KeyPressed[keyCode];
        } 
        return result;
    }
    
}
