#version 110

attribute vec2 vertexPosition;
attribute vec4 vertexColor;

uniform mat3 transformation;
uniform mat3 projection;

varying vec4 color;

void main(void) {
  vec3 pos = vec3(vertexPosition, 1.0);
  color = vertexColor;
  gl_Position = vec4(projection * transformation * pos, 1.0);
}
