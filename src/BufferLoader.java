public class BufferLoader {
    private int VAO;
    private int VBO;
    private String name;
    public BufferLoader(String name, int vAO, int vBO) {
        VAO = vAO;
        VBO = vBO;
        this.name = name;
    }
    public int getVAO() {
        return VAO;
    }
    public void setVAO(int vAO) {
        VAO = vAO;
    }
    public int getVBO() {
        return VBO;
    }
    public void setVBO(int vBO) {
        VBO = vBO;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
