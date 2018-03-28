package service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.lang.RandomStringUtils;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;
import meviii.*;


public class SearchPic {

	//��Ҫһ��facesetToken,����ͼƬ��ַ,����ʶ������������ƶ�
	public String search(String picPath) {
		// TODO Auto-generated method stub
		File file = new File(picPath);
		byte[] buff = getBytesFromFile(file);
		CommonOperate commonOperate = new CommonOperate("1C6PIleJmJ6GBjxPnygB8JFHAwYLq5Lq", "iojCR-HVuEV3feT0TExiftm0HDNAznEV", false);
		try {
			Response response = commonOperate.searchByFaceSetToken(null,null,buff, "44a77d350bccf00195ec9f27c853a23f", 1);
			while(response.getStatus()==403){
				response = commonOperate.searchByFaceSetToken(null,null,buff, "44a77d350bccf00195ec9f27c853a23f", 1);
			}
			System.out.println(new String(response.getContent()));
			JSONObject jsonObject = JSONObject.fromObject(new String(response.getContent()));
			if(jsonObject.getJSONArray("faces").size()!=0){
				JSONObject jsonObject2 = jsonObject.getJSONArray("results").getJSONObject(0);
				return "������"+jsonObject2.getString("user_id")+" ���Ŷȣ�"+jsonObject2.getString("confidence");
				/**
				 * ��ӳ��м�¼
				 */
			}else{
				return "����ͼƬδ��⵽����";
			}
			//System.out.println(new String(response.getContent()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	 
	}
	
	public double search(byte[] buff) {
		// TODO Auto-generated method stub
		CommonOperate commonOperate = new CommonOperate("1C6PIleJmJ6GBjxPnygB8JFHAwYLq5Lq", "iojCR-HVuEV3feT0TExiftm0HDNAznEV", false);
		try {
			Response response = commonOperate.searchByFaceSetToken(null,null,buff, "44a77d350bccf00195ec9f27c853a23f", 1);
			while(response.getStatus()==403){
				response = commonOperate.searchByFaceSetToken(null,null,buff, "44a77d350bccf00195ec9f27c853a23f", 1);
			}
			//System.out.println(new String(response.getContent()));
			JSONObject jsonObject = JSONObject.fromObject(new String(response.getContent()));
			//System.out.println(new String(response.getContent()));
			/*if(jsonObject.getString("error_message")!=null) {
				return "����ͼƬδ��⵽����";
			}
			else*/ if(jsonObject.getJSONArray("faces").size()!=0){
				JSONObject jsonObject2 = jsonObject.getJSONArray("results").getJSONObject(0);
				//return "������"+jsonObject2.getString("user_id")+" ���Ŷȣ�"+jsonObject2.getString("confidence");
				return jsonObject2.getDouble("confidence");
				/**
				 * ��ӳ��м�¼
				 */
			}else{
				//return "����ͼƬδ��⵽����";
				return 0.0;
			}
			//System.out.println(new String(response.getContent()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return null;
		return 101.0;
	 
	}

	public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }
	
	 public void base64StringToImage(byte[] bytes1){	        
         try {        
                     
           /*  ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);     
             FileInputStream fStream = new FileInputStream(new File("C:\\Users\\Lenovo\\Desktop\\lgx\\1.jpg"));
             BufferedImage bi1 =ImageIO.read(fStream);        
             File w2 = new File("d://testFwwb");//������jpg,png,gif��ʽ        
               
             ImageIO.write(bi1, "jpg", w2);//�������ʲô��ʽͼƬ���˴�����Ķ�       
              */     
        	 /*MemorySystem
        	 Image image = */
        	 
        	 /*OutputStream os = new FileOutputStream(new File("F:\\"+1+".jpg"));
        	 os.write(bytes1);
        	 os.flush();
        	 System.out.println("����ɹ���");
        	 os.close();*/
        	 
        	 FileImageOutputStream imageOut = new FileImageOutputStream(new File("F:\\"+UUID.randomUUID().toString()+".png"));
        	 imageOut.write(bytes1, 0, bytes1.length);
        	 imageOut.flush();
        	 imageOut.close();
        	 System.out.println("Make Picture success,Please find image in " + "F:\\");
         } catch (IOException e) {        
             e.printStackTrace();        
         }        
     }       
}
