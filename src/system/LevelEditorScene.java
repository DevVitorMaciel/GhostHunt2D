/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system;

import java.awt.event.KeyEvent;

/**
 *
 * @author vitor
 */
public class LevelEditorScene extends Scene{
    
    private boolean changingScene = false;
    private float timeToChangeScene = 2.0f;
    
    public LevelEditorScene(){
        System.out.println("Inside LevelEditorScene! ");
    }

    @Override
    public void update(float dt) {
        System.out.println("FPS: " + (1.0f/dt));
        
        if(!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)){
            changingScene = true;
        }
        
        if(changingScene && timeToChangeScene > 0) {
            timeToChangeScene -= dt;
            Window.get().r -= dt * 5.0f;
            Window.get().g -= dt * 5.0f;
            Window.get().b -= dt * 5.0f;
        }else if(changingScene){
            Window.changeScene(1);
        }
    }
    
    
    
}
