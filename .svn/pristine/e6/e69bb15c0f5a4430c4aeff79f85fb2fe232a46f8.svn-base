package geomex.xeus.tvius.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClient {

    private String svrIp;
    private String user;
    private String passwd;
    private String defaultPath;

    public FtpClient(String svrIp, String user, String passwd, String defaultPath) {
        this.svrIp = svrIp;
        this.user = user;
        this.passwd = passwd;
        this.defaultPath = defaultPath;
    }

    /**
     * 파일 업로드
     * @param org 원본파일
     * @param targetFile 저장할 파일위치/파일명
     * @param workPath 작업할 위치
     * @throws IOException
     * @throws SocketException
     */
    public boolean upload(File org, String targetFile, String workPath, String basicPath)
            throws SocketException, IOException, Exception {

        FileInputStream fis = null;

        org.apache.commons.net.ftp.FTPClient clnt = new org.apache.commons.net.ftp.FTPClient();
        clnt.setControlEncoding("utf-8");

        try {
            clnt.connect(svrIp);
            //clnt.setBufferSize(1024*1024);
            int reply = clnt.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                throw new Exception("ftp connection refused");
            }

            clnt.setSoTimeout(1000 * 3600);
            clnt.login(user, passwd);
            clnt.setFileType(FTP.BINARY_FILE_TYPE);
            //clnt.setFileType(FTP.ASCII_FILE_TYPE);

            //clnt.enterLocalActiveMode();
            clnt.enterLocalPassiveMode();

            //폴더를 만들땐 최상위 폴더로 이동한 후 생성한다.
            //clnt.changeWorkingDirectory("");

            //서구청에 맞게 최상위 폴더 위치로 이동한다.
            clnt.changeWorkingDirectory(basicPath);

            if ( !workPath.equals("") ) {
                clnt.makeDirectory(basicPath+workPath);
                clnt.changeWorkingDirectory(basicPath+workPath);
            }

            fis = new FileInputStream(org);
            return clnt.storeFile(targetFile, fis);
        }catch(Exception e){
            e.printStackTrace();
            //LogManager.getManager().getLogger("package-logger").error(e.toString());
            return false;
        } finally {
            if (clnt.isConnected()) {
                clnt.disconnect();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }


    /**
     * 파일 다운로드
     * @param target FTP에 저장된 파일명
     * @param storePath 저장될 위치
     * @param storeNm 저장될 파일명
     * @param workPath FTP 하위폴더명
     * @param basicPath FTP 최상위 폴더
     * @throws IOException
     * @throws SocketException
     */
    public boolean download(String target, String storePath, String storeNm, String basicPath, String workPath)
            throws SocketException, IOException, Exception {

        FileOutputStream fos = null;

        org.apache.commons.net.ftp.FTPClient clnt = new org.apache.commons.net.ftp.FTPClient();
        clnt.setControlEncoding("UTF-8");

        try {
            clnt.connect(svrIp);
            //clnt.setBufferSize(1024*1024);
            int reply = clnt.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                throw new Exception("ftp connection refused");
            }

            clnt.setSoTimeout(1000 * 3600);
            clnt.login(user, passwd);
            clnt.setFileType(FTP.BINARY_FILE_TYPE);

            clnt.enterLocalPassiveMode();

            //clnt.changeWorkingDirectory("/seogu/CRMS_STORAGE"+"하위폴더명");
            clnt.changeWorkingDirectory(basicPath+workPath);

            File f = new File(storePath, storeNm);

            fos = new FileOutputStream(f);
            return clnt.retrieveFile(target, fos);
        } finally {

            if (clnt.isConnected()) {
                clnt.disconnect();
            }
            if (fos != null) {
                fos.close();
            }
        }

    }


    /**
     * 폴더 생성
     * @param pathname 만들 폴더명
     * @throws IOException
     * @throws SocketException
     */
    public void makeDir(String pathname)
            throws SocketException, IOException, Exception {

        org.apache.commons.net.ftp.FTPClient clnt = new org.apache.commons.net.ftp.FTPClient();
        clnt.setControlEncoding("utf-8");

        try {
            clnt.connect(svrIp);
            //clnt.setBufferSize(1024*1024);
            int reply = clnt.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                throw new Exception("ftp connection refused");
            }

            clnt.setSoTimeout(1000 * 100);
            clnt.login(user, passwd);
            clnt.setFileType(FTP.BINARY_FILE_TYPE);


            //clnt.enterLocalActiveMode();
            clnt.enterLocalPassiveMode();

            //폴더를 만들땐 최상위 폴더로 이동한 후 생성한다.
            clnt.changeWorkingDirectory("");
            //폴더를 생성한다. 있으면 아무동작 안함.
            clnt.makeDirectory(pathname);

        } finally {
            if (clnt.isConnected()) {
                clnt.disconnect();
            }
        }
    }

    /**
     * FTP 서버 파일 삭제
     * @param basicPath FTP 최상위 폴더
     * @param path 저장된 하위폴더명
     * @param pathNm 저장된 파일 이름
     * @throws IOException
     * @throws SocketException
     */
    public boolean deleteFile(String basicPath, String path, String fileNm)
            throws SocketException, IOException, Exception {

        org.apache.commons.net.ftp.FTPClient clnt = new org.apache.commons.net.ftp.FTPClient();
        clnt.setControlEncoding("utf-8");

        try {
            clnt.connect(svrIp);
            //clnt.setBufferSize(1024*1024);
            int reply = clnt.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                throw new Exception("ftp connection refused");
            }

            clnt.setSoTimeout(1000 * 1000);
            clnt.login(user, passwd);
            clnt.setFileType(FTP.BINARY_FILE_TYPE);

            //clnt.enterLocalActiveMode();
            clnt.enterLocalPassiveMode();

            clnt.changeWorkingDirectory(basicPath + path);
            return clnt.deleteFile(fileNm);

        } finally {
            if (clnt.isConnected()) {
                clnt.disconnect();
            }
        }
    }
}
