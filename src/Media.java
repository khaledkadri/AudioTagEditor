import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.TrackInfo;
import uk.co.caprica.vlcj.player.TrackType;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class Media {

	EmbeddedMediaPlayer mediaPlayer;
	MediaPlayerFactory mediaPlayerFactory;
	
	void loadLibrary(){
		String currentDir = System.getProperty("user.dir");
    	NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), "/Directory path/to/VLC installation");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        LibXUtil.initialise();
    }
	
	
	int getDuration(){
		return (int) mediaPlayer.getMediaMeta().getLength()/1000;
	}
	
	public static void main(String[] args) {
		new Media();
	}

	public String gettitle() {
		return mediaPlayer.getMediaMeta().getTitle();
	}
	
	public String getArtist(){
		return mediaPlayer.getMediaMeta().getArtist();
	}
	
	public String getAlbum(){
		return mediaPlayer.getMediaMeta().getAlbum();
	}
	
	public String getGenre(){
		return mediaPlayer.getMediaMeta().getGenre();
	}
	
	String getPicture(){
		if(mediaPlayer.getMediaMeta().getArtworkUrl()!=null)
		return mediaPlayer.getMediaMeta().getArtworkUrl();
		return "empty";
	}
	
	public String getDate(){
		return mediaPlayer.getMediaMeta().getDate();
	}
	
	public String getTrack(){
		return mediaPlayer.getMediaMeta().getTrackId();
	}
	/*******************************************************************************/
	public boolean accept(File file) {
		String[] extensions={"mp3"};
	    if (file.isDirectory()) {
	      return true;
	    } else {
	      String path = file.getAbsolutePath().toLowerCase();
		for (int i = 0, n = extensions.length; i < n; i++) {
	        String extension = extensions[i];
	        if ((path.endsWith(extension) && (path.charAt(path.length() 
	                  - extension.length() - 1)) == '.')) {
	          return true;
	        }
	      }
	    }
	    return false;
	}
	public String getCompositeur(String path){
		Mp3File mp3file;
		if(accept(new File(path))){
		try {
			mp3file = new Mp3File(path);
			if(mp3file.hasId3v2Tag()) {
	    		   ID3v2 id3v2tag = mp3file.getId3v2Tag();
	    		   if(id3v2tag.getComposer()!=null)
	    			   return id3v2tag.getComposer().toString();
			}
		}
		catch (UnsupportedTagException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InvalidDataException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		}
		return ""; 
	}
	
	public String getOriginalArtist(String path){
		Mp3File mp3file;
		if(accept(new File(path))){
		try {
			mp3file = new Mp3File(path);
			if(mp3file.hasId3v2Tag()) {
	    		   ID3v2 id3v2tag = mp3file.getId3v2Tag();
	    		   if(id3v2tag.getOriginalArtist()!=null)
	    			   return id3v2tag.getOriginalArtist().toString();
			}
		}
		catch (UnsupportedTagException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InvalidDataException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}}
		return ""; 
	}
	
	public String getRating(){
		return mediaPlayer.getMediaMeta().getRating();
	}
	
	public String getBitRate(){
		java.util.List<TrackInfo> audioTrackInfo = mediaPlayer.getTrackInfo(TrackType.AUDIO);
		int bitRate=128;
		if(audioTrackInfo.size()>0)
			bitRate = audioTrackInfo.get(0).bitRate()/1000;
		return String.valueOf(bitRate);
	}
	
	public String getDescription(){
		return mediaPlayer.getMediaMeta().getDescription();
	}
	
	public String getEncodedBy(){
		return mediaPlayer.getMediaMeta().getEncodedBy();
	}
	
	
	/*************************
	 * 
	 * getters
	 */
	public ArrayList<String> gettagss(String path) {
		// TODO Auto-generated method stub
		ArrayList<String> info = new ArrayList<String>();
		Mp3File mp3file;
		try {
			mp3file = new Mp3File(path);
			if(mp3file.hasId3v2Tag()) {
	    		   ID3v2 id3v2tag = mp3file.getId3v2Tag();
	    		   if(id3v2tag.getTitle()!=null)
	    			   info.add(id3v2tag.getTitle().toString());
	    		   else
	    			   info.add("null");
	    		   if(id3v2tag.getArtist()!=null)
	    			   info.add(id3v2tag.getArtist().toString());
	    		   else
	    			   info.add("null");
	    		   if(id3v2tag.getAlbum()!=null)
	    			   info.add(id3v2tag.getAlbum().toString());
	    		   else
	    			   info.add("null");
	    		   if(id3v2tag.getGenreDescription()!=null)
	    			   info.add(id3v2tag.getGenreDescription().toString());
	    		   else
	    			   info.add("null");
	    		   if(id3v2tag.getYear()!=null)
	    		   	   info.add(id3v2tag.getYear().toString());
	    		   else
	    			   info.add("null");
	    		}
		} catch (UnsupportedTagException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InvalidDataException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		return info;
	}
	/*************************
	 * 
	 * setters
	 */
	public void settags(String titre, String artiste,
			String album, String genre, String date, String path) {
		// TODO Auto-generated method stub
		Mp3File mp3file;
		try {
			mp3file = new Mp3File(path);
			if(mp3file.hasId3v2Tag()) {
	    		   ID3v2 id3v2tag = mp3file.getId3v2Tag();
	    		   try {
	    			    id3v2tag.setTitle(new String(titre.getBytes("UTF-8"), "UTF-8"));
	    			    id3v2tag.setArtist(new String(artiste.getBytes("UTF-8"), "UTF-8"));
	 	    		   id3v2tag.setAlbum(new String(album.getBytes("UTF-8"), "UTF-8"));
	    			} catch (UnsupportedEncodingException e) {
	    			    e.printStackTrace();
	    			}
	    		   
	    		   if(!genre.equals(" "))
	    			   id3v2tag.setGenreDescription(genre);
	    		   id3v2tag.setYear(date);
	    		   mp3file.save(path+"1");
	    		   File file = new File(path);
	    		   file.delete();
	    		   file = new File(path+"1");
	    		   file.renameTo(new File(path));
	    		}
		} catch (UnsupportedTagException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InvalidDataException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}


	public void settags(String titre, String artiste, String album,
			String genre, String date, String piste, String desc,
			String compositeur, String artiste_orig, byte[] image, String path) {
		// TODO Auto-generated method stub
		Mp3File mp3file;
		try {
			mp3file = new Mp3File(path);
			if(mp3file.hasId3v2Tag()) {
	    		   ID3v2 id3v2tag = mp3file.getId3v2Tag();
	    		   
	    		   try {
	    			    id3v2tag.setTitle(new String(titre.getBytes("UTF-8"), "UTF-8"));
	    			    id3v2tag.setArtist(new String(artiste.getBytes("UTF-8"), "UTF-8"));
	 	    		   id3v2tag.setAlbum(new String(album.getBytes("UTF-8"), "UTF-8"));
	 	    		   id3v2tag.setTrack(new String(piste.getBytes("UTF-8"), "UTF-8"));
		    		   id3v2tag.setComment(new String(desc.getBytes("UTF-8"), "UTF-8"));
		    		   id3v2tag.setComposer(new String(compositeur.getBytes("UTF-8"), "UTF-8"));
		    		   id3v2tag.setOriginalArtist(new String(artiste_orig.getBytes("UTF-8"), "UTF-8"));
	    			} catch (UnsupportedEncodingException e) {
	    			    e.printStackTrace();
	    			}
	    		   
	    		   if(!genre.equals(" "))
	    			   id3v2tag.setGenreDescription(genre);
	    		   id3v2tag.setYear(date);
	    		   
	    		   if(image!=null)
	    		   id3v2tag.setAlbumImage(image, "image/png");
	    		   
	    		   mp3file.save(path+"1");
	    		   File file = new File(path);
	    		   file.delete();
	    		   file = new File(path+"1");
	    		   file.renameTo(new File(path));
	    		}
		} catch (UnsupportedTagException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InvalidDataException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
