#version 330 core
out vec4 FragColor;

in vec3 ourColor;
in vec2 TexCoord;
uniform sampler2D ourTexture;
uniform vec2 textureEnable ;


void main()
{
    if(textureEnable.x == 1)
        FragColor =  vec4(ourColor,1)*texture(ourTexture, TexCoord);
    else
        FragColor =  vec4(ourColor,1);


    
}