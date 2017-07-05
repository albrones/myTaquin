package com.example.alexis.mytaquin;

/**
 * Created by Alexis on 01/07/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Collections;


public class ImageAdapter2 extends BaseAdapter {

    private Context mContext;
    private Bitmap img ;
    private Bitmap maskImg ;
    private Bitmap hiddenImg ;
    private Bitmap[] mThumbIds  ;
    private int[] position = {0 , 0 , 0 , 0 } ;
    private int width ;
    private int height ;
    private int masked =  0 ;
    private ArrayList<Integer> listeInts = new ArrayList<>();

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public ImageAdapter2(Context c ,int position, int side ){ 

        mContext = c;
        ImageAdapter imageAdapter = new ImageAdapter(c);
        int id = imageAdapter.imagesArray[position];
        img = BitmapFactory.decodeResource(c.getResources(), id); 
        maskImg = BitmapFactory.decodeResource(c.getResources(), R.drawable.skin);
        this.width  = side ;
        this.height = side ;
        mThumbIds = new Bitmap[ width * height ];

        for(int i=0;i< ( width * height ) ;i++)
        {
            this.listeInts.add(i);
        }
        Collections.shuffle(this.listeInts);

        int imageWidth  = img.getWidth() / width ;
        int imageHeight = img.getHeight() / height ;


        Bitmap tmpBitmap = BitmapFactory.decodeResource(c.getResources(), R.drawable.skin);
        maskImg =  Bitmap.createScaledBitmap(tmpBitmap , imageWidth, imageHeight, true);

        int r = 0 ;
        for (int i = 0 ; i < width ; i++){
            for ( int j = 0 ; j < height ; j++){
                Bitmap unBout = Bitmap.createBitmap(img,  imageHeight   *  j   ,  imageWidth  * i   , imageWidth , imageHeight );
                mThumbIds[listeInts.get(r)] = unBout ;
                r++ ;
            }
        }
        hiddenImg = mThumbIds[masked] ;
        masked = side * side - 1;
        mThumbIds[masked] = maskImg ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(mThumbIds[position]);

        return imageView;
    }

    public void move(int pos){

        boolean find     = false ;
        int  indice         = 0     ;
        int positionUp      = 0     ;
        int positionDown    = 0     ;
        int positionLeft    = 0     ;
        int positionRight   = 0     ;

        positionLeft = pos - 1 ;
        positionRight = pos + 1 ;

        if (positionLeft >= 0  && (pos % width) != 0)
            position[0] = positionLeft ;
        if (positionRight < (height * width) && ((pos + 1) % width) != 0)
            position[1] = positionRight ;

        positionUp = pos - height ;
        positionDown = pos + height ;

        if (positionUp >= 0)
            position[2] = positionUp ;
        if (positionDown < (height * width))
            position[3] = positionDown ;

        for (int i = 0 ; i < position.length ; i++){

            if (position[i] == masked){
                find = true ;
                indice = position[i] ;
                break;
            }
        }

        if (find){
            masked = pos ;
            mThumbIds[indice] = mThumbIds[pos] ;
            mThumbIds[pos] = maskImg ;
            listeInts.set(indice, pos) ;
            listeInts.set(pos, pos) ;
        }
    }

}

