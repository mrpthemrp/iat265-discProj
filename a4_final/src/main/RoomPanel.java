/* CLASS COMMENT: 
 * This file contains a class that creates 
 * a panel object and is the main class.*/
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import background.Background;
import background.Screens;
import background.TimeOfDay;
import candle.*;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import recordplayer.DeviceAttachments;
import recordplayer.Knob;
import recordplayer.Needle;
import recordplayer.PlayerBit;
import recordplayer.PowerBtn;
import recordplayer.RecordPlayer;
import recordplayer.Sleeve;
import recordplayer.Vinyl;
import recordplayer.VinylBox;
import util.MinimHelp;

public class RoomPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int W_WIDTH = 1268;
	public static int W_HEIGHT = 674;

	// PASSED INTO METHOD
	public static int SUNRISE = 0;
	public static int MORNING = 1;
	public static int AFTERNOON = 2;
	public static int SUNSET = 3;
	public static int EVENING = 4;

	// ACTUAL STATES
	public static int START = 0;
	public static int BEGIN = 1;
	public static int REPLAY = 2;
	public static int RECORD_CLOSE = 3;
	public static int RECORD_OPEN = 4;
	public static int VINYL_OUT_OF_SLEEVE = 5;
	public static int VINYL_SELECTED = 6;
	public static int VINYL_SET = 7;
	public static int SONG_PLAYING = 8;

	private Screens screen;
	private Background bkgd;
	private DeviceAttachments recPlyr;
	private RecordPlayer recPlayer;
	private Knob volKnob;
	private Candle candle;
	private Lighter lighter;
	private PowerBtn powerBtn;
	private AudioPlayer roomNoise;
	private VinylBox box;
	private PlayerBit bit;
	private Needle needle;
	private Vinyl selectedVinyl;
	private TimeOfDay windowBkgd;
	private ArrayList<Vinyl> vinylList;
	private ArrayList<Sleeve> sleeveList;
	private AudioPlayer discSet;

	// VOLUME CONSTANTS
	private final float LOW = -3.0f;
	private final float MID = 2.0f;
	private final float HIGH = 6.0f;

	private double mouseX;
	private double mouseY;

	public static int state = 0;
	public static boolean discPicked, switchIsOn, needleIsSet, recordIsOnPlayer;
	public int windowState = 0;
	public String status = "";
	private int selectedSongIndex = 0;
	private float songLengthPlayed, songStartTimeSec, songPauseTimeSec;

	private Minim minim;

	private Timer t;
	private long startTimeSec, currentTimeSec, previousSecond;

	private JFrame frame;

	private void resetProgram() {
		// SET STATES
		discPicked = false;
		recordIsOnPlayer = false;
		switchIsOn = false;
		needleIsSet = false;
		
		//CANDLE STATE
		Candle.state = Candle.CANDLE_BLOWNOUT;
		
		//song
		songLengthPlayed = 0;
		songStartTimeSec = 0;
		songPauseTimeSec = 0;
		
		state =0;
		selectedSongIndex =0;
		
		selectedVinyl = null;
	}
	
	
	public RoomPanel(JFrame jframe) {
		frame = jframe;

		// DEFAULT BACKGROUND INITIALIZATIONS
		this.setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(W_WIDTH, W_HEIGHT));
		startTimeSec = System.currentTimeMillis();

		// SET STATES
		discPicked = false;
		recordIsOnPlayer = false;
		switchIsOn = false;
		needleIsSet = false;

		// INITIALIZE OBJECTS
		minim = new Minim(new MinimHelp());
		vinylList = new ArrayList<>();
		sleeveList = new ArrayList<>();
		addAllImages();
		addAllSleeves();
		screen = new Screens();
		bkgd = new Background("assets/defaultBackground.png");
		bit = new PlayerBit("assets/recordPlayer/discBit.png", "sounds/discSet.mp3", minim);
		box = new VinylBox("assets/vinylBox/boxBack.png", "sounds/onOff.mp3", minim, "assets/vinylBox/boxFront.png");
		needle = new Needle("assets/recordPlayer/needle.png", "sounds/needleOn.mp3", minim);
		windowBkgd = new TimeOfDay(this);
		discSet = minim.loadFile("sounds/discSet.mp3");
		songLengthPlayed = 0;
		songStartTimeSec = 0;
		songPauseTimeSec = 0;

		candle = new Candle("assets/candle/candleBase.png", "sounds/candleCrackle.mp3", minim);
		lighter = new Lighter("assets/candle/lightBody.png","sounds/lighter.mp3", minim);

		// DECORATOR STUFF
		recPlayer = new RecordPlayer("assets/recordPlayer/recordPlayer.png", "sounds/capClick.mp3", minim);
		recPlyr = recPlayer;
		volKnob = new Knob(recPlyr, "assets/recordPlayer/volKnobLow.png","sounds/knobTurn.mp3", minim);
		recPlyr = volKnob;
		powerBtn = new PowerBtn(recPlyr, "assets/recordPlayer/onOff.png","sounds/onOff.mp3", minim);
		recPlyr = powerBtn;
		// INITIALIZE SOUNDS
		setDefaultSounds();

		// MOUSELISTENER INITIALIZATIONS
		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);

		MyMouseMotionListener mml = new MyMouseMotionListener();
		addMouseMotionListener(mml);

		// START TIMER
		t = new Timer(30, this);
		t.start();
	}

	// PRIVATE METHODS
	private void addAllImages() {
		vinylList.add(new Vinyl("'Still With You' by JK of BTS", "assets/discs/stillWithYouDisc.png",
				"songs/stillMix.mp3", minim, RoomPanel.W_WIDTH / 2, RoomPanel.W_HEIGHT / 2, vinylList.size()));
		vinylList.get(vinylList.size() - 1).setMinMax(630, 700, 190, 345);

		vinylList.add(new Vinyl("'Dope Lovers' by DPR IAN", "assets/discs/dopeDisc.png", "songs/dopeLoversMix.mp3",
				minim, RoomPanel.W_WIDTH / 2, RoomPanel.W_HEIGHT / 2, vinylList.size()));
		vinylList.get(vinylList.size() - 1).setMinMax(700, 720, 190, 340);

		vinylList.add(new Vinyl("'Fly Me to the Moon' by Frank Sinatra", "assets/discs/flyDisc.png",
				"songs/moonMix.mp3", minim, RoomPanel.W_WIDTH / 2, RoomPanel.W_HEIGHT / 2, vinylList.size()));
		vinylList.get(vinylList.size() - 1).setMinMax(730, 755, 190, 340);

		vinylList.add(new Vinyl("'Jasmine' by DPR LIVE", "assets/discs/jasmineDisc.png", "songs/jasmineMix.mp3", minim,
				RoomPanel.W_WIDTH / 2, RoomPanel.W_HEIGHT / 2, vinylList.size()));
		vinylList.get(vinylList.size() - 1).setMinMax(760, 790, 190, 340);

		vinylList.add(new Vinyl("'La Vie En Rose' by Edith Piaf", "assets/discs/laVieDisc.png", "songs/laVieMix.mp3",
				minim, RoomPanel.W_WIDTH / 2, RoomPanel.W_HEIGHT / 2, vinylList.size()));
		vinylList.get(vinylList.size() - 1).setMinMax(795, 825, 190, 340);

		vinylList.add(new Vinyl("'No Blueberries' by DPR IAN ft. CL, DPR LIVE", "assets/discs/noBlueDisc.png",
				"songs/noBlueMix.mp3", minim, RoomPanel.W_WIDTH / 2, RoomPanel.W_HEIGHT / 2, vinylList.size()));
		vinylList.get(vinylList.size() - 1).setMinMax(825, 860, 190, 340);

	}

	private void addAllSleeves() {
		sleeveList.add(new Sleeve("assets/sleeves/sleeveJK.jpg", "sounds/sleeveSlide.mp3", minim,
				"assets/sleeves/takeOut1.png", RoomPanel.W_WIDTH / 2, RoomPanel.W_HEIGHT / 2, sleeveList.size()));

		sleeveList.add(new Sleeve("assets/sleeves/sleeveMITOWhite.jpg", "sounds/sleeveSlide.mp3", minim,
				"assets/sleeves/takeOut2.png", RoomPanel.W_WIDTH / 2, RoomPanel.W_HEIGHT / 2, sleeveList.size()));

		sleeveList.add(new Sleeve("assets/sleeves/sleeveMoon.jpg", "sounds/sleeveSlide.mp3", minim,
				"assets/sleeves/takeOut3.png", RoomPanel.W_WIDTH / 2, RoomPanel.W_HEIGHT / 2, sleeveList.size()));

		sleeveList.add(new Sleeve("assets/sleeves/sleeveHer.jpg", "sounds/sleeveSlide.mp3", minim,
				"assets/sleeves/takeOut4.png", RoomPanel.W_WIDTH / 2, RoomPanel.W_HEIGHT / 2, sleeveList.size()));

		sleeveList.add(new Sleeve("assets/sleeves/sleeveRose.jpg", "sounds/sleeveSlide.mp3", minim,
				"assets/sleeves/takeOut5.png", RoomPanel.W_WIDTH / 2, RoomPanel.W_HEIGHT / 2, vinylList.size()));

		sleeveList.add(new Sleeve("assets/sleeves/sleeveMITOBlack.jpg", "sounds/sleeveSlide.mp3", minim,
				"assets/sleeves/takeOut6.png", RoomPanel.W_WIDTH / 2, RoomPanel.W_HEIGHT / 2, sleeveList.size()));

	}

	// Reference for figuring out time:
	// https://stackoverflow.com/questions/22025534/find-how-long-swing-timer-has-been-running
	private void updateTime() {
		previousSecond = currentTimeSec;
		currentTimeSec = System.currentTimeMillis() - startTimeSec;
		currentTimeSec /= 1000;

		if (currentTimeSec != previousSecond && currentTimeSec % 30 == 0) {
			if (windowState == 4) {
				windowState = SUNRISE;
			} else {
				windowState++;
			}
			
			if(windowState%2 ==0) {
				Candle.state  = Candle.CANDLE_BLOWNOUT;
			}
		}
	}

	private void setDefaultSounds() {
		// set background noise
		roomNoise = minim.loadFile("sounds/roomNoise.mp3");
	}

	private void setSelectedVinyl(int index) {
		selectedVinyl = box.getSelectedVinyl();
		this.selectedSongIndex = index;
	}

	private void drawStatusBar(Graphics2D g) {
		Font f = new Font("Arial", Font.BOLD, 12);
		g.setFont(f);
		g.setColor(Color.GRAY.darker());
		g.fillRect(0, getSize().height - 24, getSize().width, 24);
		g.setColor(Color.WHITE);
		g.drawString(status, 12, getSize().height - 8);
	}

	// PUBLIC METHODS
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// we create a separate Graphics2D variable for vinyl
		// because we need to use AffineTransform directly
		Graphics2D gVinyl = (Graphics2D) g2.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gVinyl.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (state < RECORD_CLOSE) {
			screen.drawScreen(g2);
		}
		if (state == RECORD_CLOSE) {
			// the same regardless of state
			windowBkgd.drawSky(g2);
			bkgd.drawBackground(g2);
			windowBkgd.drawFilter(g2);
			box.draw(g2);
			sleeveList.get(0).draw(g2);
			((VinylBox) box).drawFront(g2);
			recPlyr.drawAttachments(g2);
			bit.draw(g2);
			needle.draw(g2);
		}
		if (state > RECORD_CLOSE) {
			// the same regardless of state
			windowBkgd.drawSky(g2);
			bkgd.drawBackground(g2);
			windowBkgd.drawFilter(g2);
			box.draw(g2);

			if (state == RECORD_CLOSE) {
				sleeveList.get(0).draw(g2);
				((VinylBox) box).drawFront(g2);
				recPlyr.drawAttachments(g2);
				bit.draw(g2);
				needle.draw(g2);
			}
			if (state == RECORD_OPEN) {
				sleeveList.get(0).draw(g2);
				box.drawFront(g2);
				recPlyr.drawAttachments(g2);
				bit.draw(g2);
				needle.draw(g2);
			}
			if (state == VINYL_SELECTED) {
				sleeveList.get(selectedSongIndex).drawSleevesInBox(g2);
				box.drawFront(g2);
				recPlyr.drawAttachments(g2);
				bit.draw(g2);
				needle.draw(g2);
				selectedVinyl.drawVinyl(g2);
				sleeveList.get(selectedSongIndex).drawSleeveOutBox(g2);

			}
			if (state == VINYL_OUT_OF_SLEEVE) {

				sleeveList.get(selectedSongIndex).draw(g2);
				box.drawFront(g2);
				recPlyr.drawAttachments(g2);
				bit.draw(g2);
				needle.draw(g2);
				selectedVinyl.drawVinyl(g2);

			}
			if (state == VINYL_SET) {
				sleeveList.get(selectedSongIndex).draw(g2);
				box.drawFront(g2);
				recPlyr.drawAttachments(g2);
				selectedVinyl.draw(gVinyl);

				// redraw afterwards
				needle.draw(g2);
				bit.draw(g2);

			}
			if (state == SONG_PLAYING) {
				sleeveList.get(selectedSongIndex).draw(g2);
				box.drawFront(g2);
				recPlyr.drawAttachments(g2);
				selectedVinyl.draw(gVinyl);

				// redraw afterwards
				needle.draw(g2);
				bit.draw(g2);

			}

			candle.draw(g2);
			lighter.draw(g2);
		}
		
		drawStatusBar(g2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (state == START) {
			status = "Click on START.";
		}
		if (state == BEGIN) {
			status = "Click on BEGIN once you have finished reading the instructions.";
		}
		if (state == REPLAY) {
			roomNoise.pause();
			needle.getSound().pause();
			if(screen.getExit()) {
				status = "Exiting ... ";		
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));		
			}else {
				status = "Choose an option.";
			}
		}

		if (state >= RECORD_CLOSE) {
			if (!roomNoise.isPlaying()) {
				roomNoise.play(0);
			}

			if (state == RECORD_CLOSE) {
				status = "Click on the front of the record player case to open record player";
			}
			if (state == RECORD_OPEN) {
				status = "Click on a vinyl sleeve in the vinyl collection to select a song.";
			}
			if (state == VINYL_SELECTED) {
				status = "Drag vinyl out of its sleeve.  |  Selected Song: " + selectedVinyl.getSongInfo();

			}
			if (state == VINYL_OUT_OF_SLEEVE) {
				status = "Put vinyl onto the middle of the record player.  |  Selected Song: "
						+ selectedVinyl.getSongInfo();

			}
			if (state == VINYL_SET) {
				if (!needleIsSet) {
					status = "Click on needle head to set onto disc. |  Selected Song: " + selectedVinyl.getSongInfo();
				} else {
					status = "Flip the record player switch to 'ON' to start playing the song.  |  Selected Song: "
							+ selectedVinyl.getSongInfo();
				}
				if (switchIsOn && needleIsSet) {
					status = "Adjust volume by clicking on the volume knob.  |  Selected Song: "
							+ selectedVinyl.getSongInfo();
					songStartTimeSec = currentTimeSec;
					songLengthPlayed = currentTimeSec - songStartTimeSec;
					selectedVinyl.getSound().play();
					state = SONG_PLAYING;
				}

			}
			if (state == SONG_PLAYING) {
				// LIGHTER
				if(Candle.state != Candle.CANDLE_BURN) {
					if(!lighter.getOn() ) {
						status = "Click on the lighter (black rectangle by the vinyls) to turn it on.  |  Selected Song: " + selectedVinyl.getSongInfo();
					}
					else if (lighter.getOn() ) {
						status = "Drag the lighter around the candle wick to light it.  |  Selected Song: " + selectedVinyl.getSongInfo();	
					}
				}
				else if (Candle.state == Candle.CANDLE_BURN) {
					status = "Place the lighter back into its starting position. |  Selected Song: " + selectedVinyl.getSongInfo();	
					if(!lighter.getOn()) {
						status = "Adjust volume with knobs, pause song by clicking power button. Otherwise enojy the song and candle!  |  Selected Song: " + selectedVinyl.getSongInfo();	
					}
				}				

				// PLANT

				if (!switchIsOn) {
					songPauseTimeSec = currentTimeSec - songStartTimeSec - songLengthPlayed;
					selectedVinyl.getSound().pause();
				} else {
					songLengthPlayed = currentTimeSec - songStartTimeSec - songPauseTimeSec;
					selectedVinyl.getSound().play();
				}

				// song ended
				if (songLengthPlayed >= selectedVinyl.getSound().length() / 1000) {
					songLengthPlayed = 0;
					selectedVinyl.getSound().pause();
					state = REPLAY;
				}
			}

			// FOR VOLUME ONLY
			if (state >= VINYL_OUT_OF_SLEEVE) {
				// Volume reference:
				// stackoverflow.com/questions/3495699/controlling-volume-of-a-clip-when-using-java-sound-javax-sound-sampled
				@SuppressWarnings("deprecation")
				FloatControl floatControl = (FloatControl) selectedVinyl.getSound()
						.getControl(FloatControl.Type.MASTER_GAIN);

				if (volKnob.getState() == Knob.VOL_LOW) {
					floatControl.setValue(LOW);
				} else if (volKnob.getState() == Knob.VOL_MID) {
					floatControl.setValue(MID);
				} else if (volKnob.getState() == Knob.VOL_HIGH) {
					floatControl.setValue(HIGH);
				}
			}
			// always update
			updateTime();
			windowBkgd.updateTimeOfDay();
		}

		repaint();
	}

	// MOUSELISTENER METHODS
	public class MyMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();

			if (state == START) {
				if (screen.hitStartBtn(mouseX, mouseY)) {
					state = BEGIN;
				}
			}
			if (state == BEGIN) {
				if (screen.hitBeginBtn(mouseX, mouseY) && !screen.hitStartBtn(mouseX, mouseY)) {
					state = RECORD_CLOSE;
				}
			}
			if (state == REPLAY) {
				if (screen.hitReplayBtn(mouseX, mouseY)) {
					screen.setExitStatus(false);
					frame.dispose();
					resetProgram();
					frame = new RoomApp("Digital Record Player Simulation");
				}
				if(screen.hitExitBtn(mouseX, mouseY)){
					screen.setExitStatus(true);
				}
			}

			// cap
			if (state == RECORD_CLOSE) {
				if (recPlayer.capHitClose(mouseX, mouseY) &&!screen.hitBeginBtn(mouseX, mouseY) ) {
					recPlayer.getSound().play(0);
					state = RECORD_OPEN;
				}
			}
			
			if (state > RECORD_CLOSE) {

				if (state != RECORD_CLOSE) {
					// vinylbox
					if (state == RECORD_OPEN || state == VINYL_SELECTED) {
						if (box.checkIfVinylSelected(vinylList, mouseX, mouseY)) {
							discPicked = true;
							selectedSongIndex = box.getSelectedVinyl().getIndex();
							setSelectedVinyl(selectedSongIndex);
							state = VINYL_SELECTED;
						}
					}
				}
				if (recordIsOnPlayer) {
					// powerBtn
					if (powerBtn.hit(mouseX, mouseY)) {
						powerBtn.getSound().play(0);
						if (switchIsOn == true) {
							switchIsOn = false;
						} else {
							switchIsOn = true;
						}
					}

					// needle
					if (needle.clicked(mouseX, mouseY)) {
						needle.getSound().play(0);
						needleIsSet = true;
					}
				}

				if (state == SONG_PLAYING) {
					// volKnob
					if (volKnob.hit(mouseX, mouseY)){
						volKnob.getSound().play(0);
					}
					
					//lighter
					if(lighter.hit(mouseX, mouseY)) {
						lighter.setOn(true);
						lighter.getSound().play(0);
					}
				}

			}
		}

		
	}

	public class MyMouseMotionListener extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {

			mouseX = e.getX();
			mouseY = e.getY();

			if (discPicked) {
				if (selectedVinyl.clicked(selectedVinyl.getX(), selectedVinyl.getY())) {
					selectedVinyl.setPos(mouseX, mouseY);
				}

				if (!selectedVinyl.getInSleeve()) {
					if (selectedVinyl.hit(recPlayer)) {
						discSet.play();
						state = VINYL_SET;
						recordIsOnPlayer = true;
						needleIsSet = false;
					}
				} else if (selectedVinyl.getInSleeve()) {
					sleeveList.get(selectedSongIndex).getSound().play(0);
					if (selectedVinyl.getX() >= RoomPanel.WIDTH / 2 + 1000) {
						selectedVinyl.setInSleeve(false);
						sleeveList.get(selectedSongIndex).getSound().pause();
						state = VINYL_OUT_OF_SLEEVE;
					}
				}
				
				//LIGHTER
				if(state == SONG_PLAYING) {
					if(lighter.getOn()) {
						lighter.setPos(mouseX, mouseY);
						if(lighter.hitCandle()) {
							Candle.state = Candle.CANDLE_BURN;
							candle.getSound().play();
							candle.getSound().loop();
						}
						
						if(lighter.startPos()) {
							lighter.setOn(false);
						}
					}
					
				}
			}

		}
	}
}
