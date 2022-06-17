#version 330 core
out vec4 FragColor;

in vec3 ourColor;
in vec2 TexCoord;
uniform sampler2D ourTexture;

void main()
{
    if(TexCoord.x == 0)
        FragColor =  vec4(ourColor, 1.0f);
    else    
        FragColor = texture(ourTexture, TexCoord) ;
    
}