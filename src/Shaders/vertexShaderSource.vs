#version 330 core
layout (location = 0) in vec3 aPos;
layout (location = 1) in vec3 aColor;
layout (location = 2) in vec2 aTexCoord;

layout (location = 3) in vec3 aNormal;

out vec3 ourColor;
out vec2 TexCoord;
out vec3 normal;
out vec3 fragPos;
uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

uniform vec3 color= vec3(0.8f,0.8f,0.8f);
uniform vec2 colorFromIn;

void main()
{
gl_Position = projection * view *  model * vec4(aPos, 1.0);

normal = mat3(transpose(inverse(model))) * aNormal;
fragPos = vec3(model * vec4(aPos, 1.0));
TexCoord = aTexCoord;
if(colorFromIn.x == 0 ) 
    ourColor = color;
else
    ourColor = aColor;
}