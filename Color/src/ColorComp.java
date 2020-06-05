import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class ColorComp extends GameDriverV3 implements KeyListener{
	int x = 0;
	int y = 0;
	int z = 0;
	int i = 0;
	int delay = 20;
	int time = 0;
	int speed = 1;
	int gameState = 0;
	long score = 0;
	Font good = new Font("Elephant", Font.PLAIN, 30), good1 = new Font("Times New Roman", Font.PLAIN, 15);
	Rectangle player = new Rectangle(250,100,100,100);
	Rectangle timer = new Rectangle(0,0,30,30);
	Random a = new Random();
	Color ans = new Color(x, y, z), real = new Color(0,0,0);
	boolean[] end = {true, false, false, false, false};
	boolean first = true, show = false, reset = false;
	int[] solution;
	int[] places = {318, 375, 388, 375, 458, 375};
	SoundDriver sounds, next, scare, last;
	
	public ColorComp() {
		this.addKeyListener(this);
		String[] eating = new String[1];
		eating[0] = "Mario-coin-sound.wav";
		String[] die = new String[1];
		die[0] = "imperial_march.wav";
		String[] str = new String[1];
		str[0] = "Game Theory Opening Theme Science Blaster Chiptune Tuesday.wav";
		sounds = new SoundDriver(str);
		last = new SoundDriver(die);
		next = new SoundDriver(eating);
	}
	public int[] random() {
		int x = a.nextInt(52)*5;
		int y = a.nextInt(52)*5;
		int z = a.nextInt(52)*5;
		int[] n = {x, y, z};
		return n;
	}
	public boolean isTrue() {
		int a = 0;
		if(x+30 >= solution[0] && x-30 <= solution[0]) {
			a++;
		}
		if(y+30 >= solution[1] && y-30 <= solution[1]) {
			a++;
		}
		if(z+30 >= solution[2] && z-30 <= solution[2]) {
			a++;
		}
		if(a == 3) {
			return true;
		}
		return false;
	}
	public void reset() {
		first = true;
		x=0;
		y=0;
		z=0;
		speed*=2;
		end[0]=true; 
		end[1]=false; 
		end[2]=false; 
		end[3]=false; 
		end[4]=false;
		timer.setLocation(0, 0);
		show = false;
		time = 0;
		gameState = 4;
	}
	public void resetf() {
		first = true;
		x=0;
		y=0;
		z=0;
		speed*=2;
		end[0]=true; 
		end[1]=false; 
		end[2]=false; 
		end[3]=false; 
		end[4]=false;
		timer.setLocation(0, 0);
		show = false;
		speed = 1;
		score = 0;
		gameState = 1;
	}
	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		if(gameState == 0) {
			win.setColor(Color.white);
			win.fillRect(0, 0, 800, 700);
			win.setColor(Color.BLACK);
			win.setFont(good);
			win.drawString("COLORING", 300, 100);
			win.drawString("Created by: Siddharth Chauhan", 150, 250);
			win.drawString("(Press 'I' for instructions)", 190, 300);
			win.drawString("Press ENTER to start!", 210, 350);
		}		
		
		if(gameState == 1) {
			last.stop(0);
			if (!sounds.isPlaying(0)) {
				sounds.play(0);
			}
			if(first == true) {
				solution = random();
				real = new Color(solution[0], solution[1], solution[2]);
				first = false;
			}
			win.setColor(Color.white);
			win.fillRect(0, 0, 800, 700);
			win.setColor(Color.black);
			win.fillRect(0, 150, 800, 550);
			win.setColor(Color.white);
			win.setColor(ans);
			win.fillRect(350,100,100,100);
			ans = new Color(x,y,z);
			win.setColor(Color.red);
			win.fillRect(305, 300, 50, 50);
			win.setFont(good);
			win.drawString("RED: "+x, 0, 300);
			win.setColor(Color.green);
			win.fillRect(375, 300, 50, 50);
			win.drawString("GREEN: "+y, 0, 350);
			win.setColor(Color.blue);
			win.fillRect(445, 300, 50, 50);
			win.drawString("BLUE: "+z, 0, 400);
			win.setColor(Color.white);
			win.fillRect(places[i], places[i+1], 25, 25);
			win.setColor(Color.black);
			win.fill(timer);
			if(end[0] == true) {
				timer.translate(speed, 0);
				if(timer.getX() >= 770) {
					end[0] = false;
					end[1] = true;
					timer.setLocation(0, 30);
				}
			}
			if(end[1] == true) {
				timer.translate(speed, 0);
				if(timer.getX() >= 770) {
					end[1] = false;
					end[2] = true;
					timer.setLocation(0, 60);
				}
			}
			if(end[2] == true) {
				timer.translate(speed, 0);
				if(timer.getX() >= 770) {
					end[2] = false;
					end[3] = true;
					timer.setLocation(0, 90);
				}
			}
			if(end[3] == true) {
				timer.translate(speed, 0);
				if(timer.getX() >= 770) {
					end[3] = false;
					end[4] = true;
					timer.setLocation(0, 120);
				}
			}
			if(end[4] == true) {
				timer.translate(speed, 0);
				if(timer.getX() >= 770) {
					gameState = 2;
				}
			}
			win.setColor(Color.white);
			win.fillRect(0, 550, 800, 250);
			win.setColor(real);
			win.fillRect(350, 500, 100, 100);
			random();
			if(show == true) {
				win.setColor(Color.white);
				win.drawString(solution[0]+" "+solution[1]+" "+solution[2], 0, 450);
			}
			win.setColor(Color.white);
			win.setFont(good);
			win.drawString("SCORE: "+ score, 0, 175);
			if(isTrue()) {
				//win.drawString("GOOD", 0, 400);
				reset();
				score+=100;
			}
		}
		if(gameState == 2) {
			sounds.stop(0);
			
			if (!last.isPlaying(0)) {
				last.play(0);
			}
			win.setColor(Color.white);
			win.fillRect(0, 0, 800, 700);
			win.setFont(good);
			win.setColor(Color.black);
			win.drawString("Stop being such a Billy Mitchell", 150, 250);
			win.drawString("SCORE: "+ score, 300, 300);
			win.drawString("(Press 'R' to restart!)", 225, 350);
			
		}
		if(gameState == 3) {
			win.setColor(Color.white);
			win.fillRect(0, 0, 800, 700);
			win.setFont(good);
			win.setColor(Color.black);
			win.drawString("Instructions", 285, 50);
			win.setFont(good1);
			win.drawString("This is a color mixing game. Your job is to match your color with the color given before the time runs out!", 80, 100);
			win.drawString("Use the LEFT and RIGHT arrow keys to choose your color", 205, 150);
			win.drawString("Use the UP and DOWN arrow keys to change the brightness of the selected color", 140, 200);
			win.drawString("Press B to go back", 325, 250);
			win.drawString("A si edoc taehc eht", 325, 300);
		}
		if(gameState == 4) {
			if(time == 0) {
				next.play(0);
			}
			win.setColor(Color.white);
			win.fillRect(0, 0, 800, 700);
			win.setFont(good);
			win.setColor(Color.black);
			win.drawString("NEXT LEVEL!", 275, 300);
			if(time < delay) {
				time++;
			}
			else {
				gameState = 1;
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP && i == 0) {
			x+=5;
			if(x>255) {
				x=0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP && i == 2) {
			y+=5;
			if(y>255) {
				y=0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP && i == 4) {
			z+=5;
			if(z>255) {
				z=0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && i == 0) {
			x-=5;
			if(x < 0) {
				x = 255;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && i == 2) {
			y-=5;
			if(y < 0) {
				y = 255;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && i == 4) {
			z-=5;
			if(z < 0) {
				z = 255;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_I) {
			gameState = 3;
		}
		if(e.getKeyCode() == KeyEvent.VK_B) {
			gameState = 0;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameState++;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			i+=2;
			i%=6;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			i-=2;
			if(i<0) {
				i = 4;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			if(show) {
				show = false;
			}
			else {
				show = true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_R) {
			resetf();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
