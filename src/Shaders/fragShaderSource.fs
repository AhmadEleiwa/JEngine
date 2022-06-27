#version 330 core
out vec4 FragColor;

in vec3 ourColor;
in vec2 TexCoord;
uniform sampler2D ourTexture;
uniform vec2 textureEnable ;


void main()
{

    vec4 texColor  = texture(ourTexture, TexCoord);
    if(texColor.a < 0.1) // if alpha is less than 0.1 so remove the pixel 
        discard;
    if(textureEnable.x == 1)
        FragColor =  vec4(ourColor,1)*texColor;
    else
        FragColor =  vec4(ourColor,1);


    
}