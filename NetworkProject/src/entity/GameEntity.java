package entity;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tests.xml.Entity;



public abstract class GameEntity extends Entity {
	protected Shape boundingBox;
	public static final float SIZE = 34f;
	public GameEntity(Pos pos, float width, float height){
		boundingBox  = new Rectangle(pos.x, pos.y, width, height);
		boundingBox.setLocation(pos.x, pos.y);
		
	}
	
	
	public void setPos(Pos pos) {
	  boundingBox.setLocation(pos.x, pos.y);
	}
	public Shape getBoundingBox() {
		return boundingBox;
	}
	
	public boolean intersects(GameEntity entity) {
	    if (this.getBoundingBox() == null || entity == null) {
	        return false;
	    }
	    return this.getBoundingBox().intersects(entity.getBoundingBox());
	}

}
