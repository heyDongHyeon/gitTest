package geomex.xeus.map.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
/**
 * MP4 파일에서 srt 파일을 추출한다.
 *
 * @author 안형준
 *
 */
public class MovieParser {

    public MovieParser(){ }

	public static void main(String[] args) throws Exception {


		System.out.println(MovieParser.getTime("Z:\\storage\\ffmpeg\\ffprobe",

				"C:\\Users\\GEOMEX-NB-SS-ABYSER\\Desktop\\TT\\DJI_0016.mp4"));
    }

    /**
     * 작업 수행
     *
     * @param ffmpeg ffmpeg의 파일 경로
     * @param videoPath mp4파일 경로
     * @param srtPath 추출할 srt파일 경로
     * @return
     * @throws Exception
     */
    public static boolean parser(String ffmpeg, String videoPath, String srtPath)  throws Exception {

          boolean chk = true;

          String line = ffmpeg + " -y -i " + videoPath+ " " + srtPath ;

          try {
        	  File videoFile = new File( videoPath );
        	  if ( videoFile.isFile() ) {

        		  CommandLine cmdLine = CommandLine.parse(line);
        		  DefaultExecutor executor = new DefaultExecutor();
        		  executor.execute(cmdLine);
        	  } else {
        		  chk = false;

        		  System.out.println(videoPath + " 파일이 존재하지 않습니다.");
        	  }

          } catch ( ExecuteException e ) {

        	  chk = false;
        	  e.printStackTrace();

          }

         return chk;
    }

    /**
     * 작업 수행
     *  - 영상 트랙 시간 및 생성 시간을 가져오낟.
     *
     * @param ffmpeg ffmpeg의 파일 경로
     * @param videoPath mp4파일 경로
     * @return
     * @throws Exception
     */
    public static HashMap<Object, Object> getTime(String ffmpegPath, String videoPath)  throws Exception {

    	String line = ffmpegPath + "ffmpeg -y -i "+videoPath+" -vcodec mpeg1video -f mpegts -s 1280x720 -r 24/1 -b:v 1500000 -an -sn d:/test.mp4";
System.out.println(line);
    	InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        Process process = null;
        HashMap<Object, Object> map = new HashMap<Object, Object>();

        try {
System.out.println(">>>>>>>>>>>> ffmpeg tesst start ");
      		  Runtime runtime = Runtime.getRuntime();
      		  process = runtime.exec(line);
      		  System.out.println(">>>>>>>>>>>> ffmpeg test end ");

      		  is = process.getInputStream();
      		  isr = new InputStreamReader(is);
      		  br = new BufferedReader(isr);
      		  String req;

      		  while ((req = br.readLine()) != null) {
      			 System.out.println(req);
      		  }

        } catch ( ExecuteException e ) {

      	  e.printStackTrace();

        } finally {
      	  br.close();
      	  isr.close();
      	  is.close();
      	  process.destroy();
        }
    	return map;
    }
}
