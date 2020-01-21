import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.spi.*; 
import ddf.minim.signals.*; 
import ddf.minim.*; 
import ddf.minim.analysis.*; 
import ddf.minim.ugens.*; 
import ddf.minim.effects.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Le_jeu_pong extends PApplet {










//Ecran
int xScreenSize = 1100;
int yScreenSize = 600;

int start = 1;

//Frame rate
int FPS = 60;

//Balle
float xBalle = 600;
float yBalle = 300;
float xSizeBalle = 20;
float ySizeBalle = 20;
float vitessexBalle = 5;
float vitesseyBalle = 5;

//Joueur 1
int xJ1 = 50;
int yJ1 = 50;
int xSizeJ1 = 20;
int ySizeJ1 = 150;
int VitesseJoueur = 25;


//Barre du millieu
float xBarre = 600;
float yBarre = 0;

//Text
float xScoreJ1 = 550;
float yScoreJ1 = 60;
int score = 0;
int xText = 425;
int yText = 300;
int lose = 1;
Minim minim;
AudioSnippet whatislove8bit;
AudioSnippet superSmachBrosMele;

//SetUp
public void setup() {
 size(xScreenSize, yScreenSize); 
 minim = new Minim(this);
 whatislove8bit = minim.loadSnippet("whatislove8bit.mp3");
 superSmachBrosMele = minim.loadSnippet("superSmachBrosMele.mp3");
 smooth();
 noStroke();
 frameRate(FPS);
}

public void stop() {
  whatislove8bit.close();
  superSmachBrosMele.close();
  minim.stop();
  super.stop();
}


public void draw() {
if(whatislove8bit.isPlaying() == false){
 whatislove8bit.play(); 
}

if(start == 1){  
fill(0, 0, 0, 10);
rect(0, 0, width, height);
fill(255);
ellipse(millis() % width, millis() % height, 20, 20);
textSize(30);
text("START THE GAME PRESS S", 350, 300);

  
  if(keyPressed == true){
    if(key == 's'){
   start = 2; 
    }
  }
}
 
  
  if(start == 2){
  //Fond noir
  background(0);
  
  //Joueur 1
  rect(xJ1, yJ1, xSizeJ1, ySizeJ1);
  
  
  //Balle
  rect(xBalle, yBalle, xSizeBalle, ySizeBalle);
  //Annimation de la Balle
  xBalle += vitessexBalle;
  yBalle += vitesseyBalle;
  //Collision de la Balle
  if(xBalle+VitesseJoueur/2 > width) {
    vitessexBalle = -vitessexBalle;
  }
  //Collision Balle sur J1
  if(xBalle < xSizeBalle+xSizeJ1*2 && yBalle > yJ1 && yBalle < yJ1+ySizeJ1+2) {
   vitessexBalle = -vitessexBalle; 
   score = score +1;
   
  
   if(score == 5) vitessexBalle = vitessexBalle +5;
   if(score == 10) vitessexBalle = vitessexBalle +5;
   if(score == 15) vitessexBalle = vitessexBalle +5;
   if(score == 20 && superSmachBrosMele.isPlaying() == false){
     vitessexBalle = vitessexBalle +5;
     superSmachBrosMele.play();
     whatislove8bit.close();
     vitessexBalle = vitessexBalle +5;
   }
   if(score == 25) vitessexBalle = vitessexBalle +5;
   if(score == 30) vitessexBalle = vitessexBalle +5;
   if(score == 35) vitessexBalle = vitessexBalle +5;
   if(score == 40) vitessexBalle = vitessexBalle +5;
   
  }
  if(yBalle+VitesseJoueur/2 > height) {
   vitesseyBalle = -vitesseyBalle; 
  }
  if(yBalle < 0) {
   vitesseyBalle = -vitesseyBalle; 
  }
  if(yJ1 <= 0) {
   yJ1 = 0; 
  }
 
  //Barre du millieu
  rect(xBarre, yBarre, xBarre, yBarre);
  
  //Propriete du texte
  textSize(60);
  
  //Score Joueur 1
  text(score, xScoreJ1, yScoreJ1);
  
  if(xBalle < 10) {
    lose = 2;
    noLoop();
   text("GAME OVER !", xText, yText); 
   text("RESTART PRESS T", 350, 400); 
    start = 1; 
  }
 
  }
  }



public void keyPressed(){ 
  if(start != 1){
    
  switch(key){
    //PAUSE
   case 'p':
   noLoop(); 
   text("PAUSE PRESS R TO RESUME", xText-250, yText);
   break;
   case 'r':
   loop();
   break;
  }
  
  }
  }

public void mouseMoved() {
 if(true) {
  yJ1 = mouseY;
 } else yJ1 = mouseY;
}





  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Le_jeu_pong" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
