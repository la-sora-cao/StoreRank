package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sora on 2017-05-06.
 *
 * @author Sora
 */
public class CsvUtils {

	/**
	 * export
	 *
	 * @param file
	 * @param dataList
	 * @return
	 */
	public static boolean exportCsv(File file, List<String> dataList){
		boolean isSuccess=false;

		FileOutputStream out=null;
		OutputStreamWriter osw=null;
		BufferedWriter bw=null;
		try {
			out = new FileOutputStream(file);
			// config utf-8
			osw = new OutputStreamWriter(out, "UTF-8");
			osw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));
			bw =new BufferedWriter(osw);
			if(dataList!=null && !dataList.isEmpty()){
				for(String data : dataList){
					bw.append(data).append("\r");
				}
			}
			isSuccess=true;
		} catch (Exception e) {
			isSuccess=false;
		}finally{
			if(bw!=null){
				try {
					bw.close();
					bw=null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(osw!=null){
				try {
					osw.close();
					osw=null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null){
				try {
					out.close();
					out=null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return isSuccess;
	}

	/**
	 * import
	 *
	 * @param file path + file
	 * @return
	 */
	public static List<String> importCsv(File file){
		List<String> dataList=new ArrayList<String>();

		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				dataList.add(line);
			}
		}catch (Exception e) {
		}finally{
			if(br!=null){
				try {
					br.close();
					br=null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return dataList;
	}

}