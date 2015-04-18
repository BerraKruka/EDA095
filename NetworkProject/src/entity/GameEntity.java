package entity;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tests.xml.Entity;



public abstract class GameEntity extends Entity {
	protected Shape boundingBox;

	public GameEntity(Pos pos, int width, int height){
		boundingBox  = new Rectangle(pos.x, pos.y, 34f, 34f);
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
