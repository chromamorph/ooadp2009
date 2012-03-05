package com.titanmusic.iss3test;

import processing.core.*;

@SuppressWarnings("serial")
public class RaindropGame2 extends PApplet {
	Catcher2 catcher;
	FancyRaindrop[] raindrops;
	Timer timer;

	public void setup() {
		size(800,600);
		smooth();
		catcher = new Catcher2(this,20);
		timer = new Timer(0.01f);
		raindrops = new FancyRaindrop[1000];
	}

	public void draw() {
		background(255);
		if (timer.hasRunOut())
			for(int i = 0; i < raindrops.length; i++)
				if (raindrops[i] == null || raindrops[i].reachedBottom()) {
					raindrops[i] = new FancyRaindrop(random(width),random(5,10),random(.5f,3),color(0,0,255));
					timer.restart();
					break;
				}
		for(int i = 0; i < raindrops.length; i++)
			if (raindrops[i] != null && !raindrops[i].reachedBottom())
				raindrops[i].move();
		catcher.setLocation(mouseX,mouseY);
		for(int i = 0; i < raindrops.length; i++) {
			if (raindrops[i] != null && !raindrops[i].reachedBottom()) {
				if (catcher.intersects(raindrops[i]))
					raindrops[i] = null;
				else
					raindrops[i].display();
			}
		}
		catcher.display();
	}


	/*
	Fancy raindrop class
	 */

	class FancyRaindrop {
		float x, y;
		float speed;
		int col;
		float diameter;

		FancyRaindrop(float x, float diameter, float speed, int col) {
			this.x = x;
			this.diameter = diameter;
			this.speed = speed;
			this.col = col;
			this.y = 0;

		}

		void move() {
			y += speed;
		}

		void display() {
			stroke(col);
			fill(col);
			for(int i = 0; i < 4; i++)
				ellipse(x,y-i,diameter-2*i,diameter+2*i);
		}

		boolean reachedBottom() {
			return (y > height);
		}
	}

	class Timer{
		int startTime;
		float period;

		Timer(float period) {
			startTime = millis();
			this.period = period * 1000;
		}

		void restart() {
			startTime = millis();
		}

		boolean hasRunOut() {
			return (millis() - startTime > period);
		}
	}


}
