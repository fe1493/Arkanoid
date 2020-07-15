package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The sprites.SpriteCollection class.
 */
public class SpriteCollection {
   private List<Sprite> collectionOfDifferentSprites;
   /**
    * Initalize a collection of sprites.
    */
   public SpriteCollection() {
      this.collectionOfDifferentSprites = new ArrayList<>();
   }
   /**
    * Add the sprites to the collection of sprites.
    * @param s the sprite to add
    */
   public void addSprite(Sprite s) {
      collectionOfDifferentSprites.add(s);
   }
   /**
    * Remove the sprite from the collection of sprites.
    * @param s the sprite to add
    */
   public void removeSprite(Sprite s) {
      collectionOfDifferentSprites.remove(s);
   }

   /**
    * call timePassed() on all sprites in the collection.
    */
   public void notifyAllTimePassed() {
      //make a copy of the list
      List<Sprite> collectionOfDifferentSpritesList = new ArrayList<>(this.collectionOfDifferentSprites);
      for (Sprite s: collectionOfDifferentSpritesList) {
         s.timePassed();
      }
   }
   /**
    * call drawOn(d) on all sprites.
    * @param d the draw surface
    */
   public void drawAllOn(DrawSurface d) {
      //make a copy of the list
      List<Sprite> collectionOfDifferentSpritesList = this.collectionOfDifferentSprites;
      for (Sprite s: collectionOfDifferentSpritesList) {
         s.drawOn(d);
      }
   }
}