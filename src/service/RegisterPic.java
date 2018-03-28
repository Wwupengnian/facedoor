package service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import meviii.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class RegisterPic {

	public boolean UploadPic(String path1, String path2, String path3,String name) {
		// TODO Auto-generated method stub
		File file1 = new File(path1);
		byte[] buff1 = getBytesFromFile(file1);
		File file2 = new File(path2);
		byte[] buff2 = getBytesFromFile(file2);
		File file3 = new File(path3);
		byte[] buff3 = getBytesFromFile(file3);
		
		String token1 = dectect(buff1);
		String token2 = dectect(buff2);
		String token3 = dectect(buff3);
		if(token1!=null&token2!=null&token3!=null) {
			if(add(token1)&add(token2)&add(token3)) {
				setUserID(token1, name);
				setUserID(token2, name);
				setUserID(token3, name);
				return true;
			}
			else
				return false;
		}else {
			return false;
		}
		
	}

	public void setUserID(String token,String name) {
		FaceOperate faceOperate = new FaceOperate("1C6PIleJmJ6GBjxPnygB8JFHAwYLq5Lq", "iojCR-HVuEV3feT0TExiftm0HDNAznEV", false);
		try {
			Response response = faceOperate.faceSetUserId(token, name);
			while(response.getStatus()==403) {
				response = faceOperate.faceSetUserId(token, name);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean add(String token) {
		 FaceSetOperate faceSetOperate = new FaceSetOperate("1C6PIleJmJ6GBjxPnygB8JFHAwYLq5Lq", "iojCR-HVuEV3feT0TExiftm0HDNAznEV", false);
		 try {
				Response response = faceSetOperate.addFaceByFaceToken(token, "44a77d350bccf00195ec9f27c853a23f");
				while(response.getStatus()==403) {
					response = faceSetOperate.addFaceByFaceToken(token, "44a77d350bccf00195ec9f27c853a23f");
				}
				JSONObject jsonObject = JSONObject.fromObject(new String(response.getContent()));
				if(jsonObject.getString("face_added")!=null&jsonObject.getInt("face_added")==1)
					return true;
				else 
					return false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
		 
	}
	public String dectect(byte[] buff) {
		CommonOperate commonOperate = new CommonOperate("1C6PIleJmJ6GBjxPnygB8JFHAwYLq5Lq", "iojCR-HVuEV3feT0TExiftm0HDNAznEV", false);
		try {
			Response response = commonOperate.detectByte(buff, 1, null);
			while(response.getStatus()==403) {
				response = commonOperate.detectByte(buff, 1, null);
			}
			JSONObject jsonObject = JSONObject.fromObject(new String(response.getContent()));
			JSONArray jsonArray = jsonObject.getJSONArray("faces");
			return jsonArray.getJSONObject(0).getString("face_token");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
}
