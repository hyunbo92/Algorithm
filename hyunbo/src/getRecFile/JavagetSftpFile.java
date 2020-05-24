//http://www.jcraft.com/jsch/  여기서 jsch 라이브러리 다운받으면됨jsch-0.1.55.zip

package getRecFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JavagetSftpFile {
	
	private static Session session = null;
	private static Channel channel = null;
	private static ChannelExec channelExec = null;
	private static ChannelSftp channelSftp = null;
	private static final String PATHSEPARATOR = "/";	
    
	 //세션 오픈 메소드
	public void connect() throws JSchException{
		String url = "";
		String user = "";
		String password = "";
		int port=0;
		
		System.out.println(url); 
		//JSch 객체 생성
	JSch jsch = new JSch();
	try {
		//세션객체 생성 ( user , host, port ) 	
	    session = jsch.getSession(user, url,port);
	    
	    //password 설정
	    session.setPassword(password);
	    
	    //세션관련 설정정보 설정
	    java.util.Properties config = new java.util.Properties();
	    
	    //호스트 정보 검사하지 않는다.
	    config.put("StrictHostKeyChecking", "no");
	    session.setConfig(config);
	    
	    //접속
	    session.connect();

	    //sftp 채널 접속
	    channel = session.openChannel("sftp");
	    channel.connect();
	    System.out.println("connect success");
	} catch (JSchException e) {
	    e.printStackTrace();
	}
	channelSftp = (ChannelSftp) channel;
	
	}
    
	 public static void main(String[] args) throws Exception

	    {
		 //현재 날짜가져오기
		 SimpleDateFormat format = new SimpleDateFormat ("yyyyMMdd");
		 Date time = new Date();
		 String today = format.format(time);
				
		 
		 String sourcePath="/uploads/"+today;
		 String destinationPath = "D:\\RECDATA\\TSP\\"+today; //저장할 폴더 경로
			File Folder = new File(destinationPath);

			// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
			if (!Folder.exists()) {
				try{
				    Folder.mkdir(); //폴더 생성합니다.
				    System.out.println("폴더가 생성되었습니다.");
				} 
				catch(Exception e){
				    e.getStackTrace();
				}        
			 }else {
				System.out.println("이미 폴더가 생성되어 있습니다.");
			}
		    

		 JavagetSftpFile ftp=new JavagetSftpFile();
		 ftp.recursiveFolderDownload(sourcePath,destinationPath);
	    }
	 
    //파일 다운로드
    public void recursiveFolderDownload(String sourcePath, String destinationPath) throws Exception {
	connect();
	Vector<ChannelSftp.LsEntry> fileAndFolderList = channelSftp.ls(sourcePath); // Let list of folder content
	for (ChannelSftp.LsEntry item : fileAndFolderList) {
	    if (!item.getAttrs().isDir()) { // 파일체크
		if (!(new File(destinationPath + PATHSEPARATOR + item.getFilename())).exists()
			|| (item.getAttrs().getMTime() > Long
				.valueOf(new File(destinationPath + PATHSEPARATOR + item.getFilename()).lastModified()
					/ (long) 1000)
				.intValue())) { // Download only if changed later.
		    new File(destinationPath + PATHSEPARATOR + item.getFilename());
		    channelSftp.get(sourcePath + PATHSEPARATOR + item.getFilename(),
			    destinationPath + PATHSEPARATOR + item.getFilename()); // 파일 다운로드 하기
		}
	    } else if (!(".".equals(item.getFilename()) || "..".equals(item.getFilename()))) {
		new File(destinationPath + PATHSEPARATOR + item.getFilename()).mkdirs(); // Empty folder copy.
		recursiveFolderDownload(sourcePath + PATHSEPARATOR + item.getFilename(),
			destinationPath + PATHSEPARATOR + item.getFilename()); 
	    }
	}
	
		disconnect();
    }	    
    
 // 파일서버와 세션 종료
	public void disconnect() {
	 if(session.isConnected()){
	     System.out.println("disconnecting...");
	     channelSftp.disconnect();
	     channel.disconnect();
	     session.disconnect();
	 }
     }
}    
