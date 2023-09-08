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

/**
 *
 * @author vitor
 */
public class Window {
    
    int width, height;
    String title;
    private long glfwWindow;
    
    private static Window window = null;
    
    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "GhostHunt2D";
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
        
        // Adiciona o contexto do OpenGL
        GLFW.glfwMakeContextCurrent(glfwWindow);
        // Habilita o v-sync
        GLFW.glfwSwapInterval(1);
        
        // Torna a janela visível
        GLFW.glfwShowWindow(glfwWindow);
        
        GL.createCapabilities();
    }
    
    public void loop() {
        while(!GLFW.glfwWindowShouldClose(glfwWindow)) {
            // Poll events
            GLFW.glfwPollEvents();
            
            GL11.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
            GL11.glClear(GL_COLOR_BUFFER_BIT);
            
            GLFW.glfwSwapBuffers(glfwWindow);
        }
    }
    
}
