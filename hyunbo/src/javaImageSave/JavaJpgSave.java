package javaImageSave;


import java.awt.Color;

import java.awt.Font;

import java.awt.Graphics2D;

import java.awt.font.FontRenderContext;

import java.awt.geom.Rectangle2D;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.Date;




import javax.imageio.ImageIO;





public class JavaJpgSave {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";





public static void main(String[] a){ 	

long startTime=System.currentTimeMillis();


	
	print("start make new feed image"); 
	
	String text="20,000,000원 / 3.9%";
	
	String tesxt2=getInformation();
	
	
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	
	Date date=new Date();
	
	
	
	// 저장할 파일명 생성
	
	File makeImage=new File("C:/Users/pk10625-dev/Desktop/새 폴더/202005/best/(진행중)java 이미지 파일에 텍스트 추가 개발/수정본/qm6_"+sdf.format(date)+".jpg");
	
	print("saved New image name : "+makeImage.toString());
	
	
	
	// 문구 작성 할 이미지 불러오기 
	
	File loadImage=new File("C:/Users/pk10625-dev/Desktop/새 폴더/202005/best/(진행중)java 이미지 파일에 텍스트 추가 개발/qm6.jpg");
	
	
	
	BufferedImage bi=null;
	
	try {
	
	bi=ImageIO.read(loadImage);
	
	} catch (IOException e) {
	
	print("image load error ");
	
	e.printStackTrace();
	
	}
	
	
	
	int imgWidth=bi.getWidth();
	
	int imgHeight=bi.getHeight();
	
	print("loadImage\nwidth : "+imgWidth+", height : "+imgHeight);
	
	
	
	Graphics2D g2=null;
	
	g2=bi.createGraphics();
	
	
	
	// text에 적용할 폰트 생성, 아래 폰트는 시스템에 설치 되어 있어야 사용할 수 있음
	
	Font font=new Font("맑은 고딕",0,50);
	
	
	
	// 가운데 정렬하기 위해, text의 width구하기
	
	FontRenderContext frc=new FontRenderContext(null,true,true);
	
	Rectangle2D r2=font.getStringBounds(text, frc);
	
	int textWidth=(int)r2.getWidth();
	
	float paddingleft=0;
	
	
	
	// 입력하는 문자의 가용 넓이
	
	int textWide=439;
	
	
	
	paddingleft=((textWide-textWidth)/2)+20;
	
	
	
	print("textWidth : "+textWidth);
	
	print("paddingleft : "+paddingleft);
	
	
	
	// 폰트 색상 설정
	
	g2.setColor(Color.black);
	
	// 폰트 종류 설정
	
	g2.setFont(font);
	
	// 이미지에 텍스트 사입. (text,x축,y축)
	
	g2.drawString(text,650,2000);
	g2.drawString(tesxt2,650,2080);
	
	
	g2.drawString(text,650,2230);
	g2.drawString(text,650,2350);
	g2.drawString(text,650,2480);
	g2.drawString(text,650,2610);
	g2.drawString(text,650,2740);
	g2.drawString(text,650,2870);
	
	g2.drawString(text,1000,3070);
	g2.drawString(text,1000,3150);
	g2.drawString(text,1000,3240);
	
	
	g2.dispose();
	
	try {
	
	ImageIO.write(bi, "jpg", makeImage);
	
	} catch (IOException e) {
	
	System.out.print("New Imagae Save error");
	
	e.printStackTrace();
	
	}
	
	print("text length : "+text.length());
	
	print("end make image");
	
	
	
	long endTime=System.currentTimeMillis();
	
	print("currentTimeMillis()형태\n시작시간 : "+startTime+", 종료시간 : "+endTime);
	
	print("이미지 생성하는데 걸린 시간 ["+((endTime-startTime)/1000.0)+"]");
	
	}
	


	public static void print(String str){
	
	System.out.print("\n"+str+"\n");
	
	}
	
	
	
	
	
	
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("DB_DRIVER error " + e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);

		} catch (SQLException e) {
			System.out.println("dbConnection error " + e.getMessage());
		}
		return dbConnection;
	}
	
	
	
	public static String  getInformation() {
		
		
		Connection dbConnection = null;
		
		PreparedStatement preparedStatement1 = null;


		ResultSet rs = null;

		String home_Cust_no = "";

		StringBuffer selectStatusSQL = new StringBuffer();

		selectStatusSQL.append("\n   select 문                                            ");


		

		try {	

			// DB 접속을 시도한다

			dbConnection = getDBConnection();
			dbConnection.setAutoCommit(false);
			
			
			preparedStatement1 = dbConnection.prepareStatement(selectStatusSQL.toString()); 
			rs = preparedStatement1.executeQuery();
			
			
			while(rs.next()) {
				
				home_Cust_no=rs.getString("home_Cust_no");
				
			}
			
		}catch (Exception e) {
			System.out.println("Error : DB SELECT Error");
			System.out.println(e.getMessage());
		}finally {
			
		
			if (rs != null) {

				try {

					rs.close();

				} catch (SQLException e1) {

					e1.printStackTrace();

				}
				
				if (preparedStatement1 != null) {

					try {

						preparedStatement1.close();

					} catch (SQLException e1) {

						e1.printStackTrace();

					}

				}



				if (dbConnection != null) {

					try {

						dbConnection.close();

					} catch (SQLException e1) {

						e1.printStackTrace();

					}

				}

			
			}
		}
		
		return home_Cust_no;
		
	}

	
	
}
