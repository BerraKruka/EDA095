
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;


public class Song extends Music{
	
	private String music_destination;

	public Song(String music_destination) throws SlickException {
		super(music_destination);
		
		this.music_destination = music_destination;
	
	}
	
	

}
