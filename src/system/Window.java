/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import static org.lwjgl.glfw.GLFW.glfwInit;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.system.MemoryUtil.NULL;
import util.Time;

/**
 *
 * @author vitor
 */
public class Window {
    
    int width, height;
    String title;
    private long glfwWindow;
    
    public float r, g, b, a;
    
    private static Window window = null;
    
    private static Scene currentScene;
    
    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "GhostHunt2D";
        this.r = 1;
        this.g = 1;
        this.b = 1;
        this.a = 1;
    }
    
    public static void changeScene(int newScene) {
        switch(newScene){
            case 0:
                currentScene = new LevelEditorScene();
                //currentScene.init();
                break;
            case 1:
                currentScene = new LevelScene();
                break;
            default:
                assert false : "Cena desconhecida '" + newScene + "'";
                break;
        }
    }
    
    public static Window get() {
        if(Window.window == null) {
            Window.window = new Window();
        }
        
        return Window.window;
    }
    
    public void run() {
        System.out.println("Hello LWJGL");
        
        init();
        loop();
        
        // Libera a memória.
        Callbacks.glfwFreeCallbacks(glfwWindow);
        GLFW.glfwDestroyWindow(glfwWindow);
        
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }
    
    public void init() {
        // Define um callback de erro.
        GLFWErrorCallback.createPrint(System.err).set();
        
        // Inicializa o GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Não foi possível iniciar o GLFW.");
        }
        
        // Configura o GLFW
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_TRUE);
        
        // Cria a janela
        glfwWindow = GLFW.glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if(glfwWindow == NULL) {
            throw new IllegalStateException("Falha ao criar a janela GLFW");
        }
        
        GLFW.glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        GLFW.glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        GLFW.glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        GLFW.glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);
        
        // Adiciona o contexto do OpenGL
        GLFW.glfwMakeContextCurrent(glfwWindow);
        // Habilita o v-sync
        GLFW.glfwSwapInterval(0);
        
        // Torna a janela visível
        GLFW.glfwShowWindow(glfwWindow);
        
        GL.createCapabilities();
        
        Window.changeScene(0);
    }
    
    public void loop() {
        
        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;
        
        while(!GLFW.glfwWindowShouldClose(glfwWindow)) {
            // Poll events
            GLFW.glfwPollEvents();
            
            GL11.glClearColor(r, g, b, a);
            GL11.glClear(GL_COLOR_BUFFER_BIT);
            
            if(dt >= 0){
                currentScene.update(dt);
            }
            
            if(KeyListener.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
                System.out.println("Espaço apertado!");
            }
            
            GLFW.glfwSwapBuffers(glfwWindow);
            
            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }
    
}
