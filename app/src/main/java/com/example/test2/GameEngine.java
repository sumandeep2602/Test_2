package com.example.test2;


import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Rect;
        import android.util.Log;
        import android.view.MotionEvent;
        import android.view.SurfaceHolder;
        import android.view.SurfaceView;

        import java.util.ArrayList;
        import java.util.Random;

public class GameEngine<candyHitbox> extends SurfaceView implements Runnable {

    // Android debug variables
    final static String TAG="DINO-RAINBOWS";

    // screen size
    int screenHeight;
    int screenWidth;
    Rect playerHitbox;
    Rect rainbowHitbox;
    Rect candyHitbox;
    Rect garbageHitbox;
    int diansourXposition = 200;
    int diansourYposition = 1200;
    int candyXposition = 1200;
    int candyYposition = 400;
    Bitmap rainbowimge;
    Bitmap candyimge;
 int garbageXposition = 100;
 int  garbageYposition =
 int rainbowXposition = 1000;
 int rainbowYposition =  300;

    int lives = 3;
    int score = 0;
    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;



    // -----------------------------------
    // GAME SPECIFIC VARIABLES
    // -----------------------------------

    // ----------------------------
    // ## SPRITES
    // ----------------------------

    // represent the TOP LEFT CORNER OF THE GRAPHIC

    // ----------------------------
    // ## GAME STATS
    // ----------------------------


    public GameEngine(Context context, int w, int h) {
        super(context);

        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;

        this.printScreenInfo();

        this.rainbowimge = BitmapFactory.decodeResource(this.getContext().getResources(),
                R.drawable.walk);
        this.candyimge = BitmapFactory.decodeResource(this.getContext().getResources(),
                R.drawable.frame1);
    }

this.candyHitbox = new Rect(1200,400,1200+candyimge.getWidth();
                400+candyimge.getHeight()
                        );
    this.rainbowHitbox = new Rect(1000,300,1200+rainbowimge.getWidth();
                300+rainbowimge.getHeight()
                        );

    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }

    private void spawnPlayer() {
        //@TODO: Start the player at the left side of screen
    }
    private void spawnEnemyShips() {
        Random random = new Random();

        //@TODO: Place the enemies in a random location

    }

    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------

    public void updatePositions()
    {
         if(this.fingerAction == "mousedown")
         {
             this.diansourYposition = this.diansourYposition-100;
         }
         if(this.fingerAction == "mouseup")
         {
             this.diansourXposition = this.diansourXposition + 100;
         }
    }

    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------

            // configure the drawing tools
            this.canvas.drawColor(Color.argb(255,255,255,255));
            paintbrush.setColor(Color.WHITE);


            // DRAW THE PLAYER HITBOX
            // ------------------------
            // 1. change the paintbrush settings so we can see the hitbox
            paintbrush.setColor(Color.BLUE);
            paintbrush.setStyle(Paint.Style.STROKE);
            paintbrush.setStrokeWidth(5);

            //----------------
            this.holder.unlockCanvasAndPost(canvas);
            //canvas.drawBitmap(playerImage, playerXPosition, playerYPosition, paintbrush);
            // draw the player's hitbox
            //canvas.drawRect(this.playerHitbox, paintbrush);

            // draw the enemy graphic on the screen
            //canvas.drawBitmap(ememyImage, enemyXPosition, enemyYPosition, paintbrush);
            // 2. draw the enemy's hitbox
            //canvas.drawRect(this.enemyHitbox, paintbrush);


            // draw enemy 2 on the screen
            // draw the enemy graphic on the screen
            //canvas.drawBitmap(enemy2Image, enemy2XPosition, enemy2YPosition, paintbrush);
            // 2. draw the enemy's hitbox
            //canvas.drawRect(this.enemy2Hitbox, paintbrush);




            // DRAW GAME STATS
            // -----------------------------
            paintbrush.setTextSize(60);
            canvas.drawText("Lives remaining: " + lives,
                    1100,
                    800,
                    paintbrush
            );

            //----------------
            this.holder.unlockCanvasAndPost(canvas);

            paintbrush.setTextSize(60);
            canvas.drawText("Score: " + score,
                    1200,
                    1000,
                    paintbrush
            );

            //----------------
            this.holder.unlockCanvasAndPost(canvas);

            if(this.playerHitbox.intersect((this.rainbowHitbox) && (this.candyHitbox) == true)))
            {

                     score= score+1;
            }
            else if (this.playerHitbox.intersect((this.garbageHitbox) == true)
            {
               lives = lives -1;
            }
        }
    }



    public void setFPS() {
        try {
            gameThread.sleep(120);
        }
        catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------


    String fingerAction = "";

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();
        //@TODO: What should happen when person touches the screen?
        if (userAction == MotionEvent.ACTION_DOWN)
        {
            fingerAction = "mousedown";
        }
        else if (userAction == MotionEvent.ACTION_UP)
        {
              fingerAction = "mouseup";
        }

        return true;
    }
    }