#version 330 core
out vec4 FragColor;

in vec3 ourColor;
in vec2 TexCoord;
in vec3 normal;
in vec3 fragPos;

uniform sampler2D ourTexture;
uniform vec2 textureEnable ;

uniform vec3 lightColor = vec3(1,1,1);
uniform vec3 lightPos;
uniform float  a= 1;
uniform float  lightOn = 0;
uniform vec3  result = vec3(0,0,0);
void main()
{
    float ambientStrength = 0.4f;
    vec3 norm  = normalize(normal);
    vec3 lightDir = normalize(lightPos - fragPos);
    float diff = max(dot(norm, lightDir), 0.0);
    vec3 diffuse = diff*lightColor;
    vec3 ambient = ambientStrength * lightColor;

    vec3 result;
    if(lightOn == 1 ){
        result = vec3(diffuse+ambient);
    }else{
        result = vec3(1,1,1);
    }
       



    vec4 texColor  = texture(ourTexture, TexCoord);
    if(texColor.a < 0.1) // if alpha is less than 0.1 so remove the pixel 
        discard;
        // FragColor =  vec4(ourColor*(result),a);
    if(textureEnable.x == 1)
        FragColor =  vec4(ourColor*(result),a)*texColor;
    else
        FragColor =  vec4(ourColor*(result),a);


    
}